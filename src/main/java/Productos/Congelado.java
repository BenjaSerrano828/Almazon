package Productos;

import Productos.Producto;

public class Congelado extends Producto {
    public Congelado() {
        registrarNuevoProducto();
    }

    public Congelado(String nombre, int valor,int stock, String tipo) {
        super(nombre, valor,stock,"Congelado");
    }
}
