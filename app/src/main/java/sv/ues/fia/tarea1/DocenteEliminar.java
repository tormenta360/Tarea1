package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DocenteEliminar extends Activity {

    EditText editCodigo;
    ControlBD controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);
        controlhelper=new ControlBD (this);
        editCodigo=(EditText)findViewById(R.id.editCodigo);
    }
    public void eliminarDocente(View v){
        String regEliminadas;
        Docente docente=new Docente();
        docente.setCodigoDocente(editCodigo.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(docente);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
