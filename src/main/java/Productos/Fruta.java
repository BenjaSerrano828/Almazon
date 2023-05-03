package Productos;

import Productos.Producto;

public class Fruta extends Producto {

    public Fruta(String nombre, int valor,int stock) {
        super(nombre, valor,stock,"Fruta");
    }

    public Fruta() {
        registrarNuevoProducto();
    }
}
