package Usuarios;

import java.util.Scanner;

public class Admin extends Usuario {
    private int idActual = 1;
    private static Scanner teclado =  new Scanner(System.in);

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
