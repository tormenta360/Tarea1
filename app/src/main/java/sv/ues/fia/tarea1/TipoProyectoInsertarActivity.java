package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class TipoProyectoInsertarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_tema;
    EditText editNombre_tipo;
    EditText editTipo_defensa;
    EditText editTipo_realizacion;
    Spinner spiNombre_tipo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_proyecto_insertar);
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
    public void insertarTipo_Proyecto(View v) {

        try{

        int numero_tema=Integer.valueOf(editNumero_tema.getText().toString());
        String nombre_tipo=spiNombre_tipo.getSelectedItem().toString();
        String tipo_defensa=editTipo_defensa.getText().toString();
        String tipo_realizacion=editTipo_realizacion.getText().toString();
        String regInsertados;


        TipoProyecto tipoProyecto = new TipoProyecto();
        tipoProyecto.setNumero_tema(numero_tema);
        tipoProyecto.setNombre_tipo(nombre_tipo);
        tipoProyecto.setTipo_defensa(tipo_defensa);
        tipoProyecto.setTipo_realizacion(tipo_realizacion);
        helper.abrir();
        regInsertados=helper.insertar(tipoProyecto);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
       } catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editNumero_tema.setText("");
        //editNombre_tipo.setText("");
        spiNombre_tipo.setSelection(0);
        editTipo_defensa.setText("");
        editTipo_realizacion.setText("");
    }
}
