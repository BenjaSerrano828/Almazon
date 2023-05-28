package usuarios;
import baseDeDatos.GestorBaseDatos;
import productos.*;
import sesion.Sesion;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Cajero extends Usuario {
    Scanner teclado = new Scanner(System.in);
    ArrayList<Producto> productosParaEditar = new ArrayList<>();
    private int valorTotalVenta = 0;
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
                System.out.println("\n--- Bienvenido al menu principal ---\n¿Que desea realizar? Ingrese una opcion:\n(1)-> Realizar Venta\n(2)-> Cerrar Sesion");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        venderProducto();
                        break;
                    case 2:
                        Sesion sesionNueva = new Sesion();
                        sesionNueva.primerInicioSesion();
                        break;
                    default:
                        System.err.println("\nIngrese una opcion valida");
                }
            }catch(InputMismatchException e){
                System.err.println("\nError al seleccionar opcion");
                teclado.next();
            }
        }while(opcion!=2);
    }
    public void venderProducto(){
        System.out.println(productosParaEditar);
        System.out.print("\nIngrese el codigo del producto: ");
        int codigo = teclado.nextInt();
        Producto p = encontrarProductoPorCodigo(productosParaEditar,codigo);
        System.out.println(p);
        System.out.print("Ingrese la cantidad a vender: ");
        int cantidadVendida = teclado.nextInt();
        int stockActual = p.getStock();
        int stockFinal = stockActual - cantidadVendida;
        p.setStock(stockFinal);
        int valorProductos = p.getValor() * cantidadVendida;
        System.out.println("Producto vendido\n" + "\n" + p.getNombre() + "\n$" + p.getValor());
        System.out.println("Cantidad vendida: " + cantidadVendida);
        System.out.println("Total del producto: $"+ valorProductos);
        valorTotalVenta += valorProductos;
        GestorBaseDatos.guardarCambiosProductos(productosParaEditar);


        System.out.println("¿Desea añadir otro producto?\n(1)-> Si\n(2)-> No");
        int opcion = teclado.nextInt();
        try{
            if (opcion == 1){
                venderProducto();
            }else if (opcion == 2){
                System.out.println("El valor total es: $" + valorTotalVenta);
                iniciarMenuPrincipalCajero();
            }else{
                System.err.println("Ingrese una opcion valida");
            }
        }catch (InputMismatchException e){
            System.err.println("Ingrese una opcion valida");
        }
    }
    public Producto encontrarProductoPorCodigo(ArrayList<Producto> productos,int codigo){
        for  (Producto p : productos) {
            if (p.getCodigo()==codigo) {
                return p;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "\nCajero" +
                "\nNombre: "+ getNombre() +
                "\nRut: "+getRut() +
                "\nNombre de Usuario: "+getNombreUsuario() +
                "\nContrasena: "+getContrasena();
    }
}
