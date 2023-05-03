package Productos;

import Productos.Producto;

public class Bebida extends Producto {
    public Bebida() {
        registrarNuevoProducto();
    }

    public Bebida(String nombre, int valor,int stock) {
        super(nombre, valor,stock, "Bebida");
    }
}
