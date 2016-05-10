package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteConsultar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editNombre;
    EditText editApellido;
    EditText editEscuela;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        helper = new ControlBD(this);
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editNombre = (EditText) findViewById(R.id.editNombreDocente);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editEscuela = (EditText) findViewById(R.id.editEscuela);
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
            editEscuela.setText(docente.getEscuela());
        }
    }
    public void limpiarTexto(View v){
        editCodigo.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editEscuela.setText("");
    }
}
