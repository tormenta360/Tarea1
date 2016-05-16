package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DocenteActualizar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editNombre;
    EditText editApellido;
    Spinner spinner;
    EditText editCorreo,editTelefono;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        spinner = (Spinner) findViewById(R.id.spinnerTipoContrato);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.tipoContrato,android.R.layout.simple_spinner_item);



        helper = new ControlBD(this);
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editNombre = (EditText) findViewById(R.id.editNombreDocente);
        editApellido = (EditText) findViewById(R.id.editApellido);

        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editTelefono =(EditText) findViewById(R.id.editTelefono);
        spinner.setAdapter(adaptador);

    }
    public void actualizarDocente(View v) {

        String codigo = editCodigo.getText().toString();
        String nombre = editNombre.getText().toString();
        String apellido = editApellido.getText().toString();
        String correo = editCorreo.getText().toString();
        String telefono = editTelefono.getText().toString();


        Docente docente = new Docente();
        docente.setCodigoDocente(codigo);
        docente.setNombreDocente(nombre);
        docente.setApellidoDocente(apellido);
        docente.setTipoContrato(spinner.getSelectedItem().toString());
        docente.setCorreo(correo);
        docente.setTelefono(telefono);

        helper.abrir();
        String estado = helper.actualizar(docente);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editTelefono.setText("");
        editCorreo.setText("");
        spinner.setSelection(0);
    }
}
