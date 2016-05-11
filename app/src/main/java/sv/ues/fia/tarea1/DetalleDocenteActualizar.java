package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetalleDocenteActualizar extends Activity {
    ControlBD helper;
    EditText editCodigo;
    EditText editCodigoGrupo;
    Spinner spinnerl;
    EditText editNombreDocente;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_actualizar);
        helper = new ControlBD(this);

        spinnerl = (Spinner) findViewById(R.id.spinner);
        String []opciones={"Jurado","Docente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);

        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editCodigoGrupo = (EditText) findViewById(R.id.editCodigoGrupo);
        editNombreDocente = (EditText) findViewById(R.id.editNombreDocente);
        //editTipoRol = (EditText) findViewById(R.id.editTipoRol);
        spinnerl.setAdapter(adapter);
    }
    public void actualizarDetalleDocente(View v) {

        DetalleDocente detalleDocente = new DetalleDocente();
        detalleDocente.setCodigoDocente(editCodigo.getText().toString());
        detalleDocente.setCodigoGrupo(Integer.valueOf(editCodigoGrupo.getText().toString()));
        detalleDocente.setTipoRol(spinnerl.getSelectedItem().toString());
        detalleDocente.setNombreDocente(editNombreDocente.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(detalleDocente);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editCodigoGrupo.setText("");

    }

}
