package sv.ues.fia.tarea1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuEstudianteActivity extends ListActivity {

    String[] menu = {"Insertar Estudiante", "Eliminar Estudiante", "Consultar Estudiante","Actualizar Estudiante"};
    String[] activities = {"InsertarEstudianteActivity", "EliminarEstudianteActivity", "ConsultarEstudianteActivity","ActualizarEstudianteActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nombreValue = activities[position];

        try {
            Class<?> clase = Class.forName("sv.ues.fia.tarea1." + nombreValue);
            Intent inte = new Intent(this, clase);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
