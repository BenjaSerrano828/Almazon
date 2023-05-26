package productos;

public class Fruta extends Producto {
    public Fruta(String nombre, int valor, int stock,int codigo) {
        super(nombre, valor,stock,codigo);
    }
    public Fruta() {
    }
    @Override
    public String toString() {
        return "\nFruta" +
                super.toString();
    }
}
