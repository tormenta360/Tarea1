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

    private static final String[]camposDocente = new String [] {"codigodocente","nombredocente","apellidodocente","escuela"};
    private static final String[]camposDetalleDocente = new String [] {"codigo","codigogrupo","tiporol","nombredocente"};


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }

    public Docente consultarDocente(String codigo) {
        String[] id = {codigo};

        Cursor cursor = db.query("docente", camposDocente, "codigodocente = ?", id, null, null, null);
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
            cv.put("nombredocente", docente.getNombreDocente());
            cv.put("apellidodocente", docente.getApellidoDocente());
            cv.put("escuela", docente.getEscuela());
            db.update("docente", cv, "codigodocente = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigo " + docente.getCodigoDocente() + " no existe";
        }
    }

    public String eliminar(Docente docente){
        //Eliminacion en cascada
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(docente,2)) {
            contador+=db.delete("detalledocente", "codigo='"+docente.getCodigoDocente()+"'", null);
        }
        contador+=db.delete("docente", "codigodocente='"+docente.getCodigoDocente()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(DetalleDocente detalleDocente) {
        //Eliminacion en cascada
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("detalledocente", "codigo='"+detalleDocente.getCodigoDocente()+"'", null);
        regAfectados+=contador;
        return regAfectados;

    }

    public DetalleDocente consultarDetalleDocente(String codigo) {
        String[] id = {codigo};

        Cursor cursor = db.query("detalledocente", camposDetalleDocente, "codigo = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            DetalleDocente detalledocente = new DetalleDocente();
            detalledocente.setCodigoDocente(cursor.getString(0));
            detalledocente.setCodigoGrupo(cursor.getInt(1));
            detalledocente.setTipoRol(cursor.getString(2));
            detalledocente.setNombreDocente(cursor.getString(3));

            return detalledocente;
        }else{
            return null;
        }
    }

    public String actualizar(DetalleDocente detalleDocente) {
        if(verificarIntegridad(detalleDocente, 3)){

            String[] id = {detalleDocente.getCodigoDocente()};
            ContentValues cv = new ContentValues();
            cv.put("codigo", detalleDocente.getCodigoDocente());
            cv.put("codigogrupo", detalleDocente.getCodigoGrupo());
            cv.put("tiporol", detalleDocente.getTipoRol());
            cv.put("nombredocente",detalleDocente.getNombreDocente());

            db.update("detalledocente", cv, "codigo = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigo " + detalleDocente.getCodigoDocente() + " no existe";
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
                db.execSQL("CREATE TABLE docente(codigodocente VARCHAR(7) NOT NULL PRIMARY KEY, nombredocente VARCHAR(30),apellidodocente VARCHAR(30),escuela VARCHAR(30));");
                db.execSQL("CREATE TABLE detalledocente(codigo VARCHAR(7) NOT NULL PRIMARY KEY,codigogrupo INTEGER,tiporol VARCHAR(30),nombredocente VARCHAR(30));");

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
            regInsertados= "Error al Insertar docente, ya existe un docente con ese codigo";
        }
        else
        {
            ContentValues doc = new ContentValues();
            doc.put("codigodocente", docente.getCodigoDocente());
            doc.put("nombredocente", docente.getNombreDocente());
            doc.put("apellidodocente", docente.getApellidoDocente());
            doc.put("escuela", docente.getEscuela());
            contador=db.insert("docente", null, doc);
            regInsertados=regInsertados+contador;

        }

        return regInsertados;
    }

    public String insertar(DetalleDocente detalledocente){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        if(verificarIntegridad(detalledocente,4)){

            ContentValues alum = new ContentValues();
            alum.put("codigo", detalledocente.getCodigoDocente());
            alum.put("codigogrupo", detalledocente.getCodigoGrupo());
            alum.put("tiporol", detalledocente.getTipoRol());
            alum.put("nombredocente", detalledocente.getNombreDocente());
            contador = db.insert("detalledocente", null, alum);
            if (contador == -1 || contador == 0) {
                regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            } else {
                regInsertados = regInsertados + contador;
            }

            //}
        }
        else{

            regInsertados = "no existe el codigo docente";
        }


        return regInsertados;
    }



    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{

        switch(relacion){

            case 1:
            {
                //verificar que exista docente
                Docente docente2 = (Docente)dato;
                String[] id = {docente2.getCodigoDocente()};
                abrir();
                Cursor c2 = db.query("docente", null, "codigodocente = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Docente
                    return true;
                }
                return false;
            }
            case 2:
            {
                Docente docente = (Docente)dato;
                Cursor c=db.query(true, "detalledocente", new String[] {"codigo" }, "codigo='"+docente.getCodigoDocente()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 3:
            {
                //verificar que exista codigo de docente en Detalle docente
                DetalleDocente detalleDocente2 = (DetalleDocente)dato;
                String[] idm = {detalleDocente2.getCodigoDocente()};
                abrir();
                Cursor cm = db.query("detalledocente", null, "codigo = ?", idm, null, null, null);
                if(cm.moveToFirst()){
                    //Se encontro Materia
                    return true;
                }
                return false;
            }
            case 4: {
                //verifica que al insertar detalle docente exista codigo_grupo en tabla grupo y codigo_docente en la tabla docente
                DetalleDocente detalleDocente = (DetalleDocente) dato;
                String[] id1 = {detalleDocente.getCodigoDocente()};
                String[] id2 = {String.valueOf(detalleDocente.getCodigoGrupo())};
                //abrir();

                Cursor cursor1 = db.query("docente", null, "codigodocente = ?", id1, null, null, null);
                // Cursor cursor2 = null;
                //Cursor cursor2 = db.query("grupo", null, "codigo_grupo = ?", id2, null, null, null);

                if (cursor1.moveToFirst() /*&& cursor2.moveToFirst()*/){
                    //Se encontraron datos
                    return true;

                }else{
                    return false;
                }


            }






            default:
                return false;


        }
    }

    public String llenarBD(){

        final String[] VDcodigo = {"LV10022","SC12054"};
        final String[] VDnombre = {"Walter","Cristian"};
        final String[] VDapellido = {"Lemus","Sosa"};
        final String[] VDescuela = {"Sistemas","Quimica"};
        final int[] VDcodigogrupo = {01,02};
        final String[] VDtiporol = {"Jurado","Docente"};

        abrir();
        db.execSQL("DELETE FROM docente");
        db.execSQL("DELETE FROM detalledocente");

        Docente docente = new Docente();
        for(int i=0;i<2;i++){
            docente.setCodigoDocente(VDcodigo[i]);
            docente.setNombreDocente(VDnombre[i]);
            docente.setApellidoDocente(VDapellido[i]);
            docente.setEscuela(VDescuela[i]);
            insertar(docente);
        }

        DetalleDocente detalleDocente = new DetalleDocente();
        for(int i=0;i<2; i++) {
            detalleDocente.setCodigoDocente(VDcodigo[i]);
            detalleDocente.setCodigoGrupo(VDcodigogrupo[i]);
            detalleDocente.setTipoRol(VDtiporol[i]);
            detalleDocente.setNombreDocente(VDnombre[i]);

            insertar(detalleDocente);
        }
        cerrar();
        return "Tablas LLenadas con exito";
    }


}
