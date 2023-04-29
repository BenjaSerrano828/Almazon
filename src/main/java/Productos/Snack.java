package Productos;

import Productos.Producto;

public class Snack extends Producto {
    public Snack() {
        registrarNuevoProducto();
    }

    public Snack(String nombre, int valor,int stock, String tipo) {
        super(nombre, valor,stock, tipo);
    }
}
