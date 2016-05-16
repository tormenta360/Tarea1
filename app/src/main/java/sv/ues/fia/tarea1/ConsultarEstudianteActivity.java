package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarEstudianteActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editCarnet;
    EditText editNombres;
    EditText editApellidos;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_estudiante);
        helper = new ControlBD(this);
        editCarnet = (EditText) findViewById(R.id.etcecarnet);
        editNombres = (EditText) findViewById(R.id.etcenombres);
        editApellidos = (EditText) findViewById(R.id.etceapellidos);

    }
    public void consultarAlumno(View v) {
        try {
            helper.abrir();
            Estudiante estudiante = helper.consultarEstudiante(editCarnet.getText().toString());
            helper.cerrar();
            if(estudiante == null)
                Toast.makeText(this, "Alumno con carnet " + editCarnet.getText().toString() +
                        " no encontrado", Toast.LENGTH_LONG).show();
            else{
                editNombres.setText(estudiante.getNombres());
                editApellidos.setText(estudiante.getApellidos());

            }
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v){
        editCarnet.setText("");
        editNombres.setText("");
        editApellidos.setText("");

    }
}
