package sv.ues.fia.tarea1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleDocenteEliminar extends Activity {

    EditText editCodigo;
    ControlBD controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_eliminar);
        controlhelper=new ControlBD (this);
        editCodigo=(EditText)findViewById(R.id.editCodigo);
    }
    public void eliminarDetalleDocente(View v){
        String regEliminadas;
        DetalleDocente detalleDocente=new DetalleDocente();
        detalleDocente.setCodigoDocente(editCodigo.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(detalleDocente);
        controlhelper.cerrar();
        if(regEliminadas.equals(true)){
            Toast.makeText(this, "Error al eliminar, Falta registro en tabla docente o grupo", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, regEliminadas, Toast.LENGTH_LONG).show();
        }
    }
}
