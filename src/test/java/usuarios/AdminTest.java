package usuarios;

import baseDeDatos.GestorBaseDatos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import productos.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    Admin admin = new Admin();
    static ArrayList<Producto> productos = new ArrayList<Producto>();
    @BeforeAll
    public static void setUpBeforeAll() {
        GestorBaseDatos.cargarDatosProductos(productos,"Fruta");
        GestorBaseDatos.cargarDatosProductos(productos,"Pan");
        GestorBaseDatos.cargarDatosProductos(productos,"Snack");
        GestorBaseDatos.cargarDatosProductos(productos,"Bebida");
        GestorBaseDatos.cargarDatosProductos(productos,"Congelado");
        GestorBaseDatos.cargarDatosProductos(productos,"Abarrote");
    }
    /*
    @Test
    public void testCrearNuevoProducto(){
        boolean igual = false;
        Producto abarrote = admin.crearNuevoProducto("Tallarines",100,2000,10,6,0,"Carozzi");
        Producto abarrote2 = new Abarrote("Tallarines", 2000, 10,100,"Carozzi");
        if(abarrote.getNombre() == abarrote2.getNombre()){
            if(abarrote.getValor() == abarrote2.getValor()){
                if(abarrote.getCodigo() == abarrote2.getCodigo()){
                    if(abarrote.getStock() == abarrote2.getStock()){
                        if(((Abarrote) abarrote).getMarca() == ((Abarrote) abarrote2).getMarca()){
                            igual = true;
                        }
                    }
                }
            }
        }
        assertTrue(igual);
    }*/
    @Test
    public void testCrearNuevoAbarrote(){
        boolean igual = false;
        Producto abarrote = admin.crearNuevoProducto("Tallarines",105,2000,10,6,0,"Carozzi");
        Producto abarrote2 = new Abarrote("Tallarines", 2000, 10,105,"Carozzi");
        if(abarrote.toString().equals(abarrote2.toString())){
            igual = true;
        }
        assertTrue(igual);
    }
    @Test
    public void testCrearNuevaBebida(){
        boolean igual = false;
        Producto bebida = admin.crearNuevoProducto("Canada Dry",100,2000,10,3,3,"");
        Producto bebida2 = new Bebida("Canada Dry", 2000, 10,100,3);
        if(bebida.toString().equals(bebida2.toString())){
            igual = true;
        }
        assertTrue(igual);
    }
    @Test
    public void testCrearNuevoCongelado(){
        boolean igual = false;
        Producto congelado = admin.crearNuevoProducto("Nuggets de pescado",100,2000,10,5,0,"Super Pollo");
        Producto congelado2 = new Congelado("Nuggets de pescado", 2000, 10,"Super Pollo",100);
        if(congelado.toString().equals(congelado2.toString())){
            igual = true;
        }
        assertTrue(igual);
    }
    @Test
    public void testCrearNuevaFruta(){
        boolean igual = false;
        Producto fruta = admin.crearNuevoProducto("Platano",100,2000,10,1,0,"");
        Producto fruta2 = new Fruta("Platano",2000,10,100);
        if(fruta.toString().equals(fruta2.toString())){
            igual = true;
        }
        assertTrue(igual);
    }
    @Test
    public void testCrearNuevoPan(){
        boolean igual = false;
        Producto pan = admin.crearNuevoProducto("Marraqueta",100,2000,10,2,0,"");
        Producto pan2 = new Pan("Marraqueta", 2000, 10,100);
        if(pan.toString().equals(pan2.toString())){
            igual = true;
        }
        assertTrue(igual);
    }
    @Test
    public void testCrearNuevoSnack(){
        boolean igual = false;
        Producto snack = admin.crearNuevoProducto("Tostitos",100,2000,10,4,0,"Evercrisp");
        Producto snack2 = new Snack("Tostitos", 2000, 10,"Evercrisp",100);
        if(snack.toString().equals(snack2.toString())){
            igual = true;
        }
        assertTrue(igual);
    }
    @Test
    public void testGuardarProducto(){
        boolean existe = false;
        int codigo = 105; // codigo del abarrote creado en el testCrearAbarrote()
        for  (Producto p: productos) {
            if (p.getCodigo() == codigo){
                existe = true;
            }
        }
        assertTrue(existe);
    }
}