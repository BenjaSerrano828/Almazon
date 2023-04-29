package Productos;

import Productos.Producto;

public class Fruta extends Producto {

    public Fruta(String nombre, int valor,int stock, String tipo) {
        super(nombre, valor,stock, tipo);
    }

    public Fruta() {
        registrarNuevoProducto();
    }
}
