package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarEstudianteGrupoActivity extends AppCompatActivity {

    ControlBD helper;
    EditText carnet;
    EditText codigo;
    EditText nombre;
    EditText apellido;
    EditText integrantes;
    EditText año;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_estudiante_grupo);
        helper = new ControlBD(this);
        carnet = (EditText) findViewById(R.id.etcegcarnet);
        codigo = (EditText) findViewById(R.id.etcegcodigo);
        nombre = (EditText) findViewById(R.id.etcegnombre);
        apellido = (EditText) findViewById(R.id.etcegapellido);
        integrantes = (EditText) findViewById(R.id.etcegintegrantes);
        año = (EditText) findViewById(R.id.etcegaño);

    }
    public void consultarEstudianteGrupo(View v) {
        try {
            helper.abrir();
            EstudianteGrupo eg = helper.consultarEstudianteGrupo(carnet.getText().toString(), Integer.parseInt(codigo.getText().toString()));
            helper.cerrar();
            if(eg == null)
                Toast.makeText(this, "El estudiante: " + carnet.getText().toString() +" no se encuentra en el grupo: "+codigo.getText().toString(), Toast.LENGTH_LONG).show();
            else{
                nombre.setText(eg.getNombres());
                apellido.setText(eg.getApellidos());
                integrantes.setText(String.valueOf(eg.getIntegrantes()));
                año.setText(String.valueOf(eg.getAño()));

            }
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    public void limpiarCEG(View v){
        carnet.setText("");
        nombre.setText("");
        apellido.setText("");
        integrantes.setText("");
        año.setText("");
        codigo.setText("");

    }
}
