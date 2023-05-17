package Productos;

public class Fruta extends Producto {

    public Fruta(String nombre, int valor, int stock) {
        super(nombre, valor,stock,"Fruta");
    }
    public Fruta(String nombre, int valor, int stock,int codigo) {
        super(nombre, valor,stock,"Fruta");


    }

    public Fruta() {
    }

    public Fruta(Producto p) {
        transformarFruta(p);
    }
    public void transformarFruta(Producto p){
        ((Fruta) p ).getClass();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
