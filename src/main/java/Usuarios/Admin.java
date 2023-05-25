package Usuarios;
import BaseDeDatos.GestorArchivo;
import BaseDeDatos.GestorBaseDatos;
import Productos.*;
import Sesion.Sesion;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Admin extends Usuario {
    private static Scanner teclado =  new Scanner(System.in);
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Usuario> usuariosParaEditar = new ArrayList<>();
    public int codigoActual;
    public Admin() {
    }
    public Admin(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
    }
    public void iniciarMenuPrincipalAdmin() {
        usuariosParaEditar.clear();
        GestorBaseDatos.cargarDatosUsuarios(usuariosParaEditar);
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
                        sesionNueva.primerInicioSesion();
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
                System.out.println("\nBienvenido al Menu de Administracion de Usuarios"+
                "\n---- Que desea realizar: ----"+
                "\n(1)-> Crear Nuevo Cajero"+
                "\n(2)-> Crear Nuevo Admin"+
                "\n(3)-> Editar usuario"+
                "\n(4)-> Eliminar usuario"+
                "\n(0)-> Atras");

                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        registrarNuevoCajero();
                        break;
                    case 2:
                        registrarNuevoAdmin();
                        break;
                    case 3:
                        buscarUsuarioAEditar();
                        break;
                    case 4:
                        buscarUsuarioAEliminar();
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
    public void buscarUsuarioAEliminar() {
        mostrarUsuarios();
        System.out.print("Ingrese el rut del usuario a eliminar: ");
        String rutIngresado = teclado.next();
        eliminarUsurio(encontrarUsuarioPorRut(usuariosParaEditar,rutIngresado));
    }
    public void buscarUsuarioAEditar() {
        System.out.println(usuariosParaEditar);
        System.out.print("Ingrese el rut del usuario a editar: ");
        String rutIngresado = teclado.next();
        menuEditarUsuario(encontrarUsuarioPorRut(usuariosParaEditar,rutIngresado));
    }
    public Usuario encontrarUsuarioPorRut(ArrayList<Usuario> usuarios,String rut){
        for  (Usuario u: usuarios) {
            if (u.getRut().equals(rut)) {
                return u;
            }
        }
        return null;
    }
    public void eliminarUsurio(Usuario u){
        System.out.println(u);
        System.out.println("Estas seguro si deseas eliminar este usuario?"+
                "\n(1)-> Si\n(2)-> No");
        int opcion = teclado.nextInt();
        try{
            if (opcion==1){
                usuariosParaEditar.remove(u);
                System.out.println(usuariosParaEditar);
                GestorBaseDatos.guardarCambiosUsuarios(usuariosParaEditar);
            }else if (opcion==2){
                menuAdministrarUsuario();
            }else {
                System.out.println("Ingrese una opcion valida");
            }
        }catch (InputMismatchException e){
            System.out.println("Por favor ingrese un numero");
        }
    }
    public void menuEditarUsuario(Usuario u){
        int opcion = -1;
        do {
            try{
                System.out.println("--- Que desea editar?---"+
                "(1)-> Nombre"+
                "(2)-> Nombre de Usuario"+
                "(3)-> Contraseña"+
                "(0)-> Atras");

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
                    case 0:
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Error al seleccionar opcion");
                teclado.next();
            }
        }while (opcion!=0);
    }
    public void editarNombre(Usuario u){
        Scanner registrarNombre = new Scanner(System.in);
        System.out.println("Nombre actual: "+ u.getNombre()
                + "\nIngrese el nuevo nombre: ");
        String nuevoNombre = registrarNombre.nextLine();
        u.setNombre(nuevoNombre);
        System.out.println("Se modifico correctamente el nombre");
        GestorBaseDatos.guardarCambiosUsuarios(usuariosParaEditar);

    }
    public void editarNombreUsuario(Usuario u){
        Scanner registrarNombreUsuario = new Scanner(System.in);

        System.out.println("Nombre de usuario actual: "+ u.getNombreUsuario()+
                "\nIngrese el nuevo nombre de usuario: ");
        String nuevoNombreUsuario = registrarNombreUsuario.next();
        u.setNombreUsuario(nuevoNombreUsuario);
        System.out.println("Se modifico correctamente el nombre de usuario");
        GestorBaseDatos.guardarCambiosUsuarios(usuariosParaEditar);

    }
    public void editarContrasena(Usuario u){
        Scanner registrarContra = new Scanner(System.in);

        System.out.println("Contraseña actual: "+ u.getContrasena()+
                "\nIngrese la nueva contrasena: ");
        String nuevaContra = registrarContra.next();
        u.setContrasena(nuevaContra);
        System.out.println("Se modifico correctamente la contraseña");
        GestorBaseDatos.guardarCambiosUsuarios(usuariosParaEditar);
    }
    public void mostrarUsuarios(){
        System.out.println(GestorArchivo.leerArchivo("ArchivosBD/usuarios.txt"));
    }
    private void menuAdministrarProducto() {
        GestorBaseDatos.cargarDatosProductos(productos,"Fruta");
        GestorBaseDatos.cargarDatosProductos(productos,"Pan");
        GestorBaseDatos.cargarDatosProductos(productos,"Snack");
        GestorBaseDatos.cargarDatosProductos(productos,"Bebida");
        GestorBaseDatos.cargarDatosProductos(productos,"Congelado");
        GestorBaseDatos.cargarDatosProductos(productos,"Abarrote");
        codigoActual = GestorBaseDatos.cargarCodigo();
        System.out.println(productos.get(1));

        int opcion = -1;

        do{
            try{
                System.out.println("\nQue desea realizar"+
                "(1)-> Registrar Nuevo Producto"+
                "(2)-> Modificar Producto"+
                "(3)-> Eliminar Producto"+
                "(0)-> Atras");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        registrarNuevoProducto();
                        break;
                    case 2:
                        //Pendiente su implementacion
                        break;
                    case 3:
                        //Pendiente su implementacion
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
        Scanner registroMarca = new Scanner(System.in);
        System.out.println(codigoActual);

        System.out.println(productos);

        Scanner registro = new Scanner(System.in);

        System.out.println("\nQue tipo de producto desea registrar: "+
                "(1)-> Fruta"+
                "(2)-> Pan"+
                "(3)-> Bebida"+
                "(4)-> Snack"+
                "(5)-> Congelado"+
                "(6)-> Abarrote");
        int opcion = teclado.nextInt();

        int codigo = codigoActual;
        codigo++;

        System.out.println("Codigo AUTO: " + codigo);

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = registro.nextLine();
        System.out.print("Valor por peso (unidad con peso definido).\nIngrese el valor que tendrá el producto: ");
        int valor = teclado.nextInt();
        System.out.print("Stock por unidad o peso.\nIngrese el stock inicial del producto: ");
        int stock = teclado.nextInt();
        String codigoString = "Codigo\nCodigo: " + codigo;

        do{
            try{
                switch (opcion){
                    case 1:
                        Producto fruta = new Fruta(nombre,valor,stock,codigo);
                        String frutaString = fruta.toString();
                        GestorArchivo.nuevaLinea("ArchivosBD/productos.txt",frutaString);
                        GestorArchivo.escribirArchivo("ArchivosBD/codigo.txt",codigoString);
                        System.out.println("Fruta registrada");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 2:
                        Producto pan = new Pan(nombre,valor,stock,codigo);
                        String panString = pan.toString();
                        GestorArchivo.nuevaLinea("ArchivosBD/productos.txt",panString);
                        GestorArchivo.escribirArchivo("ArchivosBD/codigo.txt",codigoString);
                        System.out.println("Pan registrado");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 3:
                        System.out.print("Ingrese el peso en litros de la bebida: ");
                        double pesoLitros = teclado.nextDouble();
                        Producto bebida = new Bebida(nombre,valor,stock,codigo,pesoLitros);
                        String bebidaString = bebida.toString();
                        GestorArchivo.nuevaLinea("ArchivosBD/productos.txt",bebidaString);
                        GestorArchivo.escribirArchivo("ArchivosBD/codigo.txt",codigoString);
                        System.out.println("Bebida registrada");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 4:
                        System.out.print("Ingrese la marca del Snack:");
                        String marcaSnack = registroMarca.nextLine();
                        Producto snack = new Snack(nombre,valor,stock,marcaSnack,codigo);
                        String snackString = snack.toString();
                        GestorArchivo.nuevaLinea("ArchivosBD/productos.txt",snackString);
                        GestorArchivo.escribirArchivo("ArchivosBD/codigo.txt",codigoString);
                        System.out.println("Snack registrado");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 5:
                        System.out.print("Ingrese la marca del Congelado");
                        String marcaCongelado = registroMarca.nextLine();
                        Producto congelado = new Congelado(nombre,valor,stock,marcaCongelado,codigo);
                        String congeladoString = congelado.toString();
                        GestorArchivo.nuevaLinea("ArchivosBD/productos.txt",congeladoString);
                        GestorArchivo.escribirArchivo("ArchivosBD/codigo.txt",codigoString);
                        System.out.println("Congelado registrado");
                        iniciarMenuPrincipalAdmin();
                        break;
                    case 6:
                        System.out.print("Ingrese la marca del Abarrote");
                        String marcaAbarrote = registroMarca.nextLine();
                        Producto abarrote = new Abarrote(nombre,valor,stock,codigo,marcaAbarrote);
                        String abarroteString = abarrote.toString();
                        GestorArchivo.nuevaLinea("ArchivosBD/productos.txt",abarroteString);
                        GestorArchivo.escribirArchivo("ArchivosBD/codigo.txt",codigoString);
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
            System.out.print("\nRUT con puntos y guion. Ej: 12.345.678-9"+
                    "\nIngrese el rut del nuevo Cliente: ");
            rut = teclado.next();
        }while(validarRut(rut)!=true);

        System.out.println("Ingrese el nombre de usuario del nuevo Cajero");
        String nombreUsuario = teclado.next();
        System.out.println("Ingrese la contraseña del nuevo Cajero");
        String contrasena = teclado.next();
        Usuario cajero = new Cajero(rut,nombre,nombreUsuario,contrasena);
        usuariosParaEditar.add(cajero);

        String contenido = cajero.toString();
        GestorArchivo.nuevaLinea("ArchivosBD/usuarios.txt",contenido);
    }
    public void registrarNuevoAdmin() {
        Scanner registrarNombre = new Scanner(System.in);
        System.out.print("\nIngrese en nombre del nuevo Admin");
        String nombre = registrarNombre.nextLine();
        String rut;
        do {
            System.out.println("\nRUT con puntos y guion. Ej: 12.345.678-9"+
                    "\nIngrese el rut del nuevo Admin");
            rut = teclado.next();
        }while(validarRut(rut)!=true);

        System.out.println("Ingrese el nombre de usuario del nuevo Admin");
        String nombreUsuario = teclado.next();
        System.out.println("Ingrese la contraseña del nuevo Admin");
        String contrasena = teclado.next();
        Usuario admin = new Admin(rut,nombre,nombreUsuario,contrasena);
        usuariosParaEditar.add(admin);

        String contenido = admin.toString();
        GestorArchivo.nuevaLinea("ArchivosBD/usuarios.txt",contenido);
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
    @Override
    public String toString() {
        return "\nAdmin" +
                "\nNombre: "+ getNombre() +
                "\nRut: "+getRut() +
                "\nNombre de Usuario: "+getNombreUsuario() +
                "\nContraseña: "+getContrasena();
    }
}
