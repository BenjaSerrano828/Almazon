public class Admin extends Usuario {
    private int idActual = 1;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                '}';
    }

    public Admin(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
        this.permiso=1; //1 es todos los permisos de admin




    }

    public void crearProducto() {

    }

    public static void crearNuevoUsuario(){
        System.out.println("Que usuario desea crear:");
        System.out.println("1.- Admin");
        System.out.println("2.- Cajero");
        System.out.println("Ingrese el nombre del usuario");




    }

}
