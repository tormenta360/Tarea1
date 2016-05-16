package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarEstudianteActivity extends AppCompatActivity {

    EditText editCarnet;
    ControlBD controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_estudiante);
        controlhelper=new ControlBD(this);
        editCarnet=(EditText)findViewById(R.id.eteecarnet);
    }
    public void eliminar(View v){
        try {
            String regEliminadas;
            Estudiante alumno =new Estudiante();
            alumno.setCarnet(editCarnet.getText().toString());
            controlhelper.abrir();
            regEliminadas=controlhelper.eliminar(alumno);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
