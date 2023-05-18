package Productos;

public class Abarrote extends Producto {
    private String marca;
    public Abarrote() {
    }
    public Abarrote(String nombre, int valor,int stock,int codigo) {
        super(nombre, valor,stock,codigo);
    }

    @Override
    public String toString() {
        return "\nAbarrote" +
                super.toString();
    }
}
