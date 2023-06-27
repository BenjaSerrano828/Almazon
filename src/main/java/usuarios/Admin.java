package usuarios;
import baseDeDatos.GestorArchivo;
import baseDeDatos.GestorBaseDatos;
import productos.*;
import sesion.Sesion;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Admin extends Usuario {
    private static Scanner teclado =  new Scanner(System.in);
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Usuario> usuariosParaEditar = new ArrayList<>();
    private int codigoActual;
    public Admin() {
    }
    public Admin(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
    }
    public void iniciarMenuPrincipalAdmin() {
        recargarDatosUsuarios();
        int opcion = -1;
        do{
            try{
                System.out.println("\n--- Bienvenido al menu principal ---\n¿Que desea realizar? Ingrese una opcion:\n(1)-> Administrar Usuarios\n(2)-> Administrar Productos\n(3)-> Cerrar Sesion");
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
                        System.err.println("\nIngrese una opcion valida");
                }
            }catch(InputMismatchException e){
                System.err.println("\nError al seleccionar opcion");
                teclado.next();
            }
        }while(opcion!=3);
    }
    private void menuAdministrarUsuario() {
        int opcion = -1;
        do{
            try{
                System.out.println("\nBienvenido al Menu de Administracion de Usuarios\n---- Que desea realizar: ----\n(1)-> Crear Nuevo Usuario\n(2)-> Editar usuario\n(3)-> Eliminar usuario\n(0)-> Atras");
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        obtenerDatosNuevoUsuario();
                        break;
                    case 2:
                        buscarUsuarioAEditar();
                        break;
                    case 3:
                        buscarUsuarioAEliminar();
                        break;
                    case 0:
                        iniciarMenuPrincipalAdmin();
                        break;
                    default:
                        System.err.println("\nIngrese una opcion valida");
                }
            }catch (InputMismatchException e){
                System.err.println("\nError al seleccionar opcion");
                teclado.next();
            }
        }while(opcion!=0);
    }
    public void buscarUsuarioAEliminar() {
        System.out.println(usuariosParaEditar);
        System.out.print("Ingrese el rut del usuario a eliminar: ");
        String rutIngresado = teclado.next();
        eliminarUsuario(encontrarUsuarioPorRut(usuariosParaEditar,rutIngresado));
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
    public void eliminarUsuario(Usuario u){
        System.out.println(u);
        System.out.println("Estas seguro si deseas eliminar este usuario?\n(1)-> Si\n(2)-> No");
        int opcion = teclado.nextInt();
        try{
            if (opcion==1){
                usuariosParaEditar.remove(u);
                System.out.println(usuariosParaEditar);
                GestorBaseDatos.guardarCambiosUsuarios(usuariosParaEditar);
            }else if (opcion==2){
                menuAdministrarUsuario();
            }else {
                System.err.println("\nIngrese una opcion valida");
            }
        }catch (InputMismatchException e){
            System.err.println("\nPor favor ingrese un numero");
        }
    }
    public void menuEditarUsuario(Usuario u){
        int opcion = -1;
        do {
            try{
                System.out.println("--- Que desea editar?---\n(1)-> Nombre\n(2)-> Nombre de Usuario\n(3)-> Contraseña\n(0)-> Atras");
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
                    default:
                        System.err.println("\nIngrese una opcion valida");
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
    private void menuAdministrarProducto() {
        GestorBaseDatos.cargarDatosProductos(productos,"Fruta");
        GestorBaseDatos.cargarDatosProductos(productos,"Pan");
        GestorBaseDatos.cargarDatosProductos(productos,"Snack");
        GestorBaseDatos.cargarDatosProductos(productos,"Bebida");
        GestorBaseDatos.cargarDatosProductos(productos,"Congelado");
        GestorBaseDatos.cargarDatosProductos(productos,"Abarrote");
        codigoActual = GestorBaseDatos.cargarCodigo();
        int opcion = -1;
        do{
            try{
                System.out.println("\nQue desea realizar\n(1)-> Registrar Nuevo Producto\n(2)-> Modificar Producto\n(3)-> Eliminar Producto\n(0)-> Atras");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        obtenerValoresNuevoProducto();
                        break;
                    case 2:
                        //Pendiente su implementacion
                        break;
                    case 3:
                        //Pendiente su implementacion
                        break;
                    case 0:
                        iniciarMenuPrincipalAdmin();
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
    public void obtenerValoresNuevoProducto(){
        Scanner registro = new Scanner(System.in);
        int opcion = obtenerOpcionRegistrarNuevoProducto();
        String nombre = obtenerNombreProducto(registro);
        int valor = obtenerValorProducto();
        int stock = obtenerStockProducto(registro);
        int codigo = codigoActual;
        codigo++;

        double pesoLitros=0;
        if (opcion==3){
            pesoLitros = obtenerPesoLitrosProducto(registro);
        }
        String marca="";
        if (opcion == 4 || opcion == 5 || opcion == 6){
            marca = obtenerMarcaProduto(registro);
        }

        crearNuevoProducto(nombre,codigo,valor,stock,opcion,pesoLitros,marca);
        menuAdministrarProducto();
    }
    public Producto crearNuevoProducto(String nombre,int codigo,int valor, int stock, int opcion, double pesoLitros, String marca) {
        do {
            try {
                switch (opcion) {
                    case 1:
                        Producto fruta = new Fruta(nombre, valor, stock, codigo);
                        guardarProducto(fruta);
                        guardarCodigo(codigo);
                        //iniciarMenuPrincipalAdmin();
                        return fruta;
                    case 2:
                        Producto pan = new Pan(nombre, valor, stock, codigo);
                        guardarProducto(pan);
                        guardarCodigo(codigo);
                        //iniciarMenuPrincipalAdmin();
                        return pan;
                    case 3:
                        Producto bebida = new Bebida(nombre, valor, stock, codigo, pesoLitros);
                        guardarProducto(bebida);
                        guardarCodigo(codigo);
                        //iniciarMenuPrincipalAdmin();
                        return bebida;
                    case 4:
                        Producto snack = new Snack(nombre, valor, stock, marca, codigo);
                        guardarProducto(snack);
                        guardarCodigo(codigo);
                        //iniciarMenuPrincipalAdmin();
                        return snack;
                    case 5:
                        Producto congelado = new Congelado(nombre, valor, stock, marca, codigo);
                        guardarProducto(congelado);
                        guardarCodigo(codigo);
                        //iniciarMenuPrincipalAdmin();
                        return congelado;
                    case 6:
                        Producto abarrote = new Abarrote(nombre, valor, stock, codigo, marca);
                        guardarProducto(abarrote);
                        guardarCodigo(codigo);
                        //iniciarMenuPrincipalAdmin();
                        return abarrote;
                    case 0:
                        break;
                    default:
                        System.err.println("\nError: Ingrese una opción válida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("\nError: Ingrese una opción válida");
                teclado.next();
            }
        } while (opcion != 0);
        return null;
    }

    private double obtenerPesoLitrosProducto(Scanner registro){
        System.out.print("Ingrese el peso en litros de la bebida: ");
        return registro.nextDouble();
    }
    private String obtenerMarcaProduto(Scanner registro){
        System.out.print("Ingrese la marca del Snack: ");
        return registro.nextLine();
    }
    private int obtenerOpcionRegistrarNuevoProducto(){
        System.out.println("\nQué tipo de producto desea registrar:\n(1)-> Fruta\n(2)-> Pan\n(3)-> Bebida\n(4)-> Snack\n(5)-> Congelado\n(6)-> Abarrote");
        return teclado.nextInt();
    }
    private String obtenerNombreProducto(Scanner registro) {
        System.out.print("Ingrese el nombre del producto: ");
        return registro.nextLine();
    }

    private int obtenerValorProducto() {
        System.out.print("Valor por peso (unidad con peso definido).\nIngrese el valor que tendrá el producto: ");
        return teclado.nextInt();
    }

    private int obtenerStockProducto(Scanner registro) {
        System.out.print("Stock por unidad o peso.\nIngrese el stock inicial del producto: ");
        return registro.nextInt();
    }

    private void guardarProducto(Producto producto) {
        String productoString = producto.toString();
        GestorArchivo.nuevaLinea("ArchivosBD/productos.txt", productoString);
        System.out.println(producto.getNombre()+" registrado/a");
    }
    private void guardarCodigo(int codigo) {
        String codigoString = "Codigo\nCodigo: " + codigo;
        GestorArchivo.escribirArchivo("ArchivosBD/codigo.txt", codigoString);
    }

    public void guardarUsuario(Usuario u){
        usuariosParaEditar.add(u);
        String contenido = u.toString();
        GestorArchivo.nuevaLinea("ArchivosBD/usuarios.txt",contenido);
    }


    public void obtenerDatosNuevoUsuario(){
        Scanner registro = new Scanner(System.in);
        String rut = obtenerNuevoRut();

        if(!validarRut(rut)){
            obtenerDatosNuevoUsuario();
        }

        int opcion = obtenerNuevoTipoUsuario();
        String nombre = obtenerNuevoNombre(registro);

        String nombreUsuario = obtenerNuevoNombreUsuario(registro);
        String contrasena = obtenerNuevaContrasena();
        registrarNuevoUsuario(opcion,nombre,nombreUsuario,rut,contrasena);
    }


    public int obtenerNuevoTipoUsuario(){
        System.out.println("Que tipo de Usuario deseas registrar: \n(1)-> Cajero\n(2)-> Admin");
        return teclado.nextInt();
    }
    public String obtenerNuevoNombre(Scanner registro){
        System.out.print("\nIngrese en nombre del nuevo Usuario: ");
        return registro.nextLine();
    }
    public String obtenerNuevoNombreUsuario(Scanner registro){
        System.out.println("Ingrese el nombre de usuario del nuevo Usuario: ");
        return registro.next();
    }

    public String obtenerNuevaContrasena(){
        System.out.println("Ingrese la contraseña del nuevo Usuario: ");
        return teclado.next();
    }

    public String obtenerNuevoRut(){
        System.out.println("RUT con puntos y guion. Ej: 12.345.678-9\nIngrese el rut del nuevo Usuario: ");
        return teclado.next();
    }

    public Usuario registrarNuevoUsuario(int opcion, String nombre, String nombreUsuario, String rut, String contrasena) {

        switch (opcion){
            case 1:
                Cajero cajero = new Cajero(rut,nombre,nombreUsuario,contrasena);
                guardarUsuario(cajero);

                return cajero;
            case 2:
                Admin admin = new Admin(rut,nombre,nombreUsuario,contrasena);
                guardarUsuario(admin);
                return admin;
        }
        return null;
    }
    public boolean validarRut(String rut) {
        boolean validar = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char digitoVerificador = rut.charAt(rut.length() - 1);
            int m = 0;
            int s = 1;
            for (; rutAux!=0; rutAux/=10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (digitoVerificador == (char) (s != 0 ? s + 47 : 75)) {
                validar = true;
            }
        } catch (Exception e) {
            System.err.println("\nRut no valido " + e.getMessage());
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
    private void recargarDatosUsuarios() {
        usuariosParaEditar.clear();
        GestorBaseDatos.cargarDatosUsuarios(usuariosParaEditar);
    }
}
