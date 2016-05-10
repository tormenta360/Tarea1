package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleDocenteConsultar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editCodigoGrupo;
    EditText editTipoRol;
    EditText editnombreDocente;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_consultar);
        helper = new ControlBD(this);
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editCodigoGrupo = (EditText) findViewById(R.id.editCodigoGrupo);
        editTipoRol = (EditText) findViewById(R.id.editTipoRol);
        editnombreDocente = (EditText) findViewById(R.id.editNombreDocente);
    }

    public void consultarDetalleDocente(View v) {
        helper.abrir();
        DetalleDocente detalleDocente = helper.consultarDetalleDocente(editCodigo.getText().toString());
        helper.cerrar();
        if(detalleDocente == null)
            Toast.makeText(this, "Docente con codigo " + editCodigo.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editCodigo.setText(detalleDocente.getCodigoDocente());
            editCodigoGrupo.setText(detalleDocente.getCodigoGrupo());
            editTipoRol.setText(detalleDocente.getTipoRol());
            editnombreDocente.setText(detalleDocente.getNombreDocente());
        }
    }

    public void limpiarTexto(View v){
        editCodigo.setText("");
        editCodigoGrupo.setText("");
        editTipoRol.setText("");
        editnombreDocente.setText("");
    }

}
