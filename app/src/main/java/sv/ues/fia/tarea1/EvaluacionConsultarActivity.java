package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EvaluacionConsultarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_Evaluacion;
    EditText editNumero_Tema;
    EditText editNombre_Evaluacion;
    EditText editNota_Global;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_consultar);

        helper = new ControlBD(this);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editNumero_Tema = (EditText) findViewById(R.id.editNumero_Tema);
        editNombre_Evaluacion = (EditText) findViewById(R.id.editNombre_Evaluacion);
        editNota_Global = (EditText) findViewById(R.id.editNota_Global);
    }

    public void consultarEvaluacion(View v){

        try{
            helper.abrir();

            Evaluacion evaluacion = helper.consultarEvaluacion(Integer.valueOf(editNumero_Evaluacion.getText().toString()), Integer.valueOf(editNumero_Tema.getText().toString()));

            helper.cerrar();

            if (evaluacion == null)
                Toast.makeText(this, "Evaluacion N° " + editNumero_Evaluacion.getText().toString() + " y tema N° " + editNumero_Tema.getText().toString() + " no encontrado.", Toast.LENGTH_LONG).show();
            else {
                editNombre_Evaluacion.setText(evaluacion.getNombre_evaluacion());
                editNota_Global.setText(String.valueOf(evaluacion.getNota_global()));
            }
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editNumero_Evaluacion.setText("");
        editNumero_Tema.setText("");
    }
}
