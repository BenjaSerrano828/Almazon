package Productos;

import Productos.Producto;

public class Abarrote extends Producto {
    public Abarrote() {
        registrarNuevoProducto();
    }

    public Abarrote(String nombre, int valor,int stock, String tipo) {
        super(nombre, valor,stock, tipo);
    }
}
