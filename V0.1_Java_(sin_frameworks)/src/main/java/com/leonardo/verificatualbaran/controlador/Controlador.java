package com.leonardo.verificatualbaran.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.leonardo.verificatualbaran.entidades.Albaran;
import com.leonardo.verificatualbaran.entidades.Producto;
import com.leonardo.verificatualbaran.entidades.ProductoEnAlbaran;

/**
 *
 * @author Leonardo Méndez
 */
public class Controlador {

    public static List<ProductoEnAlbaran> leerAlbaran(File pdfFile) {
        List<ProductoEnAlbaran> listaProductos = new ArrayList<>();
        ProductoEnAlbaran nuevoProductoEnAlbaran;
        Producto nuevoProducto = null;
        Albaran nuevoAlbaran = null;
        String codigo, nombre, numero = null;
        LocalDate fecha = null;
        int cantidadBultos;
        int cantidadUnidades;

        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            Pattern patronItems = Pattern.compile("(\\d{2,6})\\s{1,1}(.{3,32}?)\\s{1,1}(\\.{0,1})\\s{1,8}(\\d{1,6})\\s{1,3}(\\d{1,3})\\s{1,5}(\\d{0,4})");

            Matcher matcherItems = patronItems.matcher(text);

            Pattern patronNumero = Pattern.compile("NUMERO DE ALBARAN ..........: (\\d{1,2})- (\\d{1,9})");
            Matcher matcherNumero = patronNumero.matcher(text);
            if (matcherNumero.find()) {
                numero = matcherNumero.group(1) + "- " + matcherNumero.group(2);
            }

            Pattern patronFecha = Pattern.compile("Fecha ..:  (\\d{2}/\\d{2}/\\d{2})"); // Patrón para dd/mm/aa
            Matcher matcherFecha = patronFecha.matcher(text);

            if (matcherFecha.find()) {
                try {
                    fecha = LocalDate.parse(matcherFecha.group(1), DateTimeFormatter.ofPattern("dd/MM/yy"));
                } catch (DateTimeParseException e) {
                    System.err.println("Error al analizar la fecha: " + e.getMessage());
                }
            }

            nuevoAlbaran = new Albaran(numero, fecha);

            while (matcherItems.find()) {
                codigo = matcherItems.group(1);

                nombre = matcherItems.group(2);

                cantidadBultos = Integer.parseInt(matcherItems.group(4));
                cantidadUnidades = 0;
                String unidadesPRE = matcherItems.group(6);
                if (!unidadesPRE.equals("")) {
                    cantidadUnidades = Integer.parseInt(unidadesPRE);
                }
                nuevoProducto = new Producto(codigo, nombre);

                nuevoProductoEnAlbaran = new ProductoEnAlbaran(nuevoProducto, nuevoAlbaran, cantidadBultos, cantidadUnidades);
                listaProductos.add(nuevoProductoEnAlbaran);

            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo PDF: " + e.getMessage()); // Mensaje más detallado
        }
        System.out.println(listaProductos.toString());

        return listaProductos;
    }

    public static void actualizarLineadelTXT(String id, String columna, String nuevoValor) {

    }

    public static void borrarLineadelTXT(List<Producto> listaProductos, String id) {

        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equals(id)) {
                listaProductos.remove(i);

                break;
            }
        }

        //escribir en el txt la nueva lista de productos con el cambio 
        try {
            FileOutputStream fos = new FileOutputStream("producto.txt", false);
            OutputStreamWriter os = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(os);
            String linea;
            for (int j = 0; j < listaProductos.size(); j++) {

                linea = listaProductos.get(j).getCodigo() + "    " + listaProductos.get(j).getNombre() + "    "
                        + listaProductos.get(j).getEanProducto() + "    " + listaProductos.get(j).getEanBulto();
                bw.write(linea, 0, linea.length());

                bw.newLine();
            }

            bw.close();
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    public static List<Producto> obtenerListaProductos() {

        File archivotxt = new File("producto.txt");
        List<Producto> listaProductos = new ArrayList<>();
        String codigo_t;
        String nombre_t;
        String eanBulto_t;
        String eanProducto_t;

        String regex1 = "^(\\d{1,6})\\s{3,}(.{0,32})\\s*(\\d*)\\s*(\\d*)\\s*$"; // Expresión regular

        Pattern pattern = Pattern.compile(regex1);

        try (BufferedReader br = new BufferedReader(new FileReader(archivotxt))) {
            String linea = null;

            while ((linea = br.readLine()) != null) {

                Matcher matcher = pattern.matcher(linea);

                if (matcher.find()) {
                    codigo_t = matcher.group(1);
                    nombre_t = matcher.group(2);
                    nombre_t = nombre_t.replace("  ", "");
                    eanProducto_t = matcher.group(3);
                    eanBulto_t = matcher.group(4);
                    listaProductos.add(new Producto(codigo_t, nombre_t, eanProducto_t, eanBulto_t));

                }
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return listaProductos;

    }

}
