package sv.ues.fia.tarea1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class InsertarGrupoActivity extends AppCompatActivity {

    ControlBD helper;
    NumberPicker npintegrantes;
    NumberPicker npaño;
    Button bingresar;
    Button brestablecer;
    TextView t;
    Calendar fecha = Calendar.getInstance();
    int año = fecha.get(Calendar.YEAR);


    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_insertar_grupo);
            helper = new ControlBD(this);
            npintegrantes = (NumberPicker) findViewById(R.id.npigintegrantes);
            npaño = (NumberPicker) findViewById(R.id.npigaño);
            t = (TextView) findViewById(R.id.tvig);
            bingresar = (Button) findViewById(R.id.biginsertar);
            brestablecer = (Button) findViewById(R.id.bigrestablecer);
            npintegrantes.setMaxValue(5);
            npintegrantes.setMinValue(1);
            npintegrantes.setWrapSelectorWheel(true);
            npaño.setMaxValue(año);
            npaño.setMinValue(2000);
            npintegrantes.setWrapSelectorWheel(true);
        } catch (Exception e) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }

    }

    public void insertarGrupo(View v) {

        int nointegrantes = npintegrantes.getValue();
        int año = npaño.getValue();
        String regInsertados;

        Grupo grupo = new Grupo();
        grupo.setNumero_integrantes(nointegrantes);
        grupo.setAño(año);

        helper.abrir();
        regInsertados = helper.insertar(grupo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void restablecer(View v) {

        npintegrantes.setValue(1);
        npaño.setValue(2000);

    }

}
