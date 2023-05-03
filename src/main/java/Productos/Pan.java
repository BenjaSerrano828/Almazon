package Productos;

import Productos.Producto;

public class Pan extends Producto {
    public Pan() {
        registrarNuevoProducto();
    }

    public Pan(String nombre, int valor,int stock) {
        super(nombre, valor, stock, "Fruta");
    }
}
