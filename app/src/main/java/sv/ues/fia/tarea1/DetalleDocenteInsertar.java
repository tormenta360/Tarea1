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
    EditText editTipoRol;
    Spinner spinnerl;

    final String[] datos = new String[]{"Jurado","Docente"};





    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_insertar);
        helper = new ControlBD(this);

        spinnerl = (Spinner) findViewById(R.id.spinner);
        String []opciones={"Jurado","Docente"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);


        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editCodigoGrupo = (EditText) findViewById(R.id.editCodigoGrupo);
        editTipoRol = (EditText) findViewById(R.id.editTipoRol);
        spinnerl.setAdapter(adapter);

    }
    public void insertarDetalleDocente(View v) {
        String regInsertados;
        String codigo=editCodigo.getText().toString();
        int codigogrupo=Integer.parseInt(editCodigoGrupo.getText().toString());
        String selec = spinnerl.getSelectedItem().toString();


        DetalleDocente detalledocente= new DetalleDocente();
        detalledocente.setCodigoDocente(codigo);
        detalledocente.setCodigoGrupo(codigogrupo);
        detalledocente.setTipoRol(selec);
        helper.abrir();
        regInsertados=helper.insertar(detalledocente);
        helper.cerrar();
        if(regInsertados.equals(true)){
            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Error al ingresar, Falta registro en tabla docente o grupo", Toast.LENGTH_LONG).show();
        }

    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editCodigoGrupo.setText("");
        editTipoRol.setText("");
    }
}
