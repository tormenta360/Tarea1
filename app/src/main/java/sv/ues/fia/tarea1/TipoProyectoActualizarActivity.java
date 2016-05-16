package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TipoProyectoActualizarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_tema;
    EditText editNombre_tipo;
    EditText editTipo_defensa;
    EditText editTipo_realizacion;
    Spinner spiNombre_tipo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_proyecto_actualizar);
        helper = new ControlBD(this);
        editNumero_tema = (EditText) findViewById(R.id.editNumero_tema);
        editNombre_tipo = (EditText) findViewById(R.id.editNombre_tipo);
        editTipo_defensa = (EditText) findViewById(R.id.editTipo_defensa);
        editTipo_realizacion = (EditText) findViewById(R.id.editTipo_realizacion);
        spiNombre_tipo = (Spinner) findViewById(R.id.spiNombre_tipo);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.tipoproyecto,android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiNombre_tipo.setAdapter(adaptador);

}

    public void actualizarTipo_Proyecto(View v) {

        try{

               TipoProyecto tipoProyecto = new TipoProyecto();
        tipoProyecto.setNumero_tema(Integer.valueOf(editNumero_tema.getText().toString()));
        tipoProyecto.setNombre_tipo(spiNombre_tipo .getSelectedItem().toString());
        tipoProyecto.setTipo_defensa(editTipo_defensa.getText().toString());
        tipoProyecto.setTipo_realizacion(editTipo_realizacion.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(tipoProyecto);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        } catch(Exception e){
                Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
            }
    }

    public void limpiarTexto(View v) {
        editNumero_tema.setText("");
        spiNombre_tipo.setSelection(0);
        //editNombre_tipo.setText("");
        editTipo_defensa.setText("");
        editTipo_realizacion.setText("");
    }
}
