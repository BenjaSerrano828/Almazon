package Usuarios;

public abstract class Usuario {
    protected int id;
    protected static int idInicial=1;

    protected String rut;
    protected String nombre;
    protected String nombreUsuario;
    protected String contrasena;
    protected int permiso;

    public Usuario(String rut, String nombre, String nombreUsuario, String contrasena) {
        this.rut = rut;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.id=idInicial++;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getPermiso() {
        return permiso;
    }

}
