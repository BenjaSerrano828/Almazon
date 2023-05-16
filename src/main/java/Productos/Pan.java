package Productos;

public class Pan extends Producto {
    public Pan() {
    }

    public Pan(String nombre, int valor,int stock) {
        super(nombre, valor, stock, "Fruta");
    }
}
