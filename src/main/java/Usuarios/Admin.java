package Usuarios;

import BaseDeDatos.GestorArchivo;
import Productos.*;
import Sesion.Sesion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Admin extends Usuario {
    private int idActual = 1;
    private static Scanner teclado =  new Scanner(System.in);

    //private ArrayList<Usuario> usuarios = new ArrayList<>();
    List<Usuario> usuariosTxt = new ArrayList<>();

    private ArrayList<Producto> productos = new ArrayList<>();
    public Admin() {
    }
    public Admin(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
        this.permiso=1; //1 es todos los permisos de admin
    }

    public void iniciarMenuPrincipalAdmin() {
        int opcion = -1;
        do{
            try{
                System.out.println("\n--- Bienvenido al menu principal ---" +
                        "\n¿Que desea realizar? Ingrese una opcion: " +
                        "\n(1)-> Administrar Usuarios" +
                        "\n(2)-> Administrar Productos" +
                        "\n(3)-> Cerrar Sesion");
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        menuAdministrarUsuario();
                        break;
                    case 2:
                        menuAdministrarProducto();
                        break;
                    case 3:
                        Sesion sesionNueva = new Sesion();
                        sesionNueva.iniciarSesion();
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            }catch(InputMismatchException e){
                System.out.println("Error al seleccionar opcion");
                teclado.next();
            }
        }while(opcion!=3);
    }


    private void menuAdministrarUsuario() { //ESTO NO VA EN SESION, VA EN ADMIN

        int opcion = -1;


        do{
            try{
                System.out.println("\nBienvenido al Menu de Administracion de Usuarios");
                System.out.println("Que desea realizar: ");
                System.out.println("1.- Crear Nuevo Cajero");
                System.out.println("2.- Crear Nuevo Admin");
                System.out.println("3.- Editar usuario");
                System.out.println("4.- Eliminar usuario");
                System.out.println("0.- Atras");

                opcion = teclado.nextInt();


                switch (opcion) {
                    case 1:
                        registrarNuevoCajero();
                        break;
                    case 2:
                        registrarNuevoAdmin();
                        break;
                    case 3:
                        //Pendiente hasta la implementacion de BD
                        editarUsuario();
                        break;
                    case 4:
                        //Pendiente hasta la implementacion de BD
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");

                }
            }catch (InputMismatchException e){
                System.out.println("Error al seleccionar opcion");
                teclado.next();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }while(opcion!=0);
    }

    public void editarUsuario() throws IOException {
        mostrarUsuarios();

        Path path = Paths.get("ArchivosBD/admins.txt");
        try {
            ArrayList<String> lineas = (ArrayList<String>) Files.readAllLines(path);

            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i).equals("Admin")) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String rut = lineas.get(i + 2).substring(5);
                    String nombreUsuario = lineas.get(i + 3).substring(19);
                    String contrasena = lineas.get(i + 4).substring(12);
                    usuariosTxt.add(new Admin(rut, nombre, nombreUsuario, contrasena));
                } else if (lineas.get(i).equals("Cajero")) {
                    String nombre = lineas.get(i + 1).substring(8);
                    String rut = lineas.get(i + 2).substring(5);
                    String nombreUsuario = lineas.get(i + 3).substring(19);
                    String contrasena = lineas.get(i + 4).substring(12);
                    usuariosTxt.add(new Cajero(rut,nombre, nombreUsuario, contrasena));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mostrarUsuarios(){
        GestorArchivo gestorArchivo = new GestorArchivo();
        System.out.println(gestorArchivo.leerArchivo("ArchivosBD/admins.txt"));

    }

    private void menuAdministrarProducto() { //ESTO NO VA EN SESION, VA EN ADMIN

        int opcion = -1;

        do{
            try{
                System.out.println("\nQue desea realizar");
                System.out.println("1.- Registrar Nuevo Producto");
                System.out.println("2.- Modificar Producto");
                System.out.println("3.- Eliminar Producto");
                System.out.println("0.- Atras");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        registrarNuevoProducto();
                        break;
                    case 2:
                        //Pendiente hasta la implementacion de BD
                        break;
                    case 3:
                        //Pendiente hasta la implementacion de BD
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            }catch (InputMismatchException e){
                System.out.println("\n Ingrese una opcion valida");
                teclado.next();
            }
        }while(opcion!=0);

    }

    public void registrarNuevoProducto() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nQue tipo de producto desea registrar: ");
        System.out.println("1- Fruta");
        System.out.println("2- Pan");
        System.out.println("3- Bebida");
        System.out.println("4- Abarrote");
        System.out.println("5- Snack");
        int opcion = teclado.nextInt();

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = teclado.next();
        System.out.print("Ingrese el valor que tendrá el producto: ");
        int valor = teclado.nextInt();
        System.out.print("Ingrese el stock inicial del producto: ");
        int stock = teclado.nextInt();
        do{
            try{
                switch (opcion){
                    case 1:
                        Producto fruta = new Fruta(nombre,valor,stock);
                        productos.add(fruta);
                        System.out.println("Fruta registrada");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 2:
                        Producto pan = new Pan(nombre,valor,stock);
                        productos.add(pan);
                        System.out.println("Pan registrado");
                        iniciarMenuPrincipalAdmin();

                        break;
                    case 3:
                        Producto bebida = new Bebida(nombre,valor,stock);
                        productos.add(bebida);
                        System.out.println("Bebida registrada");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 4:
                        Producto snack = new Snack(nombre,valor,stock);
                        productos.add(snack);
                        System.out.println("Snack registrado");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 5:
                        Producto congelado = new Congelado(nombre,valor,stock);
                        productos.add(congelado);
                        System.out.println("Congelado registrado");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 6:
                        Producto abarrote = new Abarrote(nombre,valor,stock);
                        productos.add(abarrote);
                        System.out.println("Abarrote registrado");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("Ingrese una opcion valida");
                teclado.next();
            }
        }while (opcion!=0);
    }


    public void registrarNuevoCajero(){
        Scanner registrarNombre = new Scanner(System.in);
        System.out.println("Ingrese en nombre del nuevo Cajero");
        String nombre = registrarNombre.nextLine();
        System.out.println("Ingrese el rut del nuevo Cajero");
        String rut = teclado.next();
        System.out.println("Ingrese el nombre de usuario del nuevo Cajero");
        String nombreUsuario = teclado.next();
        System.out.println("Ingrese la contraseña del nuevo Cajero");
        String contrasena = teclado.next();
        Usuario cajero = new Cajero(rut,nombre,nombreUsuario,contrasena);

        String contenido = cajero.toString();
        GestorArchivo gestorArchivo = new GestorArchivo();
        gestorArchivo.nuevaLinea("ArchivosBD/admins.txt",contenido);

    }

    public void registrarNuevoAdmin() {
        Scanner registrarNombre = new Scanner(System.in);
        System.out.println("Ingrese en nombre del nuevo Admin");
        String nombre = registrarNombre.nextLine();
        String rut;
        do {
            System.out.println("Ingrese el rut del nuevo Admin");
            rut = teclado.next();
        }while(validarRut(rut)!=true);


        System.out.println("Ingrese el nombre de usuario del nuevo Admin");
        String nombreUsuario = teclado.next();
        System.out.println("Ingrese la contraseña del nuevo Admin");
        String contrasena = teclado.next();
        Usuario admin = new Admin(rut,nombre,nombreUsuario,contrasena);
        String contenido = admin.toString();

        GestorArchivo gestorArchivo = new GestorArchivo();
        gestorArchivo.nuevaLinea("ArchivosBD/admins.txt",contenido);
        //gestorArchivo.crearArchivo("ArchivosBD/admins.txt",contenido);
    }


    public boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (Exception e) {
            System.out.println("Rut no valido " + e.getMessage());
        }
        return validacion;
    }



    @Override
    public String toString() {
        return "\nAdmin" +
                "\nNombre: "+ getNombre() +
                "\nRut: "+getRut() +
                "\nNombre de Usuario: "+getNombreUsuario() +
                "\nContraseña: "+getContrasena();
    }
}
