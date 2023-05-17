package Usuarios;


public class Cajero extends Usuario {
    public Cajero(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
    }

    public Cajero() {
    }
    public void iniciarMenuPrincipalCajero() {
        System.out.println("---- Menu principal Cajero ----");
        System.out.println("1.- Realizar Venta");
        System.out.println("0.- Cerrar Sesion");


    }

    @Override
    public String toString() {
        return "\nCajero" +
                "\nNombre: "+ getNombre() +
                "\nRut: "+getRut() +
                "\nNombre de Usuario: "+getNombreUsuario() +
                "\nContraseña: "+getContrasena();
    }
}
