package Productos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Producto {

    private String nombre;
    private int codigo;
    private static int codigoInicial=1;
    private int valor;
    private String tipo;
    private int stock=0;

    private static ArrayList<Producto> productos = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);

    public Producto() {
    }

    public Producto(String nombre, int valor,int stock, String tipo) {
        this.nombre = nombre;
        this.valor = valor;
        this.stock = stock;
        this.tipo = tipo;
        this.codigo = codigoInicial;
    }

}
