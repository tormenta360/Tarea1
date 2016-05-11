package sv.ues.fia.tarea1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TruenoBlanco on 7/5/2016.
 */
public class ControlBD {

    private static final String[]camposDocente = new String [] {"codigo_docente","nombres_docente","apellidos_docente","escuela"};
    private static final String[]camposDetalleDocente = new String [] {"codigo_docente","codigo_grupo","tipo_rol","nombres_docente"};


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }

    public Docente consultarDocente(String codigo_docente) {
        String[] id = {codigo_docente};

        Cursor cursor = db.query("docente", camposDocente, "codigo_docente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Docente docente = new Docente();
            docente.setCodigoDocente(cursor.getString(0));
            docente.setNombreDocente(cursor.getString(1));
            docente.setApellidoDocente(cursor.getString(2));
            docente.setEscuela(cursor.getString(3));
            return docente;
        }else{
            return null;
        }
    }

    public String actualizar(Docente docente){

        if(verificarIntegridad(docente, 1)){

            String[] id = {docente.getCodigoDocente()};
            ContentValues cv = new ContentValues();
            cv.put("nombres_docente", docente.getNombreDocente());
            cv.put("apellidos_docente", docente.getApellidoDocente());
            cv.put("escuela", docente.getEscuela());
            db.update("docente", cv, "codigo_docente = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigo_docente " + docente.getCodigoDocente() + " no existe";
        }
    }

    public String eliminar(Docente docente){
        //Eliminacion en cascada
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(docente,2)) {
            contador+=db.delete("detalle_docente", "codigo_docente='"+docente.getCodigoDocente()+"'", null);
        }
        contador+=db.delete("docente", "codigo_docente='"+docente.getCodigoDocente()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(DetalleDocente detalleDocente) {
        //Eliminacion en cascada
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(detalleDocente,4)) {
            contador+=db.delete("docente", "codigo_docente='"+detalleDocente.getCodigoDocente()+"'", null);
        }
        contador+=db.delete("detalle_docente", "codigo_docente='"+detalleDocente.getCodigoDocente()+"'", null);
        regAfectados+=contador;
        return regAfectados;

    }

    public DetalleDocente consultarDetalleDocente(String codigo_docente) {
        String[] id = {codigo_docente};

        Cursor cursor = db.query("detalle_docente", camposDetalleDocente, "codigo_docente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DetalleDocente detalle_docente = new DetalleDocente();
            detalle_docente.setCodigoDocente(cursor.getString(0));
            detalle_docente.setCodigoGrupo(cursor.getInt(1));
            detalle_docente.setTipoRol(cursor.getString(2));
            detalle_docente.setNombreDocente(cursor.getString(3));
            return detalle_docente;
        }else{
            return null;
        }
    }

    public String actualizar(DetalleDocente detalleDocente) {
        if(verificarIntegridad(detalleDocente, 3)){

            String[] id = {detalleDocente.getCodigoDocente()};
            ContentValues cv = new ContentValues();
            cv.put("codigo_docente", detalleDocente.getCodigoDocente());
            cv.put("codigo_grupo", detalleDocente.getCodigoGrupo());
            cv.put("tipo_rol", detalleDocente.getTipoRol());
            cv.put("nombres_docente", detalleDocente.getNombreDocente());
            db.update("detalle_docente", cv, "codigo_docente = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigo_docente " + detalleDocente.getCodigoDocente() + " no existe";
        }
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "proyecto1.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE docente(codigo_docente VARCHAR(7) NOT NULL PRIMARY KEY, nombres_docente VARCHAR(30) NOT NULL,apellidos_docente VARCHAR(30) NOT NULL,escuela VARCHAR(30) NOT NULL);");
                db.execSQL("CREATE TABLE detalle_docente(codigo_docente VARCHAR(6) NOT NULL PRIMARY KEY,codigo_grupo VARCHAR(30) NOT NULL,tipo_rol VARCHAR(30) NOT NULL,nombres_docente VARCHAR(30) NOT NULL);");

            }catch(SQLException e){
                e.printStackTrace();

            }

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }

    public String insertar(Docente docente){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        if (verificarIntegridad(docente,1)) {
            regInsertados= "Error al Insertar docente, ya existe un docente con ese codigo_docente";
        }
        else
        {
            ContentValues doc = new ContentValues();
            doc.put("codigo_docente", docente.getCodigoDocente());
            doc.put("nombres_docente", docente.getNombreDocente());
            doc.put("apellidos_docente", docente.getApellidoDocente());
            doc.put("escuela", docente.getEscuela());
            contador=db.insert("docente", null, doc);
            regInsertados=regInsertados+contador;

        }

        return regInsertados;
    }

    public String insertar(DetalleDocente detalle_docente){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        if (verificarIntegridad(detalle_docente,4)) {
            regInsertados= "Error al ingresar, verifique si el codigo_docente docente y codigo_docente grupo estan ingresados";
        }
        else
        {
            ContentValues doc = new ContentValues();
            doc.put("codigo_docente", detalle_docente.getCodigoDocente());
            doc.put("codigo_grupo", detalle_docente.getCodigoGrupo());
            doc.put("tipo_rol", detalle_docente.getTipoRol());
            doc.put("nombres_docente", detalle_docente.getNombreDocente());
            contador=db.insert("detalle_docente", null, doc);
            regInsertados=regInsertados+contador;

        }

        return regInsertados;
    }



    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{

        switch(relacion){

            case 1:
            {
                //verificar que exista docente
                Docente docente = (Docente)dato;
                String[] id = {docente.getCodigoDocente()};
                abrir();
                Cursor c2 = db.query("docente", null, "codigo_docente = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Docente
                    return true;
                }
                return false;
            }
            case 2:
            {
                Docente docente = (Docente)dato;
                Cursor c=db.query(true, "detalle_docente", new String[] {"codigo_docente" }, "codigo_docente='"+docente.getCodigoDocente()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 3:
            {
                //verificar que exista codigo_docente de docente en Detalle docente
                DetalleDocente detalleDocente2 = (DetalleDocente)dato;
                String[] idm = {detalleDocente2.getCodigoDocente()};
                abrir();
                Cursor cm = db.query("detalle_docente", null, "codigo_docente = ?", idm, null, null, null);
                if(cm.moveToFirst()){
                    //Se encontro Materia
                    return true;
                }
                return false;
            }
            case 4: {
                //verifica que exista codigo_grupo en tabla grupo y codigo_docente en la tabla docente
                DetalleDocente detalleDocente = (DetalleDocente) dato;
                String[] id1 = {detalleDocente.getCodigoDocente()};
                String[] id2 = {Integer.toString(detalleDocente.getCodigoGrupo())};
                //abrir();

                try {
                    Cursor cursor1 = db.query("docente", null, "codigo_docente = ?", id1, null, null, null);
                    //Cursor cursor2 = null;
                    Cursor cursor2 = db.query("grupo", null, "codigo_grupo = ?", id2, null, null, null);

                    if (cursor1.moveToFirst() && cursor2.moveToFirst()) {
                        //Se encontraron datos
                        return true;
                    }
                }
                catch (Exception e) {
                    return false;
                }

            }


            default:
                return false;


        }
    }

    public String llenarBD(){

        final String[] VDcodigo_docente = {"LV10022","SC12054"};
        final String[] VDnombre = {"Walter","Cristian"};
        final String[] VDapellido = {"Lemus","Sosa"};
        final String[] VDescuela = {"Sistemas","Quimica"};
        final int[] VDcodigo_grupo = {01,02};
        final String[] VDtipo_rol = {"Jurado","Docente"};

        abrir();
        db.execSQL("DELETE FROM docente");
        db.execSQL("DELETE FROM detalle_docente");

        Docente docente = new Docente();
        for(int i=0;i<2;i++){
            docente.setCodigoDocente(VDcodigo_docente[i]);
            docente.setNombreDocente(VDnombre[i]);
            docente.setApellidoDocente(VDapellido[i]);
            docente.setEscuela(VDescuela[i]);
            insertar(docente);
        }

        DetalleDocente detalleDocente = new DetalleDocente();
        for(int i=0;i<2; i++) {
            detalleDocente.setCodigoDocente(VDcodigo_docente[i]);
            detalleDocente.setCodigoGrupo(VDcodigo_grupo[i]);
            detalleDocente.setTipoRol(VDtipo_rol[i]);
            detalleDocente.setNombreDocente(VDnombre[i]);
            insertar(detalleDocente);
        }
        cerrar();
        return "Tablas LLenadas con exito";
    }


}
