package Productos;

public class Congelado extends Producto {
    public Congelado() {
    }

    public Congelado(String nombre, int valor,int stock) {
        super(nombre, valor,stock,"Congelado");
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
