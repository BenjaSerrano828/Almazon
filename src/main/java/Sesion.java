import java.util.Scanner;

public class Sesion {


    Usuario adminSupremo = new Admin("rut del dueño", "admin", "admin", "12345admin");
    Scanner teclado = new Scanner(System.in);

    public Sesion() {
        iniciarSesion();
    }

    public void iniciarSesion() {
        //Ingrese su nombre de usuario
        //ingrese su contraseña
        //segun tipo de usuario se abre un menu distinto
        autenticarUsuario(); //validar cuando no se cumple
        direccionarMenu();
        //menu de admin
        //menu de cajero
        //iniciarMenuPrincipalAdmin();
        iniciarMenuPrincipalCajero();
    }

    public void direccionarMenu() {
        // if permiso = 1 -> menu de admin
        //else -> menu de cajero

    }

    public boolean autenticarUsuario() {
        //if bla bla bla
        return true;
    }


    private void iniciarMenuPrincipalCajero() {
    }

    private void iniciarMenuPrincipalAdmin(Admin admin) {


        //menu de admin
        System.out.println("--- Bienvenido al menu principal ---" +
                "\n¿Que desea realizar? Ingrese una opcion: " +
                "\n(1)-> Administrar Usuarios" +
                "\n(2)-> Administrar Productos" +
                "\n(3)-> Cerrar Sesion");
        seleccionarOpcion();
    }

    private void seleccionarOpcion() {
        int opcion = teclado.nextInt();
        switch (opcion) {
            case 1:
                menuAdministrarUsuario();
                break;
            case 2:
                menuAdministrarProducto();
                break;
            case 3:
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }

    }

    private void menuAdministrarUsuario() {
        System.out.println("Bienvenido al Menu de Administracion de Usuarios");
        System.out.println("QUe desea realizar: ");
        System.out.println("1.- Crear Nuevo usuario");
        System.out.println("2.- Editar usuario");
        System.out.println("3.- Eliminar Usuario");
        seleccionarOpcionAdministrarUsuario();
    }

    private void seleccionarOpcionAdministrarUsuario() {
        int opcion = teclado.nextInt();
        switch (opcion) {
            case 1:
                Admin.crearNuevoUsuario();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
    }


    private void menuAdministrarProducto() {
        System.out.println("Que tipo de producto desea registrar: ");
        System.out.println("1- Fruta");
        seleccionarProducto();

    }

    private void seleccionarProducto() {
        int opcion = teclado.nextInt();
        switch (opcion) {
            case 1:
                Producto fruta = new Fruta();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
    }
}
