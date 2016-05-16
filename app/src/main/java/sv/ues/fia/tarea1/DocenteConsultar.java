package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DocenteConsultar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editNombre;
    EditText editApellido;
    EditText editTelefono,editCorreo,editTipoContrato;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        helper = new ControlBD(this);
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editNombre = (EditText) findViewById(R.id.editNombreDocente);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editTipoContrato= (EditText) findViewById(R.id.editTipoContrato);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editTelefono = (EditText) findViewById(R.id.editTelefono);

    }
    public void consultarDocente(View v) {


        helper.abrir();
        Docente docente = helper.consultarDocente(editCodigo.getText().toString());
        helper.cerrar();
        if(docente == null)
            Toast.makeText(this, "Docente con carnet " + editCodigo.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombre.setText(docente.getNombreDocente());
            editApellido.setText(docente.getApellidoDocente());
            editTipoContrato.setText(docente.getTipoContrato());
            editCorreo.setText(docente.getCorreo());
            editTelefono.setText(docente.getTelefono());

        }


    }
    public void limpiarTexto(View v){
        editCodigo.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editTipoContrato.setText("");
        editCorreo.setText("");
        editTelefono.setText("");
        
    }
}
