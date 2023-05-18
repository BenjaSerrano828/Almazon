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
                    String contrasenaDefinitiva = contrasena.substring(0,contrasena.length()-2);
                    usuarios.add(new Admin(rut, nombre, nombreUsuario, contrasenaDefinitiva));
                } else if (lineas.get(i).equals("Cajero")) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String rut = lineas.get(i + 2).substring(5);
                    String nombreUsuario = lineas.get(i + 3).substring(19);
                    String contrasena = lineas.get(i + 4).substring(12);
                    String contrasenaDefinitiva = contrasena.substring(0,contrasena.length()-2);
                    usuarios.add(new Cajero(rut, nombre, nombreUsuario, contrasenaDefinitiva));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                    /*
                    switch (producto){
                        case "Fruta":
                            productos.add(new Fruta(nombre, valorInt, stockInt, codigoInt));
                        case "Abarrote":
                    }*/
                    if (producto.equals("Fruta")){
                        productos.add(new Fruta(nombre, valorInt, stockInt, codigoInt));
                    }else if (producto.equals("Pan")){
                        productos.add(new Pan(nombre, valorInt, stockInt, codigoInt));
                    }else if (producto.equals("Abarrote")){
                        productos.add(new Abarrote(nombre, valorInt, stockInt, codigoInt));
                    }else if (producto.equals("Bebida")){
                        String litros = lineas.get(i + 5).substring(8);
                        double litrosDouble = Double.parseDouble(litros);
                        productos.add(new Bebida(nombre, valorInt, stockInt, codigoInt,litrosDouble));
                    }else if (producto.equals("Congelado")){
                        productos.add(new Congelado(nombre, valorInt, stockInt, codigoInt));
                    }else if (producto.equals("Snack")){
                        productos.add(new Snack(nombre, valorInt, stockInt, codigoInt));
                    }
                }
            }
        } catch(IOException e){
            System.out.println("ERROR");
        }
    }
}

