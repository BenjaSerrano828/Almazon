package Usuarios;
import BaseDeDatos.GestorArchivo;
import BaseDeDatos.GestorBaseDatos;
import Productos.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Cajero extends Usuario {

    Scanner teclado = new Scanner(System.in);
    ArrayList<Producto> productosParaEditar = new ArrayList<>();
    public Cajero(String rut, String nombre, String nombreUsuario, String contrasena) {
        super(rut, nombre, nombreUsuario, contrasena);
    }
    public Cajero() {
    }
    public void iniciarMenuPrincipalCajero() {
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Fruta");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Pan");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Snack");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Bebida");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Congelado");
        GestorBaseDatos.cargarDatosProductos(productosParaEditar,"Abarrote");
        System.out.println("---- Menu principal Cajero ----");
        System.out.println("1.- Realizar Venta");
        System.out.println("0.- Atras-> SIn implementar");
        realizarVenta();

    }
    public void realizarVenta(){
        System.out.println(productosParaEditar);
        System.out.print("Ingrese el codigo del producto: ");
        int codigo = teclado.nextInt();
        venderProducto(encontrarProductoPorCodigo(productosParaEditar,codigo));
    }
    public void venderProducto(Producto p){
        System.out.print("Ingrese la cantidad a vender: ");
        int cantidadVendida = teclado.nextInt();
        int stockActual = p.getStock();
        int stockFinal = stockActual - cantidadVendida;
        p.setStock(stockFinal);
        System.out.println("Producto vendido");
        guardarCambiosEditar(productosParaEditar);
        iniciarMenuPrincipalCajero();

    }
    public Producto encontrarProductoPorCodigo(ArrayList<Producto> productos,int codigo){
        for  (Producto p : productos) {
            if (p.getCodigo()==codigo) {
                return p;
            }
        }
        return null;
    }
    public void guardarCambiosEditar(ArrayList<Producto> productos) {
        GestorArchivo gestorArchivo = new GestorArchivo();
        gestorArchivo.crearArchivo("ArchivosBD/productos.txt","");


        //String contenido = usuariosParaEditar.toString()

        for (Producto p : productos) {
            String nombre = p.getNombre();
            int valor = p.getValor();
            int stock = p.getStock();
            int codigo = p.getCodigo();
            String contenido;


            if (p instanceof Abarrote) {
                String marca = ((Abarrote) p).getMarca();
                Producto abarrote = new Abarrote(nombre,valor,stock,marca,codigo);
                contenido = abarrote.toString();
                gestorArchivo.nuevaLinea("ArchivosBD/productos.txt",contenido);
            }else if (p instanceof Bebida){
                double litros = ((Bebida) p).getPesoLitros();
                Producto bebida = new Bebida(nombre,valor,stock,codigo,litros);
                contenido = bebida.toString();
                gestorArchivo.nuevaLinea("ArchivosBD/productos.txt",contenido);
            }else if (p instanceof Pan){
                Producto pan = new Pan(nombre,valor,stock,codigo);
                contenido = pan.toString();
                gestorArchivo.nuevaLinea("ArchivosBD/productos.txt",contenido);
            }else if (p instanceof Congelado){
                String marca = ((Congelado) p).getMarca();
                Producto congelado = new Congelado(nombre,valor,stock,marca,codigo);
                contenido = congelado.toString();
                gestorArchivo.nuevaLinea("ArchivosBD/productos.txt",contenido);
            }else if (p instanceof Fruta){
                Producto fruta = new Fruta(nombre,valor,stock,codigo);
                contenido = fruta.toString();
                gestorArchivo.nuevaLinea("ArchivosBD/productos.txt",contenido);
            }else if (p instanceof Snack){
                String marca = ((Snack) p).getMarca();
                Producto snack = new Snack(nombre,valor,stock,marca,codigo);
                contenido = snack.toString();
                gestorArchivo.nuevaLinea("ArchivosBD/productos.txt",contenido);
            }
        }
    }

    public void mostrarProductos(){
        GestorArchivo gestorArchivo = new GestorArchivo();
        System.out.println(gestorArchivo.leerArchivo("ArchivosBD/productos.txt"));
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
