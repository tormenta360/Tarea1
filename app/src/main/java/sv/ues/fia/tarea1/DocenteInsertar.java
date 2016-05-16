package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DocenteInsertar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editNombre;
    EditText editApellido;
    Spinner spinnerTipoContrato;
    EditText editCorreo;
    EditText editTelefono;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);


        helper = new ControlBD(this);




        editCodigo = (EditText) findViewById(R.id.etCodigo);
        editNombre = (EditText) findViewById(R.id.etNombre);
        editApellido = (EditText) findViewById(R.id.etApellido);
        spinnerTipoContrato = (Spinner) findViewById(R.id.spinnerTipoContrato);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.tipoContrato,android.R.layout.simple_spinner_item);
;


        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editTelefono = (EditText) findViewById(R.id.editTelefono);
        spinnerTipoContrato.setAdapter(adaptador);

    }
    public void insertarDocente(View v) {
        String codigo=editCodigo.getText().toString();
        String nombre=editNombre.getText().toString();
        String apellido=editApellido.getText().toString();
        String tipoContrato = spinnerTipoContrato.getSelectedItem().toString();
        String correo = editCorreo.getText().toString();
        String telefono = editTelefono.getText().toString();


        String regInsertados;

        if(codigo.equals("") || codigo.equals("") || nombre.equals("")|| apellido.equals("")|| correo.equals("")|| telefono.equals("")){
            Toast.makeText(this, "Ingrese los datos en todos los campos", Toast.LENGTH_SHORT);
        }else {
            Docente docente = new Docente();
            docente.setCodigoDocente(codigo);
            docente.setNombreDocente(nombre);
            docente.setApellidoDocente(apellido);
            docente.setTipoContrato(tipoContrato);
            docente.setCorreo(correo);
            docente.setTelefono(telefono);


            helper.abrir();
            regInsertados = helper.insertar(docente);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editNombre.setText("");
        editApellido.setText("");
        spinnerTipoContrato.setSelection(0);
        editCorreo.setText("");
        editTelefono.setText("");


    }
}
