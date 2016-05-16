package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Detalle_EvaluacionInsertarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_Evaluacion;
    EditText editCarnet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__evaluacion_insertar);

        helper = new ControlBD(this);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
    }

    public void insertarDetalle_Evaluacion(View v) {

        try {
            String regInsertados;
            int numero_evaluacion = Integer.valueOf(editNumero_Evaluacion.getText().toString());
            String carnet = editCarnet.getText().toString();

            Detalle_Evaluacion detalle_evaluacion = new Detalle_Evaluacion();
            detalle_evaluacion.setNumero_evaluacion(numero_evaluacion);
            detalle_evaluacion.setCarnet(carnet);
            helper.abrir();
            regInsertados = helper.insertar(detalle_evaluacion);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editNumero_Evaluacion.setText("");
        editCarnet.setText("");
    }
}