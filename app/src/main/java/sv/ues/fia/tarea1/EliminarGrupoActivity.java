package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarGrupoActivity extends AppCompatActivity {

    EditText codigo;
    ControlBD controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_grupo);
        controlhelper=new ControlBD(this);
        codigo=(EditText)findViewById(R.id.etegcodigo);
    }
    public void eliminarGrupo(View v){
        try {
            String regEliminadas;
            Grupo grupo =new Grupo();
            grupo.setCodigo_grupo(Integer.parseInt(codigo.getText().toString()));
            controlhelper.abrir();
            regEliminadas=controlhelper.eliminar(grupo);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
