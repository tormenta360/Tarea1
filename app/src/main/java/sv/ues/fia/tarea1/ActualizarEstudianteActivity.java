package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarEstudianteActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editCarnet;
    EditText editNombre;
    EditText editApellido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_estudiante);
        helper = new ControlBD(this);
        editCarnet = (EditText) findViewById(R.id.etaecarnet);
        editNombre = (EditText) findViewById(R.id.etaenombres);
        editApellido = (EditText) findViewById(R.id.etaeapellidos);

    }
    public void actualizarEstudiante(View v) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setCarnet(editCarnet.getText().toString());
            estudiante.setNombres(editNombre.getText().toString());
            estudiante.setApellidos(editApellido.getText().toString());

            helper.abrir();
            String estado = helper.actualizar(estudiante);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        editCarnet.setText("");
        editNombre.setText("");
        editApellido.setText("");

    }
}
