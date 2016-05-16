package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarEstudianteGrupoActivity extends AppCompatActivity {

    EditText codigo;
    EditText carnet;

    ControlBD controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_estudiante_grupo);
        controlhelper=new ControlBD(this);
        codigo=(EditText)findViewById(R.id.eteegcodigo);
        carnet=(EditText)findViewById(R.id.eteegcarnet);
    }
    public void eliminarEstudianteGrupo(View v){
        try {
            String regEliminadas;
            EstudianteGrupo eg =new EstudianteGrupo();
            eg.setCodigoGrupo(Integer.parseInt(codigo.getText().toString()));
            eg.setCarnet(carnet.getText().toString());
            controlhelper.abrir();
            regEliminadas=controlhelper.eliminar(eg);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarEEG(View v){
        codigo.setText("");
        carnet.setText("");
    }
}
