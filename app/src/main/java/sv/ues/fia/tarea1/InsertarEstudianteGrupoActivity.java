package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarEstudianteGrupoActivity extends AppCompatActivity {

    ControlBD helper;
    EditText carnet;
    EditText codigoGrupo;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_estudiante_grupo);
        helper = new ControlBD(this);
        carnet = (EditText) findViewById(R.id.etiegcarnet);
        codigoGrupo = (EditText) findViewById(R.id.etiegcodigo);

    }

    public void insertarEstudianteGrupo(View v) {

        try {
            String carn = carnet.getText().toString();
            String cod = codigoGrupo.getText().toString();
            String regInsertados;

            EstudianteGrupo eg = new EstudianteGrupo();
            eg.setCarnet(carn);
            eg.setCodigoGrupo(Integer.parseInt(cod));

            helper.abrir();
            regInsertados = helper.insertar(eg);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }

    }

    public void restablecer(View v) {

        carnet.setText("");
        codigoGrupo.setText("");

    }
}
