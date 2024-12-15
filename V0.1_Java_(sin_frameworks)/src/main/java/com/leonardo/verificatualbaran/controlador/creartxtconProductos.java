
package com.leonardo.verificatualbaran.controlador;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author Leonardo Méndez
 */
public class creartxtconProductos {
    /*
    public static void main(String[] args) {
        File pdfFile=new File("/home/leonardo/NetBeansProjects/Proyecto_VerificaTuAlbaran/Archivos_Varios/14128_RD484026_Entrega-segun-pedido_924322_29618832.pdf");
        //las siguientes tres lineas crean un variable llamada text que contine un String con todo el contenido del PDF      
        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            //creamos un patron con expresiones regulares para buscar la información dentro de la variable text
            Pattern patron = Pattern.compile("(\\d{2,6})\\s{1,1}(.{3,32}?)\\s{1,9}(\\d{1,6})\\s{1,3}\\d{1,3}");
            // cramos un buscardor para que ubique el patron dentro del documento
            Matcher matcher = patron.matcher(text);
            
            //instaciamos los objetos necesarios para escribir en el archivo que queremos crear
            FileOutputStream fos = new FileOutputStream("producto.txt", true); 
            OutputStreamWriter os = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(os);
            
            //utilizamos el metodo find del buscador(matcher) para que ubique las partes señaladas en el patrón
            while (matcher.find()) {
                String codigo = matcher.group(1);
                String nombre = matcher.group(2);
                //creamos un String con los datos obteneidos de text
                String linea = codigo + "   " + nombre;
                //escribimos el String generado en el archivo txt
                bw.write(linea, 0, linea.length());
                bw.newLine();

            }
            //cerramos los servicios abiertos
            bw.close();
            os.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo PDF: " + e.getMessage()); // Mensaje más detallado
        }
    }
*/
}
