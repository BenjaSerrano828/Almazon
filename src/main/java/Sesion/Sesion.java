package Sesion;

import Usuarios.Admin;
import Usuarios.Cajero;
import Usuarios.Usuario;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Sesion {

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public void anadirUsuario(Usuario u) {
        usuarios.add(u);
    }

    Scanner teclado = new Scanner(System.in);

    public Sesion() {
    }

    public void iniciarSesion() {
        cargarDatos();
        System.out.println(usuarios.get(0));

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

    public void cargarDatos(){
        Path path = Paths.get("ArchivosBD/admins.txt");
        try {
            ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(path);

            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i).equals("Admin")) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String rut = lineas.get(i + 2).substring(5);
                    String nombreUsuario = lineas.get(i + 3).substring(19);
                    String contrasena = lineas.get(i + 4).substring(12);
                    usuarios.add(new Admin(rut, nombre, nombreUsuario, contrasena));
                } else if (lineas.get(i).equals("Cajero")) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String rut = lineas.get(i + 2).substring(5);
                    String nombreUsuario = lineas.get(i + 3).substring(19);
                    String contrasena = lineas.get(i + 4).substring(12);
                    usuarios.add(new Cajero(rut,nombre, nombreUsuario, contrasena));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
