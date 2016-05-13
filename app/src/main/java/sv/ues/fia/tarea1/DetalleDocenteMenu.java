package sv.ues.fia.tarea1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DetalleDocenteMenu extends ListActivity {

    String[] menu={"Insertar Detalles de Docente","Eliminar Detalles de Docente","Consultar Detalles de Docente",
            "Actualizar Detalles de Docente"};

    String[] menu1={"Insertar Detalles de Docente","Consultar Detalles de Docente","Actualizar Detalles de Docente"};

    String[] activities={"DetalleDocenteInsertar","DetalleDocenteEliminar","DetalleDocenteConsultar",
            "DetalleDocenteActualizar"};

    Contact c = new Contact();
    SQLiteDatabase db;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));

        String username = getIntent().getStringExtra("Username");
        Toast.makeText(this,username,Toast.LENGTH_SHORT).show();

       if(username.equals("walter5lemus")) {
           ArrayAdapter<String> adapter = new
                   ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu1);
            setListAdapter(adapter);
        }else {
            ArrayAdapter<String> adapter = new
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
            setListAdapter(adapter);

        }
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
