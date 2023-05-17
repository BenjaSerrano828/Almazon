package Productos;
public abstract class Producto {
    private String nombre;
    private int codigo;
    private static int codigoActual=1;
    private int valor;
    private String tipo;
    private int stock=0;
    public Producto() {
    }

    public Producto(String nombre, int valor,int stock, String tipo) {
        this.nombre = nombre;
        this.valor = valor;
        this.stock = stock;
        this.tipo = tipo;
        this.codigo = codigoActual++;
    }

    @Override
    public String toString() {
        return "\nFruta" +
                "\nNombre: "+ nombre +
                "\nValor: "+ valor +
                "\nStock: "+ stock +
                "\nCodigo: "+ codigo
                ;
    }
}
