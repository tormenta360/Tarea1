package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarEstudianteActivity extends AppCompatActivity {

    ControlBD helper;
    EditText etcartnete;
    EditText etnombrese;
    EditText etapellidose;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_estudiante);
        helper = new ControlBD(this);
        etcartnete = (EditText) findViewById(R.id.etcarnete);
        etnombrese = (EditText) findViewById(R.id.etnombrese);
        etapellidose = (EditText) findViewById(R.id.etapellidose);

    }
    public void insertarEstudiante(View v) {
        try {
            String carnet= etcartnete.getText().toString();
            String nombres = etnombrese.getText().toString();
            String apellidos = etapellidose.getText().toString();
            String regInsertados;

            Estudiante estudiante = new Estudiante();
            estudiante.setCarnet(carnet);
            estudiante.setNombres(nombres);
            estudiante.setApellidos(apellidos);

            helper.abrir();
            regInsertados = helper.insertar(estudiante);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v) {
        etcartnete.setText("");
        etnombrese.setText("");
        etapellidose.setText("");

    }
}
