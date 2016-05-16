package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Local_EvaluacionInsertarActivity extends Activity {

    ControlBD helper;
    EditText editCodigo_Local;
    EditText editNumero_Evaluacion;
    EditText editNombre_Local;
    EditText editDisponible;
    EditText editLugar;
    Spinner spiDisponible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__evaluacion_insertar);

        helper = new ControlBD(this);
        editCodigo_Local = (EditText) findViewById(R.id.editCodigo_Local);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editNombre_Local = (EditText) findViewById(R.id.editNombre_Local);
        editDisponible = (EditText) findViewById(R.id.editDisponible);
        editLugar = (EditText) findViewById(R.id.editLugar);
        spiDisponible = (Spinner) findViewById(R.id.spiDisponible);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.disponible, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiDisponible.setAdapter(adaptador);
    }

    public void insertarLocal_Evaluacion(View v) {

        try {
            String regInsertados;
            int codigo_local = Integer.valueOf(editCodigo_Local.getText().toString());
            int numero_evaluacion = Integer.valueOf(editNumero_Evaluacion.getText().toString());
            String nombre_local = editNombre_Local.getText().toString();
            //int disponible = Integer.valueOf(editDisponible.getText().toString());
            String disponible = spiDisponible.getSelectedItem().toString();
            String lugar = editLugar.getText().toString();

            Local_Evaluacion local_evaluacion = new Local_Evaluacion();
            local_evaluacion.setCodigo_local(codigo_local);
            local_evaluacion.setNumero_evaluacion(numero_evaluacion);
            local_evaluacion.setNombre_local(nombre_local);
            local_evaluacion.setDisponible(disponible);
            local_evaluacion.setLugar(lugar);
            helper.abrir();
            regInsertados = helper.insertar(local_evaluacion);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editCodigo_Local.setText("");
        editNumero_Evaluacion.setText("");
        editNombre_Local.setText("");
        //editDisponible.setText("");
        spiDisponible.setSelection(0);
        editLugar.setText("");
    }
}
