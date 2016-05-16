package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class PropuestaPerfilActualizarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_tema;
    EditText editCodigo_grupo;
    EditText editTema_perfil;
    EditText editEstado;
    EditText editAno_propuesta;
    Spinner spiEstado;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_perfil_actualizar);
        helper = new ControlBD(this);
        editNumero_tema = (EditText) findViewById(R.id.editNumero_tema);
        editCodigo_grupo = (EditText) findViewById(R.id.editCodigo_grupo);
        editTema_perfil = (EditText) findViewById(R.id.editTema_perfil);
        editEstado = (EditText) findViewById(R.id.editEstado);
        editAno_propuesta = (EditText) findViewById(R.id.editAno_propuesta);
        spiEstado = (Spinner) findViewById(R.id.spiEstado);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.estado,android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiEstado.setAdapter(adaptador);
    }

    public void actualizarPropuesta_Perfil(View v) {


        try {

            PropuestaPerfil propuestaPerfil = new PropuestaPerfil();
            propuestaPerfil.setNumero_tema(Integer.valueOf(editNumero_tema.getText().toString()));
            propuestaPerfil.setCodigo_grupo(Integer.valueOf(editCodigo_grupo.getText().toString()));
            propuestaPerfil.setTema_perfil(editTema_perfil.getText().toString());
            propuestaPerfil.setEstado(spiEstado.getSelectedItem().toString());
            propuestaPerfil.setAno_propuesta(Integer.valueOf(editAno_propuesta.getText().toString()));
            helper.abrir();
            String estado = helper.actualizar(propuestaPerfil);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto(View v) {
        editNumero_tema.setText("");
        editCodigo_grupo.setText("");
        editTema_perfil.setText("");
        spiEstado.setSelection(0);
        //editEstado.setText("");
        editAno_propuesta.setText("");
    }
}
