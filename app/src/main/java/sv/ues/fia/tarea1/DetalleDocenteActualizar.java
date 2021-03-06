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
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.tiporol,android.R.layout.simple_spinner_item);

        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editCodigoGrupo = (EditText) findViewById(R.id.editCodigoGrupo);
        editNombreDocente = (EditText) findViewById(R.id.editNombreDocente);
        spinnerl.setAdapter(adaptador);
    }
    public void actualizarDetalleDocente(View v) {

        String codigo = editCodigo.getText().toString();
        String codigogrupo=editCodigoGrupo.getText().toString();
        String nombre = editNombreDocente.getText().toString();

        if(codigo.equals("") || codigogrupo.equals("") || nombre.equals("")){
            Toast.makeText(this, "Ingrese los datos en todos los campos", Toast.LENGTH_SHORT).show();
        }else{
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

    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editCodigoGrupo.setText("");

    }

}
