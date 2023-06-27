package usuarios;

import baseDeDatos.GestorBaseDatos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminUsuarioTest {

    static ArrayList<Usuario> usuarios = new ArrayList<>();

    @BeforeEach
    public void setUpBeforeEach(){
        GestorBaseDatos.cargarDatosUsuarios(usuarios);
    }

    @Test
    @Order(1)
    public void registrarNuevoCajero(){
        Admin admin = new Admin();
        Usuario cajero1 = admin.registrarNuevoUsuario(1,"Sebastian","sebastu","20.645.607-8","sebastu123");
        Usuario cajero2 = new Cajero("20.645.607-8","Sebastian","sebastu","sebastu123");
        assertEquals(cajero1,cajero2);
    }
    @Test
    @Order(2)
    public void registrarNuevoAdmin(){
        Admin admin = new Admin();
        Usuario admin1 = admin.registrarNuevoUsuario(2,"Benjamin","benja","20.587.734-7","benja123");
        Usuario admin2 = new Admin("20.587.734-7","Benjamin","benja","benja123");
        assertEquals(admin1,admin2);
    }

    @Test
    @Order(3)
    public void guardarNuevoCajero(){
        boolean existe = false;
        Usuario cajero2 = new Cajero("20.645.607-8","Sebastian","sebastu","sebastu123");
        for  (Usuario u: usuarios) {
            if (u.equals(cajero2)){
                existe = true;
                break;
            }
        }
        assertTrue(existe);
    }
    @Test
    @Order(4)
    public void guardarNuevoAdmin(){
        boolean existe = false;
        Usuario admin2 = new Admin("20.587.734-7","Benjamin","benja","benja123");
        for  (Usuario u: usuarios) {
            if (u.equals(admin2)){
                existe = true;
                break;
            }
        }
        assertTrue(existe);
    }

}