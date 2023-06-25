package usuarios;

import java.util.Objects;

public abstract class Usuario {
    protected String rut;
    protected String nombre;
    protected String nombreUsuario;
    protected String contrasena;

    protected Usuario(String rut, String nombre, String nombreUsuario, String contrasena) {
        this.rut = rut;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    protected Usuario() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Usuario nuevo = (Usuario) obj;
        return Objects.equals(this.getNombre(), nuevo.getNombre()) &&
                Objects.equals(this.getRut(), nuevo.getRut()) &&
                Objects.equals(this.getNombreUsuario(), nuevo.getNombreUsuario()) &&
                Objects.equals(this.getContrasena(), nuevo.getContrasena());
    }
}
