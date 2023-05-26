package productos;

public class Snack extends Producto {
    private String marca;
    public Snack() {
    }
    public Snack(String nombre, int valor,int stock, String marca, int codigo) {
        super(nombre, valor,stock,codigo);
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }
    @Override
    public String toString() {
        return "\nSnack" +
                super.toString()+
                "\nMarca: "+marca;
    }
}
