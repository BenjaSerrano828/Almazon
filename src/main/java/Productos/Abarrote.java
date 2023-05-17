package Productos;

public class Abarrote extends Producto {
    public Abarrote() {
    }

    public Abarrote(String nombre, int valor,int stock) {
        super(nombre, valor,stock, "Abarrote");
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
