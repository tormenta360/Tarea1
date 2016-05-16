package sv.ues.fia.tarea1;

public class TipoProyecto {

    private String nombre_tipo;
    private int numero_tema;
    private String tipo_defensa;
    private String tipo_realizacion;

    public TipoProyecto(){
    }

    public TipoProyecto(int numero_tema, String nombre_tipo, String tipo_defensa, String tipo_realizacion) {
        this.numero_tema = numero_tema;
        this.nombre_tipo = nombre_tipo;
        this.tipo_defensa = tipo_defensa;
        this.tipo_realizacion = tipo_realizacion;
    }

    public int getNumero_tema() {
        return numero_tema;
    }

    public void setNumero_tema(int numero_tema) {
        this.numero_tema = numero_tema;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public String getTipo_defensa() {
        return tipo_defensa;
    }

    public void setTipo_defensa(String tipo_defensa) {
        this.tipo_defensa = tipo_defensa;
    }

    public String getTipo_realizacion() {
        return tipo_realizacion;
    }

    public void setTipo_realizacion(String tipo_realizacion) {
        this.tipo_realizacion = tipo_realizacion;
    }
}

