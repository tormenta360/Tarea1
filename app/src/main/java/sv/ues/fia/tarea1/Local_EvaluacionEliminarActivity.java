package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Local_EvaluacionEliminarActivity extends Activity {

    EditText editCodigo_Local;
    EditText editNumero_Evaluacion;
    ControlBD controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local__evaluacion_eliminar);
        controlhelper = new ControlBD(this);
        editCodigo_Local = (EditText) findViewById(R.id.editCodigo_Local);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
    }

    public void eliminarLocal_Evaluacion(View v) {
        try {
            String regEliminadas;
            Local_Evaluacion local_evaluacion = new Local_Evaluacion();
            local_evaluacion.setCodigo_local(Integer.valueOf(editCodigo_Local.getText().toString()));
            local_evaluacion.setNumero_evaluacion(Integer.valueOf(editNumero_Evaluacion.getText().toString()));

            controlhelper.abrir();
            regEliminadas = controlhelper.eliminar(local_evaluacion);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar(View v) {
        editCodigo_Local.setText("");
        editNumero_Evaluacion.setText("");
    }
}
