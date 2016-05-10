package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteActualizar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editNombre;
    EditText editApellido;
    EditText editEscuela;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        helper = new ControlBD(this);
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editNombre = (EditText) findViewById(R.id.editNombreDocente);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editEscuela = (EditText) findViewById(R.id.editEscuela);
    }
    public void actualizarDocente(View v) {

        Docente docente = new Docente();
        docente.setCodigoDocente(editCodigo.getText().toString());
        docente.setNombreDocente(editNombre.getText().toString());
        docente.setApellidoDocente(editApellido.getText().toString());
        docente.setEscuela(editEscuela.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(docente);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editEscuela.setText("");
    }
}
