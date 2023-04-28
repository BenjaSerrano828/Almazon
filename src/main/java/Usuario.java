public abstract class Usuario {
    protected int id;
    protected static int idInicial=1;

    private String rut;
    private String nombre;
    private String nombreUsuario;
    private String contrasena;
    protected int permiso;

    public Usuario(String rut, String nombre, String nombreUsuario, String contrasena) {
        this.rut = rut;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.id=idInicial++;
    }
}
