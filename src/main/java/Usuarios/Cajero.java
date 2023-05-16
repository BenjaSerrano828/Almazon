package Usuarios;
import java.util.ArrayList;
import java.util.Scanner;

public class Cajero extends Usuario {

    Scanner teclado = new Scanner(System.in);
    private ArrayList<Usuario> cajeros = new ArrayList<>();


    public Cajero(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
        this.permiso=2; // 2 es todos los permisos de cajero
        this.id=idInicial++;
    }

    public Cajero() {
        registrarCajero();
    }

    public void registrarCajero() {
        System.out.println("Ingrese en nombre del nuevo Cajero");
        String nombre = teclado.next();
        System.out.println("Ingrese el rut del nuevo Cajero");
        String rut = teclado.next();
        System.out.println("Ingrese el nombre de usuario del nuevo Cajero");
        String nombreUsuario = teclado.next();
        System.out.println("Ingrese la contraseña del nuevo Cajero");
        String contrasena = teclado.next();
        Usuario cajero = new Cajero(rut,nombre,nombreUsuario,contrasena);
        cajeros.add(cajero);

    }

    public void iniciarMenuPrincipalCajero() { //ESTO NO VA EN SESION, VA EN CAJERO
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
