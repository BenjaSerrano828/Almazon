public class Main {
    public static void main(String[] args){
        Sesion sesion = new Sesion();
        Usuario u1 = new Admin("rut","nombre","usuario","contra");
        Usuario u2 = new Admin("rut","nombre","usuario","contra");
        Usuario u3 = new Admin("rut","nombre","usuario","contra");
        Usuario u4 = new Admin("rut","nombre","usuario","contra");

        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println(u4);

    }
}
