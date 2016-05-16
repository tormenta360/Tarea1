package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Local_EvaluacionConsultarActivity extends Activity {

    ControlBD helper;
    EditText editCodigo_Local;
    EditText editNumero_Evaluacion;
    EditText editNombre_Local;
    EditText editDisponible;
    EditText editLugar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__evaluacion_consultar);

        helper = new ControlBD(this);
        editCodigo_Local= (EditText) findViewById(R.id.editCodigo_Local);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editNombre_Local = (EditText) findViewById(R.id.editNombre_Local);
        editDisponible = (EditText) findViewById(R.id.editDisponible);
        editLugar = (EditText) findViewById(R.id.editLugar);
    }

    public void consultarLocal_Evaluacion(View v){

        try {
            helper.abrir();

            Local_Evaluacion local_evaluacion = helper.consultarLocal_Evaluacion(Integer.valueOf(editCodigo_Local.getText().toString()), Integer.valueOf(editNumero_Evaluacion.getText().toString()));

            helper.cerrar();

            if (local_evaluacion == null)
                Toast.makeText(this, "Local N° " + editCodigo_Local.getText().toString() + " y evaluacion N° " + editNumero_Evaluacion.getText().toString() + " no encontrado.", Toast.LENGTH_LONG).show();
            else {
                editNombre_Local.setText(local_evaluacion.getNombre_local());
                editDisponible.setText(String.valueOf(local_evaluacion.getDisponible()));
                editLugar.setText(local_evaluacion.getLugar());
            }
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editCodigo_Local.setText("");
        editNumero_Evaluacion.setText("");
    }
}