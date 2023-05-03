package Productos;

import Productos.Producto;

public class Snack extends Producto {
    public Snack() {
        registrarNuevoProducto();
    }

    public Snack(String nombre, int valor,int stock) {
        super(nombre, valor,stock, "Snack");
    }
}
