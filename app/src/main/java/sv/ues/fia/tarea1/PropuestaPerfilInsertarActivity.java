package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PropuestaPerfilInsertarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_tema;
    EditText editCodigo_grupo;
    EditText editTema_perfil;
    EditText editEstado;
    EditText editAno_propuesta;
    Spinner spiEstado;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_perfil_insertar);
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
    public void insertarPropuesta_Perfil(View v) {

        try {

            int numero_tema = Integer.valueOf(editNumero_tema.getText().toString());
            int codigo_grupo = Integer.valueOf(editCodigo_grupo.getText().toString());
            String tema_perfil = editTema_perfil.getText().toString();
            String estado = spiEstado.getSelectedItem().toString();
            int ano_propuesta = Integer.valueOf(editAno_propuesta.getText().toString());
            String regInsertados;


            PropuestaPerfil propuestaPerfil = new PropuestaPerfil();
            propuestaPerfil.setNumero_tema(numero_tema);
            propuestaPerfil.setCodigo_grupo(codigo_grupo);
            propuestaPerfil.setTema_perfil(tema_perfil);
            propuestaPerfil.setEstado(estado);
            propuestaPerfil.setAno_propuesta(ano_propuesta);
            helper.abrir();
            regInsertados = helper.insertar(propuestaPerfil);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        } catch(Exception e){
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
    } }