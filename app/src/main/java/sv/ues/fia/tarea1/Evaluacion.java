package sv.ues.fia.tarea1;

/**
 * Created by Laptop10 on 8/5/2016.
 */
public class Evaluacion {

    private int numero_evaluacion;
    private int numero_tema;
    private String nombre_evaluacion;
    private double nota_global;

    public Evaluacion() {
    }

    public Evaluacion(int numero_evaluacion, int numero_tema, String nombre_evaluacion, float nota_global) {
        this.numero_evaluacion = numero_evaluacion;
        this.numero_tema = numero_tema;
        this.nombre_evaluacion = nombre_evaluacion;
        this.nota_global = nota_global;
    }

    public int getNumero_evaluacion() {
        return numero_evaluacion;
    }

    public void setNumero_evaluacion(int numero_evaluacion) {
        this.numero_evaluacion = numero_evaluacion;
    }

    public int getNumero_tema() {
        return numero_tema;
    }

    public void setNumero_tema(int numero_tema) {
        this.numero_tema = numero_tema;
    }

    public String getNombre_evaluacion() {
        return nombre_evaluacion;
    }

    public void setNombre_evaluacion(String nombre_evaluacion) {
        this.nombre_evaluacion = nombre_evaluacion;
    }

    public double getNota_global() {
        return nota_global;
    }

    public void setNota_global(double nota_global) {
        this.nota_global = nota_global;
    }
}