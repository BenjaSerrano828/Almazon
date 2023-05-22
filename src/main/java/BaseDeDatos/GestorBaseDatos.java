package BaseDeDatos;

import Productos.*;
import Usuarios.Admin;
import Usuarios.Cajero;
import Usuarios.Usuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GestorBaseDatos {

    public GestorBaseDatos() {
    }
    public static void cargarDatosUsuarios(ArrayList<Usuario> usuarios) {
        Path path = Paths.get("ArchivosBD/usuarios.txt");
        try {
            ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(path);

            for (int i = 0; i < lineas.size(); i++) {

                if (lineas.get(i).equals("Admin")) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String rut = lineas.get(i + 2).substring(5);
                    String nombreUsuario = lineas.get(i + 3).substring(19);
                    String contrasena = lineas.get(i + 4).substring(12);
                    Usuario admin = new Admin(rut, nombre, nombreUsuario, contrasena);
                    usuarios.add(admin);
                } else if (lineas.get(i).equals("Cajero")) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String rut = lineas.get(i + 2).substring(5);
                    String nombreUsuario = lineas.get(i + 3).substring(19);
                    String contrasena = lineas.get(i + 4).substring(12);
                    Usuario cajero = new Cajero(rut, nombre, nombreUsuario, contrasena);
                    usuarios.add(cajero);
                }
            }

        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    public static void cargarDatosProductos(ArrayList<Producto> productos, String producto) {
        Path path = Paths.get("ArchivosBD/productos.txt");
        try {
            ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(path);

            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i).equals(producto)) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String valor = lineas.get(i + 2).substring(7);
                    int valorInt = Integer.parseInt(valor);
                    String stock = lineas.get(i + 3).substring(7);
                    int stockInt = Integer.parseInt(stock);
                    String codigo = lineas.get(i + 4).substring(8);
                    int codigoInt = Integer.parseInt(codigo);

                    if (producto.equals("Fruta")){
                        productos.add(new Fruta(nombre, valorInt, stockInt, codigoInt));
                    }else if (producto.equals("Pan")){
                        productos.add(new Pan(nombre, valorInt, stockInt, codigoInt));
                    }else if (producto.equals("Abarrote")){
                        String marcaAbarrote = lineas.get(i + 5).substring(7);
                        productos.add(new Abarrote(nombre, valorInt, stockInt,marcaAbarrote, codigoInt));
                    }else if (producto.equals("Bebida")){
                        String litros = lineas.get(i + 5).substring(8);
                        double litrosDouble = Double.parseDouble(litros);
                        productos.add(new Bebida(nombre, valorInt, stockInt, codigoInt,litrosDouble));
                    }else if (producto.equals("Congelado")){
                        String marcaCongelado = lineas.get(i + 5).substring(7);
                        productos.add(new Congelado(nombre, valorInt, stockInt,marcaCongelado, codigoInt));
                    }else if (producto.equals("Snack")){
                        String marcaSnack = lineas.get(i + 5).substring(7);
                        productos.add(new Snack(nombre, valorInt, stockInt ,marcaSnack, codigoInt));
                    }
                }
            }
        } catch(IOException e){
        }
    }

    public static int cargarCodigo() {
        Path path = Paths.get("ArchivosBD/codigo.txt");
        try {
            ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(path);

            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i).equals("Codigo")) {
                    String ultimoCodigo = lineas.get(i + 1).substring(8);
                    int ultimoCodigoInt = Integer.parseInt(ultimoCodigo);
                    return ultimoCodigoInt++;
                }
            }
        } catch(IOException e){
            System.out.println("ERROR");
        }
        return 0;
    }


    public static void guardarDatosUsuarios(ArrayList<Usuario> usuarios) {
        GestorArchivo gestorArchivo = new GestorArchivo();
        //"ArchivosBD/usuarios.txt"


        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getClass().equals("Admin")) {
                String nombre = usuarios.get(i).getNombre();
                String rut = usuarios.get(i).getRut();
                String nombreUsuario = usuarios.get(i).getNombreUsuario();
                String contrasena = usuarios.get(i).getContrasena();
                Usuario admin = new Admin(rut, nombre, nombreUsuario, contrasena);
                String contenido = admin.toString();
                gestorArchivo.nuevaLinea("ArchivosBD/usuarios.txt",contenido);
            } else if (usuarios.get(i).getClass().equals("Cajero")) {
                String nombre = usuarios.get(i).getNombre();
                String rut = usuarios.get(i).getRut();
                String nombreUsuario = usuarios.get(i).getNombreUsuario();
                String contrasena = usuarios.get(i).getContrasena();
                Usuario cajero = new Cajero(rut, nombre, nombreUsuario, contrasena);
                String contenido = cajero.toString();
                gestorArchivo.nuevaLinea("ArchivosBD/usuarios.txt",contenido);
            }
        }
        /*
        for (Usuario usuario : usuarios){
            String nombre = usuario.getNombre();
            String nombreUsuario = usuario.getNombreUsuario();
            String rut = usuario.getRut();
            String contraseña = usuario.getContrasena();
            usuario.getClass();
            if (usuario.getClass().equals("Admin")){
                Usuario admin = new Admin(rut,nombre,nombreUsuario,contraseña);
                String contenido = admin.toString();
                gestorArchivo.crearArchivo("ArchivosBD/usuarios.txt",contenido);
            }else if (usuario.getClass().equals("Cajero")){
                Usuario cajero = new Cajero(rut,nombre,nombreUsuario,contraseña);
                String contenido = cajero.toString();
                gestorArchivo.crearArchivo("ArchivosBD/usuarios.txt",contenido);
            }
            System.out.println(usuarios);
        }*/

    }

}

