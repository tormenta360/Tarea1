package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetalleDocenteInsertar extends Activity {



    ControlBD helper;
    EditText editCodigo;
    EditText editCodigoGrupo;
    Spinner spinnerl, spinnerCodigos;
    EditText editNombre;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_insertar);
        helper = new ControlBD(this);
        spinnerl = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.tiporol,android.R.layout.simple_spinner_item);


        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editCodigoGrupo = (EditText) findViewById(R.id.editCodigoGrupo);
        editNombre = (EditText) findViewById(R.id.editNombreDocente);
        spinnerl.setAdapter(adaptador);

    }



    public void insertarDetalleDocente(View v) {
        String regInsertados;
        String codigo=editCodigo.getText().toString();
        String codigogrupo=editCodigoGrupo.getText().toString();
        String selec = spinnerl.getSelectedItem().toString();
        String nombre = editNombre.getText().toString();

        if(codigo.equals("") || codigogrupo.equals("") || selec.equals("")|| nombre.equals("")){
            Toast.makeText(this, "Ingrese los datos en todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            DetalleDocente detalledocente= new DetalleDocente();
            detalledocente.setCodigoDocente(codigo);
            detalledocente.setCodigoGrupo(Integer.valueOf(codigogrupo));
            detalledocente.setTipoRol(selec);
            detalledocente.setNombreDocente(nombre);
            helper.abrir();

            regInsertados=helper.insertar(detalledocente);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
        }

    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editCodigoGrupo.setText("");

        editNombre.setText("");
    }

}
