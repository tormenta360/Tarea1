package sv.ues.fia.tarea1;

/**
 * Created by rafae on 08/05/2016.
 */
public class EstudianteGrupo {

    private String carnet;
    private int codigoGrupo;
    private String nombres;
    private String apellidos;
    private int año;
    private int integrantes;

    public EstudianteGrupo() {
    }

    public EstudianteGrupo(String carnet, int codigoGrupo, String nombres, String apellidos, int año, int integrantes) {
        this.carnet = carnet;
        this.codigoGrupo = codigoGrupo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.año = año;
        this.integrantes = integrantes;
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

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(int integrantes) {
        this.integrantes = integrantes;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(int codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }
}
