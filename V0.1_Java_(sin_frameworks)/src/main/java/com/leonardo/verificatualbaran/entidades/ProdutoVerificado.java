package com.leonardo.verificatualbaran.entidades;

/**
 *
 * @author Leonardo Méndez
 */
public class ProdutoVerificado {

    private Producto productoEnAlbaran;
    private int cantidad;

    public ProdutoVerificado(Producto productoEnAlbaran, int cantidad) {
        this.productoEnAlbaran = productoEnAlbaran;
        this.cantidad = cantidad;
    }

    public ProdutoVerificado(Producto productoEnAlbaran) {
        this.productoEnAlbaran = productoEnAlbaran;
    }

    public Producto getProductoEnAlbaran() {
        return productoEnAlbaran;
    }

    public void setProductoEnAlbaran(Producto productoEnAlbaran) {
        this.productoEnAlbaran = productoEnAlbaran;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ProdutoVerificado{" + "productoEnAlbaran=" + productoEnAlbaran + ", cantidad=" + cantidad + '}';
    }

   

}
