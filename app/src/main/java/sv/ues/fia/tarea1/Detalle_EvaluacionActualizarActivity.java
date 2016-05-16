package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Detalle_EvaluacionActualizarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_Evaluacion;
    EditText editCarnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__evaluacion_actualizar);
        helper = new ControlBD(this);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
    }

    public void actualizarDetalle_Evaluacion(View v) {
        try {
            Detalle_Evaluacion detalle_evaluacion = new Detalle_Evaluacion();
            detalle_evaluacion.setNumero_evaluacion(Integer.valueOf(editNumero_Evaluacion.getText().toString()));
            detalle_evaluacion.setCarnet(editCarnet.getText().toString());

            helper.abrir();
            String estado = helper.actualizar(detalle_evaluacion);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editCarnet.setText("");
        editNumero_Evaluacion.setText("");
    }
}