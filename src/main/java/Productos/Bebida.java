package productos;

public class Bebida extends Producto {
    private double pesoLitro;
    public Bebida() {
    }
    public Bebida(String nombre, int valor,int stock, int codigo,double pesoLitro) {
        super(nombre, valor,stock,codigo);
        this.pesoLitro = pesoLitro;
    }
    public double getPesoLitro() {
        return pesoLitro;
    }
    @Override
    public String toString() {
        return "\nBebida" +
                super.toString() +
                "\nLitros: "+ pesoLitro;
    }
}
