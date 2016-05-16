package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarGrupoActivity extends AppCompatActivity {

    ControlBD helper;
    EditText codigo;
    EditText integrantes;
    EditText año;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_grupo);
        helper = new ControlBD(this);
        codigo = (EditText) findViewById(R.id.etcgcodigo);
        integrantes = (EditText) findViewById(R.id.etcgintegrantes);
        año = (EditText) findViewById(R.id.etcgaño);

    }
    public void consultarGrupo(View v) {
        try {
            helper.abrir();
            Grupo grupo = helper.consultarGrupo(Integer.parseInt(codigo.getText().toString()));
            helper.cerrar();
            if(grupo == null)
                Toast.makeText(this, "Grupo: " + codigo.getText().toString() +" no encontrado", Toast.LENGTH_LONG).show();
            else{
                integrantes.setText(String.valueOf(grupo.getNumero_integrantes()));
                año.setText(String.valueOf(grupo.getAño()));

            }
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
