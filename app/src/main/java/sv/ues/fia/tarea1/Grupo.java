package sv.ues.fia.tarea1;

/**
 * Created by rafae on 08/05/2016.
 */
public class Grupo {

    private int codigo_grupo;
    private int numero_integrantes;
    private int año;

    public Grupo() {
    }

    public Grupo(int codigo_grupo) {
        this.codigo_grupo = codigo_grupo;
    }

    public Grupo(int codigo_grupo, int numero_integrantes, int año) {
        this.codigo_grupo = codigo_grupo;
        this.numero_integrantes = numero_integrantes;
        this.año = año;
    }

    public int getCodigo_grupo() {
        return codigo_grupo;
    }

    public void setCodigo_grupo(int codigo_grupo) {
        this.codigo_grupo = codigo_grupo;
    }

    public int getNumero_integrantes() {
        return numero_integrantes;
    }

    public void setNumero_integrantes(int numero_integrantes) {
        this.numero_integrantes = numero_integrantes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
}


