public class Cajero extends Usuario{
    public Cajero(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
        this.permiso=2; // 2 es todos los permisos de cajero
        this.id=idInicial++;
    }
}
