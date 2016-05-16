package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EvaluacionActualizarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_Evaluacion;
    EditText editNumero_Tema;
    EditText editNombre_Evaluacion;
    EditText editNota_Global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_actualizar);
        helper = new ControlBD(this);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editNumero_Tema = (EditText) findViewById(R.id.editNumero_Tema);
        editNombre_Evaluacion = (EditText) findViewById(R.id.editNombre_Evaluacion);
        editNota_Global = (EditText) findViewById(R.id.editNota_Global);
    }

    public void actualizarEvaluacion(View v) {

        try {
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setNumero_evaluacion(Integer.valueOf(editNumero_Evaluacion.getText().toString()));
            evaluacion.setNumero_tema(Integer.valueOf(editNumero_Tema.getText().toString()));
            evaluacion.setNombre_evaluacion(editNombre_Evaluacion.getText().toString());
            evaluacion.setNota_global(Double.valueOf(editNota_Global.getText().toString()));

            helper.abrir();
            String estado = helper.actualizar(evaluacion);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editNumero_Evaluacion.setText("");
        editNumero_Tema.setText("");
        editNombre_Evaluacion.setText("");
        editNota_Global.setText("");
    }
}
