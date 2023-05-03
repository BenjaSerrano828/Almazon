package Sesion;

import Usuarios.Admin;
import Usuarios.Cajero;
import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Sesion {

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    Usuario adminSupremo = new Admin("rut del dueño", "admin", "admin", "admin123");

    public void anadirUsuario(Usuario u) {
        usuarios.add(u);
    }

    Scanner teclado = new Scanner(System.in);

    public Sesion() {
        iniciarSesion();
    }

    public void iniciarSesion() {

        anadirUsuario(adminSupremo);


        System.out.print("Ingrese su nombre de Usuario: ");
        String nombreUsuarioIngresado = teclado.next();
        System.out.print("Ingrese su contraseña: ");
        String contrasenaIngresada = teclado.next();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(nombreUsuarioIngresado)
                    & usuarios.get(i).getContrasena().equals(contrasenaIngresada)){
                System.out.println("Nombre de usuario y contraseña correctos");
                direccionarMenu(usuarios.get(i));
            }
            else{
                System.out.println("nombre de usuario o contraseña incorrectos");
                //aqui hay que llamar de nuevo al metodo
            }
        }
    }

    public void direccionarMenu(Usuario u) {
        /*if(u.getPermiso() == 1) {
            Admin a = new Admin();
            u=a;
            a.iniciarMenuPrincipalAdmin();
        }else {
            Cajero c = new Cajero();
            u = c;
            c.iniciarMenuPrincipalCajero();
        }*/


        if(u.getClass() == Admin.class){
            ((Admin) u).iniciarMenuPrincipalAdmin();
        }else{
            ((Cajero) u).iniciarMenuPrincipalCajero();
        }

        /*
        if (u.getPermiso() ==1){
            ((Admin)u).iniciarMenuPrincipalAdmin();
        }else{
            ((Cajero)u).iniciarMenuPrincipalCajero();
        }*/
    }
}
