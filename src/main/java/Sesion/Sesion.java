package Sesion;

import BaseDeDatos.GestorBaseDatos;
import Productos.Producto;
import Usuarios.Admin;
import Usuarios.Cajero;
import Usuarios.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class Sesion {

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    Scanner teclado = new Scanner(System.in);

    public Sesion() {
    }


    public void iniciarSesion() {
        GestorBaseDatos.cargarDatosUsuarios(usuarios);
        System.out.println(usuarios);
        System.out.println("\n-----Menu Iniciar Sesion-----");
        System.out.print("Ingrese su nombre de Usuario: ");
        String nombreUsuarioIngresado = teclado.next();
        System.out.print("Ingrese su contraseña: ");
        String contrasenaIngresada = teclado.next();

        for (int i = 0; i < usuarios.size(); i++) {

            if (validarSesion(usuarios,nombreUsuarioIngresado,contrasenaIngresada)==true){

                direccionarMenu(usuarios.get(i));

            }else {
                iniciarSesion();
            }
        }
    }
    public boolean validarSesion(ArrayList<Usuario> usuarios,String nombreUsuario,String contrasena){
        for  (Usuario u: usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario)
                    && u.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }


    public void direccionarMenu(Usuario u) {

        if(u.getClass() == Admin.class){
            ((Admin) u).iniciarMenuPrincipalAdmin();
        }else{
            ((Cajero) u).iniciarMenuPrincipalCajero();
        }
    }

}
