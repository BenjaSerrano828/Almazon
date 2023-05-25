package Productos;

public class Pan extends Producto {
    public Pan() {
    }
    public Pan(String nombre, int valor,int stock,int codigo) {
        super(nombre, valor, stock,codigo);
    }
    @Override
    public String toString() {
        return "\nPan" +
                super.toString();
    }
}
