package sv.ues.fia.tarea1;

public class PropuestaPerfil {

    private int numero_tema;
    private int codigo_grupo;
    private String tema_perfil;
    private String estado;
    private int ano_propuesta;

    public PropuestaPerfil(){
            }

    public PropuestaPerfil(int numero_tema, int codigo_grupo, String tema_perfil, String estado, int ano_propuesta) {
        this.numero_tema = numero_tema;
        this.codigo_grupo = codigo_grupo;
        this.tema_perfil = tema_perfil;
        this.estado = estado;
        this.ano_propuesta = ano_propuesta;
    }

    public int getNumero_tema() {

        return numero_tema;
    }

    public void setNumero_tema(int numero_tema) {

        this.numero_tema = numero_tema;
    }

    public int getCodigo_grupo() {

        return codigo_grupo;
    }

    public void setCodigo_grupo(int codigo_grupo) {

        this.codigo_grupo = codigo_grupo;
    }

    public String getTema_perfil() {

        return tema_perfil;
    }

    public void setTema_perfil(String tema_perfil) {

        this.tema_perfil = tema_perfil;
    }

    public String getEstado() {

        return estado;
    }

    public void setEstado(String estado) {

        this.estado = estado;
    }

    public int getAno_propuesta() {
        return ano_propuesta;
    }

    public void setAno_propuesta(int ano_propuesta) {

        this.ano_propuesta = ano_propuesta;
    }
}