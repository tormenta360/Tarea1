package sv.ues.fia.tarea1;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PTGActivity1 extends ListActivity {



    // String[] menu={"Tabla Propuesta Perfil","Tabla Tipo Proyecto","LLenar Base de Datos"};
    //String[] activities={"PropuestaPerfilMenuActivity","TipoProyectoMenuActivity"};

    String[] activities = {"DocenteMenu","DetalleDocenteMenu","PropuestaPerfilMenuActivity","TipoProyectoMenuActivity",
                           "EvaluacionMenuActivity", "Detalle_EvaluacionMenuActivity", "Local_EvaluacionMenuActivity",
                           "MenuEstudianteActivity", "MenuGrupoActivity", "MenuEstudianteGrupoActivity"};


    String[] OpcionCrud = {"Menu Docente","Menu Detalle Docente",
                         "Tabla Propuesta Perfil","Tabla Tipo Proyecto",
                         "Tabla Evaluación", "Tabla Detalle Evaluación",
                         "Tabla Local Evaluación","Tabla Estudiante",
                         "Tabla Grupo","Estudiante-Grupo",
                         "LLenar Base de Datos"};


    private ArrayList<String> resultados = new ArrayList<>();
    ControlBD helper;
    String username,idUser;
    TextView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c = (TextView) findViewById(R.id.cabecera);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("nousuario");
        idUser = bundle.getString("idusuario");
        try {
            helper = new ControlBD(this);
            Cursor c = helper.obtenerMenuUsuario(idUser);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String desOpcion = c.getString(0);
                        resultados.add(desOpcion);
                    } while (c.moveToNext());
                }
            }
            c.close();
        }catch (SQLiteException e) {
            Log.e(getClass().getSimpleName(), "No se pudo crear o abrir la base de datos");
        }

        helper.cerrar();
        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(118, 198, 174));
        //listView.addHeaderView(c);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultados);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue = "";
        String r = resultados.get(position);        //paso el contenido del array a una variable string para luego comparar variables del mismo tipo

        for(int i=0;i<OpcionCrud.length;i++){       //recorre el vector OpciónCrud para encontrar la incidencia y lanzar la activity correcta
            if (r.equals(OpcionCrud[i])){           //si la string en la posicion es la misma a la de OpcionCrud[i] guardare
                nombreValue = activities[i];        //el nombre de la actividad correspondiente a esa posición
                break;
            }
        }

        try {
            Class<?>
                    clase=Class.forName("sv.ues.fia.tarea1."+nombreValue);
            Intent inte = new Intent(this,clase);
            inte.putExtra("idusuario",idUser);
            this.startActivity(inte);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
