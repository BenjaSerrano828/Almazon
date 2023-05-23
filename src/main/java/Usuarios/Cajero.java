package Usuarios;
import BaseDeDatos.GestorArchivo;
import BaseDeDatos.GestorBaseDatos;
import Productos.*;
import Sesion.Sesion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Cajero extends Usuario {

    Scanner teclado = new Scanner(System.in);
    ArrayList<Producto> productosParaEditar = new ArrayList<>();
    public Cajero(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
    }
    public Cajero() {
    }
    public void iniciarMenuPrincipalCajero() {
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Abarrote");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Bebida");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Congelado");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Fruta");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Pan");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Snack");

        int opcion = -1;
        do{
            try{
                System.out.println("\n--- Bienvenido al menu principal ---" +
                        "\n¿Que desea realizar? Ingrese una opcion: " +
                        "\n(1)-> Realizar Venta" +
                        "\n(2)-> Cerrar Sesion");
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        menuRealizarVenta();
                        break;
                    case 2:
                        Sesion sesionNueva = new Sesion();
                        sesionNueva.primerInicioSesion();
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            }catch(InputMismatchException e){
                System.out.println("Error al seleccionar opcion");
                teclado.next();
            }
        }while(opcion!=2);
        menuRealizarVenta();
    }
    public void menuRealizarVenta(){
        System.out.println(productosParaEditar);
        System.out.print("Ingrese el codigo del producto: ");
        int codigo = teclado.nextInt();
        venderProducto(encontrarProductoPorCodigo(productosParaEditar,codigo));
    }
    public void venderProducto(Producto p){
        System.out.println(p);
        System.out.print("Ingrese la cantidad a vender: ");
        int cantidadVendida = teclado.nextInt();
        int stockActual = p.getStock();
        int stockFinal = stockActual - cantidadVendida;
        p.setStock(stockFinal);
        System.out.println("Producto vendido");
        GestorBaseDatos.guardarCambiosProductos(productosParaEditar);

        iniciarMenuPrincipalCajero();

    }
    public Producto encontrarProductoPorCodigo(ArrayList<Producto> productos,int codigo){
        for  (Producto p : productos) {
            if (p.getCodigo()==codigo) {
                return p;
            }
        }
        return null;
    }


    public void mostrarProductos(){
        GestorArchivo gestorArchivo = new GestorArchivo();
        System.out.println(gestorArchivo.leerArchivo("ArchivosBD/productos.txt"));
    }


    @Override
    public String toString() {
        return "\nCajero" +
                "\nNombre: "+ getNombre() +
                "\nRut: "+getRut() +
                "\nNombre de Usuario: "+getNombreUsuario() +
                "\nContraseña: "+getContrasena();
    }
}
