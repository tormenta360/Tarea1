package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EvaluacionEliminarActivity extends Activity {

    EditText editNumero_Evaluacion;
    ControlBD controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_eliminar);
        controlhelper=new ControlBD(this);
        editNumero_Evaluacion = (EditText)findViewById(R.id.editNumero_Evaluacion);
        //editNumero_Tema=(EditText)findViewById(R.id.editNumero_Tema);
    }

    public void eliminarEvaluacion(View v){
        try{
        String regEliminadas;

            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setNumero_evaluacion(Integer.valueOf(editNumero_Evaluacion.getText().toString()));
            //evaluacion.setNumero_tema(Integer.valueOf(editNumero_Tema.getText().toString()));
            controlhelper.abrir();
            regEliminadas = controlhelper.eliminar(evaluacion);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar(View v){
        editNumero_Evaluacion.setText("");
        //editNumero_Tema.setText("");
    }
}