package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarEstudianteGrupoActivity extends AppCompatActivity {

    ControlBD helper;
    EditText carnet;
    EditText codigo;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_estudiante_grupo);
        helper = new ControlBD(this);
        carnet = (EditText) findViewById(R.id.etmegcarnet);
        codigo = (EditText) findViewById(R.id.etmegcodigo);

    }
    public void actualizarEstudianteGrupo(View v) {
        try {
            EstudianteGrupo eg = new EstudianteGrupo();
            eg.setCarnet(carnet.getText().toString());
            eg.setCodigoGrupo(Integer.parseInt(codigo.getText().toString()));

            helper.abrir();
            String estado = helper.actualizar(eg);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarEG(View v) {
        carnet.setText("");
        codigo.setText("");

    }

}
