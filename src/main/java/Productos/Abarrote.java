package Productos;

import Productos.Producto;

public class Abarrote extends Producto {
    public Abarrote() {
        registrarNuevoProducto();
    }

    public Abarrote(String nombre, int valor,int stock) {
        super(nombre, valor,stock, "Abarrote");
    }
}
