package sesion;
import baseDeDatos.GestorBaseDatos;
import usuarios.Admin;
import usuarios.Cajero;
import usuarios.Usuario;
import java.util.ArrayList;
import java.util.Scanner;
public class Sesion {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    public Sesion() {
    }
    public void primerInicioSesion(){
        GestorBaseDatos.cargarDatosUsuarios(usuarios);
        obtenerDatosUsuario();
    }
    public void obtenerDatosUsuario(){
        System.out.println("\n-----Menu Iniciar Sesion-----");
        String nombreUsuarioIngresado = ingresarNombreUsuario();
        String contrasenaIngresada = ingresarContrasena();
        boolean direccionar = iniciarSesion(contrasenaIngresada,nombreUsuarioIngresado);

        if (direccionar){
            Usuario usuarioEncontrado = buscarUsuario(usuarios, nombreUsuarioIngresado);
            direccionarMenu(usuarioEncontrado);
        }else {
            obtenerDatosUsuario();
        }
    }
    public boolean iniciarSesion(String contrasenaIngresada, String nombreUsuarioIngresado) {
        boolean valido;
        if (validarSesion(usuarios,nombreUsuarioIngresado,contrasenaIngresada)){
            valido = true;
        }else{
            System.err.println("\nEl nombre de usuario y la contraseña no coinciden");
            valido = false;
        }
        return valido;
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
    public String ingresarNombreUsuario() {
        System.out.print("Ingrese su nombre de Usuario: ");
        return teclado.next();
    }
    public String ingresarContrasena() {
        System.out.print("Ingrese su contraseña: ");
        return teclado.next();
    }

}
