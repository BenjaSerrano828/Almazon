package Productos;

public class Snack extends Producto {
    private String marca;

    public Snack() {

    }

    public Snack(String nombre, int valor,int stock, int codigo) {
        super(nombre, valor,stock,codigo);
    }


    @Override
    public String toString() {
        return "\nSnack" +
                super.toString();
    }
}
