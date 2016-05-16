package sv.ues.fia.tarea1;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuGrupoActivity extends ListActivity {

    private ArrayList<String> resultados= new ArrayList<>();
    ControlBD helper;
    String id;


    String[] opcionCrud = {"Insertar Grupo", "Eliminar Grupo", "Consultar Grupo","Actualizar Grupo"};
    String[] activities = {"InsertarGrupoActivity", "EliminarGrupoActivity", "ConsultarGrupoActivity","ActualizarGrupoActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        //setContentView(R.layout.activity_menu_empresa);
        id = bundle.getString("idusuario");
        try{
            helper=new ControlBD(this);
            Cursor c = helper.obtenerSubMenu(id,"07_");
            if(c!=null) {
                if (c.moveToFirst())
                    do {
                        String desOpcion = c.getString(0);
                        resultados.add(desOpcion);
                    } while (c.moveToNext());
                c.close();
            }
        }catch (SQLiteException e){
            Log.e(getClass().getSimpleName(), "No se pudo crear o abrir la base de datos");
        }
        helper.cerrar();
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(118, 198, 174));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,resultados);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nombreValue="";
        String r = resultados.get(position);

        for (int i=0;i<opcionCrud.length;i++)
            if (r.compareTo(opcionCrud[i])==0){
                nombreValue=activities[i];
                break;
            }
        try{
            Class<?>
                    clase=Class.forName("sv.ues.fia.tarea1."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
