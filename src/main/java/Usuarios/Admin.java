package Usuarios;

import Productos.Fruta;
import Productos.Producto;
import Sesion.Sesion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Usuario {
    private int idActual = 1;
    private static Scanner teclado =  new Scanner(System.in);

    public Admin() {
    }

    @Override
    public String toString() {
        return "Usuarios.Admin{" +
                "id=" + id +
                '}';
    }


    public Admin(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
        this.permiso=1; //1 es todos los permisos de admin
    }

    public void iniciarMenuPrincipalAdmin() { //ESTO NO VA EN SESION, VA EN ADMIN
        //menu de admin
        System.out.println("--- Bienvenido al menu principal ---" +
                "\n¿Que desea realizar? Ingrese una opcion: " +
                "\n(1)-> Administrar Usuarios" +
                "\n(2)-> Administrar Productos" +
                "\n(3)-> Cerrar Sesion");

        int opcion = teclado.nextInt();
        switch (opcion) {
            case 1:
                menuAdministrarUsuario();
                break;
            case 2:
                menuAdministrarProducto();
                break;
            case 3:
                Sesion sesionNueva = new Sesion();
            default:
                System.out.println("Ingrese una opcion valida");
        }
    }


    private void menuAdministrarUsuario() { //ESTO NO VA EN SESION, VA EN ADMIN
        System.out.println("Bienvenido al Menu de Administracion de Usuarios");
        System.out.println("Que desea realizar: ");
        System.out.println("1.- Crear Nuevo usuario");
        System.out.println("2.- Editar usuario");
        System.out.println("3.- Eliminar Usuarios.Usuario");
        int opcion = teclado.nextInt();
        switch (opcion) {
            case 1:
                Usuario cajero = new Cajero();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
    }


    private void menuAdministrarProducto() { //ESTO NO VA EN SESION, VA EN ADMIN
        System.out.println("Que desea realizar");
        System.out.println("1.- Registrar Nuevo Producto");
        System.out.println("2.- Modificar");
        System.out.println("3.- Eliminar");
        System.out.println("0.- Atras");

        int opcion = teclado.nextInt();

        do{
            try{
                switch (opcion) {
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            }catch (InputMismatchException e){
                System.out.println("\n Ingrese una opcion valida");
                teclado.next();
            }
        }while(opcion!=0);

    }

    public void registrarNuevoProductoAdmin() {
        System.out.println("Que tipo de producto desea registrar: ");
        System.out.println("1- Fruta");
        System.out.println("2- Pan");
        System.out.println("3- Bebida");
        System.out.println("4- Abarrote");
        System.out.println("5- Snack");

        int opcion = teclado.nextInt();

        do{
            try{
                switch (opcion) {
                    case 1:
                        Fruta f = new Fruta();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            }catch (InputMismatchException e){
                System.out.println("\n Ingrese una opcion valida");
                teclado.next();
            }
        }while(opcion!=0);


    }


    public void crearProducto() {

    }

    public void crearNuevoUsuario(){
        int opcion;
        System.out.println("Que usuario desea crear:");
        System.out.println("1.- Usuarios.Admin");
        System.out.println("2.- Usuarios.Cajero");
        //System.out.println("Ingrese el nombre del usuario");
        opcion = teclado.nextInt();
        if(opcion==1){
            crearNuevoAdmin();

            //Usuarios.Usuario admin = new Usuarios.Admin();

        }
        if (opcion==2){
            //Usuarios.Usuario cajero = new Usuarios.Cajero();

        }
        else {

        }




    }

    public void crearNuevoAdmin() {
        System.out.println("Ingrese el nombre del Administrador: ");
        nombre = teclado.next();

    }

}
