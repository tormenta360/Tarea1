package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoProyectoEliminarActivity extends Activity {

    EditText editNumero_tema;
    ControlBD controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_proyecto_eliminar);
        controlhelper=new ControlBD(this);
        editNumero_tema=(EditText)findViewById(R.id.editNumero_tema);
    }

    public void eliminarTipo_Proyecto(View v){

        try{

        String regEliminadas;
        TipoProyecto tipoProyecto=new TipoProyecto();
        tipoProyecto.setNumero_tema(Integer.valueOf(editNumero_tema.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(tipoProyecto);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        } catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiar(View v){
        editNumero_tema.setText("");
    }
}
