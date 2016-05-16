package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoProyectoConsultarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_tema;
    EditText editNombre_tipo;
    EditText editTipo_defensa;
    EditText editTipo_realizacion;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_proyecto_consultar);
        helper = new ControlBD(this);
        editNumero_tema = (EditText) findViewById(R.id.editNumero_tema);
        editNombre_tipo = (EditText) findViewById(R.id.editNombre_tipo);
        editTipo_defensa = (EditText) findViewById(R.id.editTipo_defensa);
        editTipo_realizacion = (EditText) findViewById(R.id.editTipo_realizacion);
    }
    public void consultarTipo_Proyecto(View v) {

        try {

        helper.abrir();
        TipoProyecto tipoProyecto = helper.consultarTipoProyecto(Integer.valueOf(editNumero_tema.getText().toString()));

        helper.cerrar();

        if(tipoProyecto == null)
            Toast.makeText(this, "Tipo Proyecto con numero de tema  " + Integer.valueOf(editNumero_tema.getText().toString()) + " no encontrado",Toast.LENGTH_LONG).show();
        else{

            editNombre_tipo.setText(tipoProyecto.getNombre_tipo());
            editTipo_defensa.setText(tipoProyecto.getTipo_defensa());
            editTipo_realizacion.setText(tipoProyecto.getTipo_realizacion());}
        } catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();

        }
    }
    public void limpiarTexto(View v) {
        editNumero_tema.setText("");
        editNombre_tipo.setText("");
        editTipo_defensa.setText("");
        editTipo_realizacion.setText("");
    }
}
