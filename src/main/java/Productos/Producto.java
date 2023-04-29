package Productos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Producto {

    private String nombre;
    private int codigo;
    private static int codigoInicial=1;
    private int valor;
    private String tipo;
    private int stock=0;

    private ArrayList<Producto> productos = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);

    public Producto() {
    }

    public Producto(String nombre, int valor,int stock, String tipo) {
        this.nombre = nombre;
        this.valor = valor;
        this.stock = stock;
        this.tipo = tipo;
        this.codigo = codigoInicial;
    }
    public void registrarNuevoProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        nombre = teclado.next();
        System.out.print("Ingrese el valor que tendrá el producto: ");
        valor = teclado.nextInt();
        System.out.print("Ingrese el stock inicial del producto: ");
        stock = teclado.nextInt();
        System.out.println("Selccione que tipo de producto desea registrar: ");
        System.out.println("1.- Productos.Fruta");
        System.out.println("2.- Productos.Pan");
        System.out.println("3.- Productos.Bebida");
        System.out.println("4.- Productos.Snack");
        System.out.println("5.- Productos.Congelado");
        System.out.println("6.- Productos.Abarrote");
        System.out.println("0.- Volver atras");


        int opcion=-1;

        do{
        try{
        switch (opcion){
            case 1:
                Producto fruta = new Fruta(nombre,valor,stock,"Productos.Fruta");
                anadirProductoAlmacen(fruta);
                break;
            case 2:
                Producto pan = new Pan(nombre,valor,stock,"Productos.Pan");
                anadirProductoAlmacen(pan);
                break;
            case 3:
                Producto bebida = new Bebida(nombre,valor,stock,"Productos.Bebida");
                anadirProductoAlmacen(bebida);
                break;
            case 4:
                Producto snack = new Snack(nombre,valor,stock,"Productos.Snack");
                anadirProductoAlmacen(snack);
                break;
            case 5:
                Producto congelado = new Congelado(nombre,valor,stock,"Productos.Congelado");
                anadirProductoAlmacen(congelado);
                break;
            case 6:
                Producto abarrote = new Abarrote(nombre,valor,stock,"Productos.Abarrote");
                anadirProductoAlmacen(abarrote);
                break;
            case 0:
                break;
            default:
                System.out.println("Ingrese opcion valida");

        }
        } catch (InputMismatchException e){
            System.out.println("Ingrese una opcion valida");
            teclado.next();
        }

    }while (opcion!=0);
    }
    public void anadirProductoAlmacen(Producto p) {
        productos.add(p);
    }

}
