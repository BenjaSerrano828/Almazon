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




        /*int opcion=-1;
        do{
            try{
                switch (opcion){
                    case 1:
                        Producto fruta = new Fruta();
                        anadirProductoAlmacen(fruta);
                        break;
                    case 2:
                        Producto pan = new Pan(nombre,valor,stock,"Pan");
                        anadirProductoAlmacen(pan);
                        break;
                    case 3:
                        Producto bebida = new Bebida(nombre,valor,stock,"Bebida");
                        anadirProductoAlmacen(bebida);
                        break;
                    case 4:
                        Producto snack = new Snack(nombre,valor,stock,"Snack");
                        anadirProductoAlmacen(snack);
                        break;
                    case 5:
                        Producto congelado = new Congelado(nombre,valor,stock,"Congelado");
                        anadirProductoAlmacen(congelado);
                        break;
                    case 6:
                        Producto abarrote = new Abarrote(nombre,valor,stock,"Abarrote");
                        anadirProductoAlmacen(abarrote);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
            } catch (InputMismatchException e){
            System.out.println("Ingrese una opcion valida");
            teclado.next();
            }
        }while (opcion!=0);*/
    }
    public void anadirProductoAlmacen(Producto p) {
        productos.add(p);
    }

}
