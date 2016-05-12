package sv.ues.fia.tarea1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DocenteMenu extends ListActivity {

    String[] menu={"Insertar Docente","Eliminar Docente","Consultar Docente","Actualizar Docente"};
    String[] activities={"DocenteInsertar","DocenteEliminar","DocenteConsultar", "DocenteActualizar"};
    String[] menu1={"Insertar Detalles de Docente","Consultar Detalles de Docente","Actualizar Detalles de Docente"};
    Contact c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* if(c.getRol()==1){

        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu1);
        setListAdapter(adapter);
        }else{*/
            ArrayAdapter<String> adapter = new
                    ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
            setListAdapter(adapter);
        //}
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];

        try{
            Class<?> clase=Class.forName("sv.ues.fia.tarea1."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
