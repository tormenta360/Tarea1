package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Detalle_EvaluacionConsultarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_Evaluacion;
    EditText editCarnet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__evaluacion_consultar);

        helper = new ControlBD(this);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
    }

    public void consultarDetalle_Evaluacion(View v){

    try {
        helper.abrir();

        Detalle_Evaluacion detalle_evaluacion = helper.consultarDetalle_Evaluacion(editCarnet.getText().toString());

        helper.cerrar();

        if (detalle_evaluacion == null)
            Toast.makeText(this, "Carnet N° " + editCarnet.getText().toString() + " no encontrado.", Toast.LENGTH_LONG).show();
        else {
            editNumero_Evaluacion.setText(String.valueOf(detalle_evaluacion.getNumero_evaluacion()));
        }
    }catch(Exception e){
        Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
    }
    }

    public void limpiarTexto(View v) {
        editCarnet.setText("");
    }
}
