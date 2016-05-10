package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteInsertar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editNombre;
    EditText editApellido;
    EditText editEscuela;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        helper = new ControlBD(this);
        editCodigo = (EditText) findViewById(R.id.etCodigo);
        editNombre = (EditText) findViewById(R.id.etNombre);
        editApellido = (EditText) findViewById(R.id.etApellido);
        editEscuela = (EditText) findViewById(R.id.etEscuela);
    }
    public void insertarDocente(View v) {
        String codigo=editCodigo.getText().toString();
        String nombre=editNombre.getText().toString();
        String apellido=editApellido.getText().toString();
        String escuela=editEscuela.getText().toString();
        String regInsertados;
        Docente docente=new Docente();
        docente.setCodigoDocente(codigo);
        docente.setNombreDocente(nombre);
        docente.setApellidoDocente(apellido);
        docente.setEscuela(escuela);
        helper.abrir();
        regInsertados=helper.insertar(docente);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editEscuela.setText("");
    }
}
