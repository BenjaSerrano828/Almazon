package Usuarios;

import BaseDeDatos.GestorArchivo;
import BaseDeDatos.GestorBaseDatos;
import Productos.*;
import Sesion.Sesion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Usuario {
    private static Scanner teclado =  new Scanner(System.in);
    private ArrayList<Producto> productos = new ArrayList<>();

    private ArrayList<Usuario> usuariosParaEditar = new ArrayList<>();


    public Admin() {
    }
    public Admin(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
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


    private void menuAdministrarUsuario() {

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
                        buscarUsuarioAEditar();
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
            }

        }while(opcion!=0);
    }
    public void buscarUsuarioAEditar() {
        mostrarUsuarios();
        GestorBaseDatos.cargarDatosUsuarios(usuariosParaEditar);

        System.out.println(usuariosParaEditar.get(1));

        System.out.print("Ingrese el rut del usuario a editar: ");
        String rutIngresado = teclado.next();

        editarUsuario(encontrarUsuarioPorRut(usuariosParaEditar,rutIngresado));

    }

    public Usuario encontrarUsuarioPorRut(ArrayList<Usuario> usuarios,String rut){
        for  (Usuario u: usuarios) {
            if (u.getRut().equals(rut)) {
                return u;
            }
        }
        return null;
    }

    public void editarUsuario(Usuario u){
        int opcion = -1;
        do {
            try{
                System.out.println("--- Que desea editar?---");
                System.out.println("1.- Nombre");
                System.out.println("2.- Nombre de Usuario");
                System.out.println("3.- Contraseña");
                System.out.println("4.- Guardar");
                System.out.println("0.- Atras");

                opcion = teclado.nextInt();

                switch (opcion){
                    case 1:
                        editarNombre(u);
                        break;
                    case 2:
                        editarNombreUsuario(u);
                        break;
                    case 3:
                        editarContrasena(u);
                        break;
                    case 4:
                        guardarCambios();
                    case 0:
                        break;
                }

            }catch (InputMismatchException e){
                System.out.println("Error al seleccionar opcion");
                teclado.next();
            }
        }while (opcion!=0);

    }

    public void guardarCambios() {

        GestorArchivo gestorArchivo = new GestorArchivo();
        String contenido = usuariosParaEditar.toString();
        gestorArchivo.crearArchivo("ArchivosBD/usuarios.txt",contenido);
    }

    public void editarNombre(Usuario u){
        Scanner registrarNombre = new Scanner(System.in);
        System.out.println("Nombre actual: "+ u.getNombre());
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = registrarNombre.nextLine();
        u.setNombre(nuevoNombre);
        System.out.println("Se modifico correctamente el nombre");

    }
    public void editarNombreUsuario(Usuario u){
        Scanner registrarNombreUsuario = new Scanner(System.in);

        System.out.println("Nombre de usuario actual: "+ u.getNombreUsuario());
        System.out.print("Ingrese el nuevo nombre de usuario: ");
        String nuevoNombreUsuario = registrarNombreUsuario.next();
        u.setNombreUsuario(nuevoNombreUsuario);
        System.out.println("Se modifico correctamente el nombre de usuario");
    }
    public void editarContrasena(Usuario u){
        Scanner registrarContra = new Scanner(System.in);

        System.out.println("Contraseña actual: "+ u.getContrasena());
        System.out.print("Ingrese la nueva contrasena: ");
        String nuevaContra = registrarContra.next();
        u.setContrasena(nuevaContra);
        System.out.println("Se modifico correctamente la contraseña");
    }


    public void mostrarUsuarios(){
        GestorArchivo gestorArchivo = new GestorArchivo();
        System.out.println(gestorArchivo.leerArchivo("ArchivosBD/usuarios.txt"));

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
        System.out.println(productos);

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
                        String frutaString = fruta.toString();
                        GestorArchivo gestorArchivo = new GestorArchivo();
                        gestorArchivo.nuevaLinea("ArchivosBD/productos.txt",frutaString);

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
        String rut;
        do {
            System.out.println("RUT con puntos y guion. Ej: 12.345.678-9");
            System.out.println("Ingrese el rut del nuevo Cliente");
            rut = teclado.next();
        }while(validarRut(rut)!=true);

        System.out.println("Ingrese el nombre de usuario del nuevo Cajero");
        String nombreUsuario = teclado.next();
        System.out.println("Ingrese la contraseña del nuevo Cajero");
        String contrasena = teclado.next();
        Usuario cajero = new Cajero(rut,nombre,nombreUsuario,contrasena);

        String contenido = cajero.toString();
        GestorArchivo gestorArchivo = new GestorArchivo();
        gestorArchivo.nuevaLinea("ArchivosBD/usuarios.txt",contenido);
    }

    public void registrarNuevoAdmin() {
        Scanner registrarNombre = new Scanner(System.in);
        System.out.println("Ingrese en nombre del nuevo Admin");
        String nombre = registrarNombre.nextLine();
        String rut;
        do {
            System.out.println("RUT con puntos y guion. Ej: 12.345.678-9");
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
        gestorArchivo.nuevaLinea("ArchivosBD/usuarios.txt",contenido);
        //gestorArchivo.crearArchivo("ArchivosBD/admins.txt",contenido);
    }


    public boolean validarRut(String rut) {
        boolean validar = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char digitoVerificador = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (digitoVerificador == (char) (s != 0 ? s + 47 : 75)) {
                validar = true;
            }

        } catch (Exception e) {
            System.out.println("Rut no valido " + e.getMessage());
        }
        return validar;
    }

    public void cargarDatosProductos(String producto){
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
                    productos.add(new Fruta(nombre,valorInt,stockInt,codigoInt));
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
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
