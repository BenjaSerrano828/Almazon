package Usuarios;
public abstract class Usuario {
    private String rut;
    protected String nombre;
    protected String nombreUsuario;
    protected String contrasena;

    public Usuario(String rut, String nombre, String nombreUsuario, String contrasena) {
        this.rut = rut;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
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

    public String getRut() {
        return rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
