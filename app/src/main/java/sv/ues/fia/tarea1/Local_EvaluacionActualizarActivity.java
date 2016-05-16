package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Local_EvaluacionActualizarActivity extends Activity {

    ControlBD helper;
    EditText editCodigoLocal;
    EditText editNumero_Evaluacion;
    EditText editNombre_Local;
    EditText editDisponible;
    EditText editLugar;
    Spinner spiDisponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__evaluacion_actualizar);
        helper = new ControlBD(this);
        editCodigoLocal = (EditText) findViewById(R.id.editCodigo_Local);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editNombre_Local = (EditText) findViewById(R.id.editNombre_Local);
        editDisponible = (EditText) findViewById(R.id.editDisponible);
        editLugar = (EditText) findViewById(R.id.editLugar);
        spiDisponible = (Spinner) findViewById(R.id.spiDisponible);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.disponible, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiDisponible.setAdapter(adaptador);
    }

    public void actualizarLocal_Evaluacion(View v){
        try {
            Local_Evaluacion local_evaluacion = new Local_Evaluacion();
            local_evaluacion.setCodigo_local(Integer.valueOf(editCodigoLocal.getText().toString()));
            local_evaluacion.setNumero_evaluacion(Integer.valueOf(editNumero_Evaluacion.getText().toString()));
            local_evaluacion.setNombre_local(editNombre_Local.getText().toString());
            local_evaluacion.setDisponible(spiDisponible.getSelectedItem().toString());
            local_evaluacion.setLugar(editLugar.getText().toString());

            helper.abrir();
            String estado = helper.actualizar(local_evaluacion);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editCodigoLocal.setText("");
        editNumero_Evaluacion.setText("");
        editNombre_Local.setText("");
        //editDisponible.setText("");
        spiDisponible.setSelection(0);
        editLugar.setText("");
    }
}
