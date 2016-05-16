package sv.ues.fia.tarea1;

/**
 * Created by rafae on 08/05/2016.
 */
public class Estudiante {

    private String carnet;
    private String nombres;
    private String apellidos;

    public Estudiante() {
    }

    public Estudiante(String carnet) {
        this.carnet = carnet;
    }

    public Estudiante(String carnet, String nombres, String apellidos) {
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
