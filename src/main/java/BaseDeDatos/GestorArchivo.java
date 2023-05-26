package baseDeDatos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class GestorArchivo {
    public static void escribirArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
        } catch (IOException e) {
            System.err.println("\nEl archivo no pudo ser creado");
        }
    }
    public static String leerArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String contenido = "";
        try {
            contenido = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.err.println("\nEl archivo no pudo ser leido");
        }
        return contenido;
    }
    public static void nuevaLinea(String ruta, String contenido) {
        String oldContenido = leerArchivo(ruta);
        escribirArchivo(ruta, oldContenido + "\n" + contenido);
    }
}
