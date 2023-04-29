package Productos;

import Productos.Producto;

public class Pan extends Producto {
    public Pan() {
        registrarNuevoProducto();
    }

    public Pan(String nombre, int valor,int stock, String tipo) {
        super(nombre, valor, stock, tipo);
    }
}
