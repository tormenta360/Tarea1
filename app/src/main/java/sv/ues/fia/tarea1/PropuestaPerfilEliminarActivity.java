package sv.ues.fia.tarea1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PropuestaPerfilEliminarActivity extends Activity {

    EditText editNumero_tema;
    EditText editCodigo_grupo;
    ControlBD controlhelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propuesta_perfil_eliminar);
        controlhelper=new ControlBD(this);
        editNumero_tema=(EditText)findViewById(R.id.editNumero_tema);
        editCodigo_grupo=(EditText)findViewById(R.id.editCodigo_grupo);
    }

    public void eliminarPropuesta_Perfil(View v){

        try{

        String regEliminadas;
        PropuestaPerfil propuestaPerfil=new PropuestaPerfil();
        propuestaPerfil.setNumero_tema(Integer.valueOf(editNumero_tema.getText().toString()));
        propuestaPerfil.setCodigo_grupo(Integer.valueOf(editCodigo_grupo.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(propuestaPerfil);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        } catch(Exception e){
            Toast.makeText(this, "Ingrese todo los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiar(View v){
        editNumero_tema.setText("");
        editCodigo_grupo.setText("");
    }
}
