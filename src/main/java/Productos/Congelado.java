package Productos;

public class Congelado extends Producto {
    private String marca;

    public Congelado() {
    }
    public Congelado(String nombre, int valor,int stock,int codigo) {
        super(nombre, valor,stock,codigo);
    }



    @Override
    public String toString() {
        return "\nCongelado" +
                super.toString();
    }
}
