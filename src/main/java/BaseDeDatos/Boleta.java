package BaseDeDatos;

import java.time.LocalDate;
import java.time.LocalTime;

public class Boleta {
    private int id;
    private LocalDate fecha;
    private LocalTime hora;

    public Boleta(int id, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Boleta() {
    }
    public void generarBoleta(){

    }
    public String mostrarBoleta(){
        return "";
    }
}
