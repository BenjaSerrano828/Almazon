package productos;

public class Congelado extends Producto {
    private String marca;

    public Congelado() {
    }
    public Congelado(String nombre, int valor,int stock,String marca, int codigo) {
        super(nombre, valor,stock,codigo);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "\nCongelado" +
                super.toString() +
                "\nMarca: "+marca;
    }
}
