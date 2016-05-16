package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Detalle_EvaluacionEliminarActivity extends Activity {

    EditText editNumero_Evaluacion, editCarnet;
    ControlBD controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__evaluacion_eliminar);
        controlhelper=new ControlBD(this);
        editNumero_Evaluacion = (EditText)findViewById(R.id.editNumero_Evaluacion);
        editCarnet=(EditText)findViewById(R.id.editCarnet);
    }

    public void eliminarDetalle_Evaluacion(View v){

        try {
            String regEliminadas;
            Detalle_Evaluacion detalle_evaluacion = new Detalle_Evaluacion();
            detalle_evaluacion.setNumero_evaluacion(Integer.valueOf(editNumero_Evaluacion.getText().toString()));
            detalle_evaluacion.setCarnet(editCarnet.getText().toString());
            controlhelper.abrir();
            regEliminadas = controlhelper.eliminar(detalle_evaluacion);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar(View v){
        editNumero_Evaluacion.setText("");
        editCarnet.setText("");
    }
}