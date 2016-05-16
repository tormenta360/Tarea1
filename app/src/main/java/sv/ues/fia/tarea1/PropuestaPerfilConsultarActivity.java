package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PropuestaPerfilConsultarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_tema;
    EditText editCodigo_grupo;
    EditText editTema_perfil;
    EditText editEstado;
    EditText editAno_propuesta;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_perfil_consultar);
        helper = new ControlBD(this);
        editNumero_tema = (EditText) findViewById(R.id.editNumero_tema);
        editCodigo_grupo = (EditText) findViewById(R.id.editCodigo_grupo);
        editTema_perfil = (EditText) findViewById(R.id.editTema_perfil);
        editEstado = (EditText) findViewById(R.id.editEstado);
        editAno_propuesta = (EditText) findViewById(R.id.editAno_propuesta);
    }
    public void consultarPropuesta_Perfil(View v) {

        try {

            helper.abrir();
            PropuestaPerfil propuestaPerfil = helper.consultarPropuestaPerfil(Integer.valueOf(editNumero_tema.getText().toString()), Integer.valueOf(editCodigo_grupo.getText().toString()));

            helper.cerrar();

            if (propuestaPerfil == null)
                Toast.makeText(this, "Propuesta perfil" + " " + Integer.valueOf(editNumero_tema.getText().toString()) + "no encontrado", Toast.LENGTH_LONG).show();

            else {
                editTema_perfil.setText(propuestaPerfil.getTema_perfil());
                editEstado.setText(propuestaPerfil.getEstado());
                editAno_propuesta.setText(String.valueOf(propuestaPerfil.getAno_propuesta()));}
            }catch(Exception e){
                Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
            }
        }

    public void limpiarTexto(View v) {
            editNumero_tema.setText("");
            editCodigo_grupo.setText("");
            editTema_perfil.setText("");
            editEstado.setText("");
            editAno_propuesta.setText("");
    }
}
