package sv.ues.fia.tarea1;

/**
 * Created by Laptop10 on 8/5/2016.
 */
public class Detalle_Evaluacion {
    private int numero_evaluacion;
    private String carnet;

    public Detalle_Evaluacion() {
    }

    public Detalle_Evaluacion(int numero_evaluacion, String carnet) {
        this.numero_evaluacion = numero_evaluacion;
        this.carnet = carnet;
    }

    public int getNumero_evaluacion() {
        return numero_evaluacion;
    }

    public void setNumero_evaluacion(int numero_evaluacion) {
        this.numero_evaluacion = numero_evaluacion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
}
