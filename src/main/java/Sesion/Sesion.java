package Sesion;
import BaseDeDatos.GestorBaseDatos;
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
    public void primerInicio(){
        GestorBaseDatos.cargarDatosUsuarios(usuarios);
        iniciarSesion();
    }
    public void iniciarSesion() {
        System.out.println("\n-----Menu Iniciar Sesion-----");
        System.out.print("Ingrese su nombre de Usuario: ");
        String nombreUsuarioIngresado = teclado.next();
        System.out.print("Ingrese su contraseña: ");
        String contrasenaIngresada = teclado.next();
        if (validarSesion(usuarios,nombreUsuarioIngresado,contrasenaIngresada)){
            Usuario usuarioEncontrado = buscarUsuario(usuarios, nombreUsuarioIngresado);
            direccionarMenu(usuarioEncontrado);
        }else{
            iniciarSesion();
        }
    }
    public Usuario buscarUsuario(ArrayList<Usuario> usuarios, String nombreUsuario) {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario)) {
                return u;
            }
        }
        return null;
    }
    public boolean validarSesion(ArrayList<Usuario> usuarios, String nombreUsuario ,String contrasena){
        for  (Usuario u: usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario)
                    && u.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
    public void direccionarMenu(Usuario u) {
        if(u instanceof Admin){
            ((Admin) u).iniciarMenuPrincipalAdmin();
        }else if(u instanceof Cajero){
            ((Cajero) u).iniciarMenuPrincipalCajero();
        }
    }
}
