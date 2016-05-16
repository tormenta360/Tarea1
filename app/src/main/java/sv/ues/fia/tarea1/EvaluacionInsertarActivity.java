package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EvaluacionInsertarActivity extends Activity {

    ControlBD helper;
    EditText editNumero_Evaluacion;
    EditText editNumero_Tema;
    EditText editNombre_Evaluacion;
    EditText editNota_Global;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_insertar);

        helper = new ControlBD(this);
        editNumero_Evaluacion = (EditText) findViewById(R.id.editNumero_Evaluacion);
        editNumero_Tema = (EditText) findViewById(R.id.editNumero_Tema);
        editNombre_Evaluacion = (EditText) findViewById(R.id.editNombre_Evaluacion);
        editNota_Global = (EditText) findViewById(R.id.editNota_Global);
    }

    public void insertarEvaluacion(View v) {

        try {
            String regInsertados;
            int numero_evaluacion = Integer.valueOf(editNumero_Evaluacion.getText().toString());
            int numero_tema = Integer.valueOf(editNumero_Tema.getText().toString());
            String nombre_evaluacion = editNombre_Evaluacion.getText().toString();
            int nota_global = Integer.valueOf(editNota_Global.getText().toString());

            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setNumero_evaluacion(numero_evaluacion);
            evaluacion.setNumero_tema(numero_tema);
            evaluacion.setNombre_evaluacion(nombre_evaluacion);
            evaluacion.setNota_global(nota_global);
            helper.abrir();
            regInsertados = helper.insertar(evaluacion);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

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
