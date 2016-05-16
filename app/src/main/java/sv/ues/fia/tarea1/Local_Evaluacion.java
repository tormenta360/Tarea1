package sv.ues.fia.tarea1;

/**
 * Created by Laptop10 on 8/5/2016.
 */
public class Local_Evaluacion {

    private int codigo_local;
    private int numero_evaluacion;
    private String nombre_local;
    private String disponible;
    private String lugar;

    public Local_Evaluacion() {
    }

    public int getCodigo_local() {
        return codigo_local;
    }

    public void setCodigo_local(int codigo_local) {
        this.codigo_local = codigo_local;
    }

    public int getNumero_evaluacion() {
        return numero_evaluacion;
    }

    public void setNumero_evaluacion(int numero_evaluacion) {
        this.numero_evaluacion = numero_evaluacion;
    }

    public String getNombre_local() {
        return nombre_local;
    }

    public void setNombre_local(String nombre_local) {
        this.nombre_local = nombre_local;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
