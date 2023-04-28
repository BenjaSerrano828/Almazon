import java.util.ArrayList;
import java.util.Scanner;

public abstract class Producto {

    private String nombre;
    private int codigo;
    private static int codigoInicial=1;
    private int valor;
    private String tipo;

    private ArrayList<Producto> productos = new ArrayList<>();

    Scanner teclado = new Scanner(System.in);

    public Producto() {
    }

    public Producto(String nombre, int valor, String tipo) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
        this.codigo = codigoInicial;
    }
    protected void registrarNuevoProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        nombre = teclado.next();
        System.out.print("Ingrese el valor que tendrá el producto: ");
        valor = teclado.nextInt();
        System.out.print("Ingrese el tipo de producto: ");
        tipo = teclado.next();
        //Producto p = new Producto(nombre,valor,tipo);
        //anadirProductoAlmacen(p);

    }
    public void anadirProductoAlmacen(Producto p) {
        productos.add(p);
    }

}
