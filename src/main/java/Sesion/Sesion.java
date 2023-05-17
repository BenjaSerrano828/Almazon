package Sesion;

import BaseDeDatos.GestorBaseDatos;
import Productos.Fruta;
import Productos.Producto;
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
    private ArrayList<Producto> productos = new ArrayList<>();

    Scanner teclado = new Scanner(System.in);

    public Sesion() {
    }

    public void iniciarSesion() {
        GestorBaseDatos.cargarDatosUsuarios(usuarios);
        System.out.println(usuarios.get(3).getContrasena());
        GestorBaseDatos.cargarDatosProductos(productos,"Fruta");
        GestorBaseDatos.cargarDatosProductos(productos,"Pan");
        GestorBaseDatos.cargarDatosProductos(productos,"Snack");
        GestorBaseDatos.cargarDatosProductos(productos,"Bebida");
        GestorBaseDatos.cargarDatosProductos(productos,"Congelado");
        GestorBaseDatos.cargarDatosProductos(productos,"Abarrote");

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

    public void cargarDatosUsuarios(){
        Path path = Paths.get("ArchivosBD/usuarios.txt");
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

    public void cargarDatosProductos(){
        Path path = Paths.get("ArchivosBD/productos.txt");
        try {
            ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(path);

            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i).equals("Fruta")) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String valor = lineas.get(i + 2).substring(7);
                    int valorInt = Integer.parseInt(valor);
                    String stock = lineas.get(i + 3).substring(7);
                    int stockInt = Integer.parseInt(stock);
                    String codigo = lineas.get(i + 4).substring(8);
                    int codigoInt = Integer.parseInt(codigo);
                    productos.add(new Fruta(nombre,valorInt,stockInt,codigoInt));
                } else if (lineas.get(i).equals("Pan")) {
                    /*
                    String nombre = lineas.get(i + 1).substring(8);
                    String rut = lineas.get(i + 2).substring(5);
                    String nombreUsuario = lineas.get(i + 3).substring(19);
                    String contrasena = lineas.get(i + 4).substring(12);
                    usuarios.add(new Cajero(rut,nombre, nombreUsuario, contrasena)); */
                }
            }

        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
