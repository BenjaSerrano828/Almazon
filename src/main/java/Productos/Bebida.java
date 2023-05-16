package Productos;

public class Bebida extends Producto {
    public Bebida() {
    }

    public Bebida(String nombre, int valor,int stock) {
        super(nombre, valor,stock, "Bebida");
    }
}
