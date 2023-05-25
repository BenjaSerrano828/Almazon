package Productos;

public class Abarrote extends Producto {
    private String marca;
    public Abarrote() {
    }
    public Abarrote(String nombre, int valor,int stock, int codigo, String marca) {
        super(nombre, valor,stock,codigo);
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }
    @Override
    public String toString() {
        return "\nAbarrote" +
                super.toString()+
                "\nMarca: "+marca;
    }
}
