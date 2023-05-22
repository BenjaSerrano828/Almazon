package Productos;

public class Bebida extends Producto {
    private double pesoLitros;

    public Bebida() {
    }
    public Bebida(String nombre, int valor,int stock, int codigo,double pesoLitros) {
        super(nombre, valor,stock,codigo);
        this.pesoLitros=pesoLitros;
    }

    public double getPesoLitros() {
        return pesoLitros;
    }

    @Override
    public String toString() {
        return "\nBebida" +
                super.toString() +
                "\nLitros: "+pesoLitros;
    }
}
