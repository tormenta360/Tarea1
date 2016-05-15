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
    private static final String[]camposUsuario = new String [] {"idusuario","nomusuario","clave"};


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
            cv.put("nombredocente", detalleDocente.getNombreDocente());

            db.update("detalledocente", cv, "codigo = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigo " + detalleDocente.getCodigoDocente() + " no existe";
        }
    }

    public String searchPass(String str) {

        abrir();
        String query = "select nomusuario, clave from usuario";
        Cursor cursor = db.rawQuery(query , null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(str))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    public Usuario verificarCredenciales(Usuario u) {
        abrir();
        String[] idu = {u.getIdusuario(),u.getClave()};

        Cursor cursor = db.query("usuario",null,"nomusuario = ? AND clave = ?",idu,null,null,null);
        if(cursor.moveToFirst()){
            //se encontraron datos
            u.setIdusuario(cursor.getString(0));
            u.setNomusuario(cursor.getString(1));
            u.setClave(cursor.getString(2));
            cursor.close();
            db.close();
            return u;
        }
        db.close();
        return null;//no se enontraron coincidencias
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "consazon.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE docente(codigodocente VARCHAR(7) NOT NULL PRIMARY KEY, nombredocente VARCHAR(30),apellidodocente VARCHAR(30),escuela VARCHAR(30));");
                db.execSQL("CREATE TABLE detalledocente(codigo VARCHAR(7) NOT NULL PRIMARY KEY,codigogrupo INTEGER,tiporol VARCHAR(30),nombredocente VARCHAR(30));");
                db.execSQL("CREATE TABLE usuario(idusuario VARCHAR(2) NOT NULL PRIMARY KEY,nomusuario VARCHAR(30),clave VARCHAR(30));");
                db.execSQL("CREATE TABLE opcioncrud(idopcion VARCHAR(3) NOT NULL PRIMARY KEY,desopcion VARCHAR2(30),numcrud INTEGER);");
                db.execSQL("CREATE TABLE accesousuario(idopcion VARCHAR(3) NOT NULL,idusuario VARCHAR(2) NOT NULL,primary key(idopcion,idusuario)constraint fk_accesousuaio_usuario foreign key (idusuario) references usuario(idusuario) on delete restrict,constraint fk_accesousuario_opcioncrud foreign key (idopcion) references opcioncrud(idopcion) on delete restrict);");




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

    public void insertar(Usuario c) {
        abrir();
        ContentValues values = new ContentValues();
        values.put("idusuario" , c.getIdusuario());
        values.put("nomusuario", c.getNomusuario());
        values.put("clave", c.getClave());
        db.insert("usuario", null, values);
        cerrar();
    }

    private void insertar(OpcionCrud opcioncrud) {
        //Ingresando datos a la tabla OpcionCrud sin strings definidas
        abrir();
        ContentValues cvo = new ContentValues();

            cvo.put("idopcion", opcioncrud.getIdOpcion());
            cvo.put("desopcion", opcioncrud.getDesOpcion());
            cvo.put("numcrud", opcioncrud.getNumCrud());
            db.insert("opcioncrud", null, cvo);
        cerrar();
    }

    private void insertar(AccesoUsuario acceso) {
        abrir();
        //Ingresando datos a la tabla AccesoUsuario sin strings definidas
        ContentValues cva = new ContentValues();
            cva.put("idusuario", acceso.getIdUsuario());
            cva.put("idopcion", acceso.getIdOpcion());
            db.insert("accesousuario", null, cva);
        cerrar();

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


        final String[] VUidUsuario = {"00","01","02"};
        final String[] VUnomUsuario = {"admin","consultor","otro"};
        final String[] VUclave = {"pdm","pdm","12345"};

        final String[] VOidOpcion = {"000","001","002","003","004",
                "010","011","012","013","014",
                /*"020","021","022","023","024",
                "030","031","032","033","034",
                "040","041","042","043","044",
                "050","051","052","053","054",
                "060","061","062","063","064",
                "070","071","072","073","074",
                "080","081","082","083","084",
                "090","091","092","093","094",
                "100","101","102","103","104"*/};

        final String[] VOdesOpcion = {"Menu Docente","Ingresar Docente","Eliminar Docente","Actualizar Docente","Consultar Docente",
                "Menu Detalle Docente","Ingresar Detalle Docente","Eliminar Detalle Docente","Actualizar Detalle Docente","Consultar Detalle Docente"//,
                /*"Menu Docente","Ingresar Docente","Eliminar Docente","Actualizar Docente","Consultar Docente",
                "Menu Empresa","Ingresar Empresa","Eliminar Empresa","Actualizar Empresa","Consultar Empresa",
                "Menu Especialización","Ingresar Especialización","Eliminar Especialización","Actualizar Especialización","Consultar Especialización",
                "Menu Experiencia Laboral","Ingresar Experiencia","Eliminar Experiencia","Actualizar Experiencia","Consultar Experiencia",
                "Menu Grados Académicos","Ingresar Grado Académico","Eliminar Grado Académico","Actualizar Grado Académico","Consulta Grado Académico",
                "Menu de Institución","Ingresar Institución","Eliminar Institución","Actualizar Institucion","Consultar Institución",
                "Menu Materia","Ingresar Materia","Eliminar Materia","Actualizar Materia","Consultar Materia",
                "Menu Materias Impartidas","Ingresar Materia Impartida","Eliminar Materia Impartida","Actualizar Materia Impartida","Consultar Materia Impartida",
                "Menu Tipo de Contratación","Ingresar Tipo","Eliminar Tipo","Actualizar Tipo","Consultar Tipo"*/};

        final int[] VOnumCrud = {0,1,2,3,4,
                0,1,2,3,4//,
                /*0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,*/
                /*0/*,1,2,3,4*/};

        final String[] VAidUsuario = {"00","00","00","00","00",
                "00","00","00","00","00",
                /*"00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",*/
                "01","01","01","01","01",
                "01","01","01","01","01",
                /*"01","01","01","01","01",
                "01","01","01","01","01",
                "01","01",*/"02","02"};
        final String[] VAidOpcion = {"000","001","002","003","004",
                "010","011","012","013","014"//,
               /* "020","021","022","023","024",
                "030","031","032","033","034",
                "040","041","042","043","044",
                "050","051","052","053","054",
                "060","061","062","063","064",
                "070","071","072","073","074",
                "080","081","082","083","084",
                "090","091","092","093","094",
                "100","101","102","103","104"*/,
                "000","004","010","014","020",
                "024","030","034","040","044",
                /*"050","054","060","064","070",
                "074","080","084","090","094",
                "100","104",*/"000","004"};



        abrir();
        db.execSQL("DELETE FROM docente");
        db.execSQL("DELETE FROM detalledocente");
        db.execSQL("DELETE FROM usuario");
        db.execSQL("DELETE FROM opcioncrud");
        db.execSQL("DELETE FROM accesousuario");

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
        Usuario usuario = new Usuario();
        for(int i=0;i<3; i++) {
            usuario.setIdusuario(VUidUsuario[i]);
            usuario.setNomusuario(VUnomUsuario[i]);
            usuario.setClave(VUclave[i]);

            insertar(usuario);
        }

        OpcionCrud opcioncrud = new OpcionCrud();
        for(int i=0;i<VOidOpcion.length; i++) {
            opcioncrud.setIdOpcion(VOidOpcion[i]);
            opcioncrud.setDesOpcion(VOdesOpcion[i]);
            opcioncrud.setNumCrud(VOnumCrud[i]);

            insertar(opcioncrud);
        }

        AccesoUsuario acceso = new AccesoUsuario();
        for (int i=0;i<VAidUsuario.length;i++){
            acceso.setIdUsuario(VAidUsuario[i]);
            acceso.setIdOpcion(VAidOpcion[i]);

            insertar(acceso);
        }




        cerrar();
        return "Tablas LLenadas con exito";
    }



    public Cursor obtenerMenuUsuario(String id){
        String sql = "select oc.desopcion, oc.numcrud from usuario u "+
                "join accesousuario au on u.idusuario = au.idusuario and u.idusuario='"+id+"'\n" +
                "join opcioncrud oc on au.idopcion = oc.idopcion and oc.numcrud='0'";
        abrir();
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerSubMenu(String id, String idOpcion){
        String sql = "select oc.desopcion, oc.numcrud from usuario u "+
                "join accesousuario au on u.idusuario = au.idusuario and u.idusuario='"+id+"'\n" +
                "join opcioncrud oc on au.idopcion = oc.idopcion and oc.numcrud!='0' and au.idopcion like '"+idOpcion+"'";
        abrir();
        return db.rawQuery(sql, null);
    }

    public Usuario consultarId(String nomusuario) {
        String[] id = {nomusuario};

        Cursor cursor = db.query("usuario", camposUsuario, "nomusuario = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario detalledocente = new Usuario();
            detalledocente.setIdusuario(cursor.getString(0));
            detalledocente.setNomusuario(cursor.getString(1));
            detalledocente.setClave(cursor.getString(2));

            return detalledocente;
        }else{
            return null;
        }
    }
}
