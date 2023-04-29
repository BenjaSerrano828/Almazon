package Sesion;

import Productos.Producto;
import Usuarios.Admin;
import Usuarios.Cajero;
import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sesion {

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    Usuario adminSupremo = new Admin("rut del dueño", "admin", "admin", "admin123");

    public void anadirUsuario(Usuario u) {
        usuarios.add(u);
    }

    Scanner teclado = new Scanner(System.in);

    public Sesion() {
        iniciarSesion();
    }

    public void iniciarSesion() {

        anadirUsuario(adminSupremo);

        System.out.print("Ingrese nombre de Usuario: ");
        String nombreUsuarioIngresado = teclado.next();
        System.out.println("Ingrese contraseña: ");
        String contrasenaIngresada = teclado.next();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(nombreUsuarioIngresado)
                    & usuarios.get(i).getContrasena().equals(contrasenaIngresada)){
                System.out.println("Nombre de usuario y contraseña correctos");
                direccionarMenu(usuarios.get(i));

            }
            else{
                System.out.println("nombre de usuario o contraseña incorrectos");
            }
        }
        //segun tipo de usuario se abre un menu distinto

    }

    public void direccionarMenu(Usuario u) {
        if(u.getPermiso() == 1) { // si permiso es 1 significa que es admin
            iniciarMenuPrincipalAdmin();


        }else { //else es decir 2, significa que es cajero
            iniciarMenuPrincipalCajero();

        }
        // if permiso = 1 -> menu de admin
        //else -> menu de cajero

    }


    private void iniciarMenuPrincipalCajero() { //ESTO NO VA EN SESION, VA EN CAJERO
    }

    private void iniciarMenuPrincipalAdmin() { //ESTO NO VA EN SESION, VA EN ADMIN
        //menu de admin
        System.out.println("--- Bienvenido al menu principal ---" +
                "\n¿Que desea realizar? Ingrese una opcion: " +
                "\n(1)-> Administrar Usuarios" +
                "\n(2)-> Administrar Productos" +
                "\n(3)-> Cerrar Sesion");
        seleccionarOpcion();
    }

    private void seleccionarOpcion() { //ESTO NO VA EN SESION, VA EN ADMIN
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
        System.out.println("QUe desea realizar: ");
        System.out.println("1.- Crear Nuevo usuario");
        System.out.println("2.- Editar usuario");
        System.out.println("3.- Eliminar Usuarios.Usuario");
        seleccionarOpcionAdministrarUsuario();
    }

    private void seleccionarOpcionAdministrarUsuario() { //ESTO NO VA EN SESION, VA EN ADMIN
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
        System.out.println("Que tipo de producto desea registrar: ");
        System.out.println("1- Productos.Fruta");
        //seleccionarProducto();

    }

    /*private void seleccionarProducto() { //ESTO NO VA EN SESION, VA EN ADMIN
        int opcion = teclado.nextInt();
        try{


        }catch (InputMismatchException e){
            System.out.println("\n Ingrese una opcion valida");
        }
        teclado.next();
        switch (opcion) {
            case 1:
                Producto fruta = new Fruta();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
    } */
}
