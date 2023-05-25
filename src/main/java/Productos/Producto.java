package Productos;
public abstract class Producto {
    private String nombre;
    private int codigo;
    private int valor;
    private int stock=0;
    public Producto() {
    }
    public Producto(String nombre, int valor,int stock,int codigo) {
        this.nombre = nombre;
        this.valor = valor;
        this.stock = stock;
        this.codigo = codigo;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getValor() {
        return valor;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public String toString() {
        return "\nNombre: "+ nombre +
                "\nValor: "+ valor +
                "\nStock: "+ stock +
                "\nCodigo: "+ codigo
                ;
    }
}
