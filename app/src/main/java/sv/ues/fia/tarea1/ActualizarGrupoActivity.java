package sv.ues.fia.tarea1;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Calendar;

public class ActualizarGrupoActivity extends AppCompatActivity {

    NumberPicker npintegrantes;
    NumberPicker npaño;
    EditText etcodigo;
    ControlBD helper;
    Calendar fecha = Calendar.getInstance();
    int año = fecha.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_grupo);
        helper = new ControlBD(this);
        npintegrantes = (NumberPicker) findViewById(R.id.npagintegrantes);
        npaño = (NumberPicker)findViewById(R.id.npagaño);
        etcodigo = (EditText) findViewById(R.id.etagcodigo);
        npintegrantes.setMinValue(1);
        npaño.setMinValue(2000);
        npintegrantes.setMaxValue(5);
        npaño.setMaxValue(año);
    }

    public void actualizarGrupo(View v) {
        try {
            Grupo grupo = new Grupo();
            grupo.setCodigo_grupo(Integer.parseInt(etcodigo.getText().toString()));
            grupo.setNumero_integrantes(npintegrantes.getValue());
            grupo.setAño(npaño.getValue());

            helper.abrir();

            String estado = helper.actualizar(grupo);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTextoG(View v) {
        etcodigo.setText("");
        npintegrantes.setValue(1);
        npaño.setValue(2000);

    }
}
