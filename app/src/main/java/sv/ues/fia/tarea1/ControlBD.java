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

    private static final String[]camposDocente = new String [] {"codigodocente","nombredocente","apellidodocente","tipocontrato,correo,telefono"};
    private static final String[]camposDetalleDocente = new String [] {"codigo","codigogrupo","tiporol","nombredocente"};
    private static final String[]camposUsuario = new String [] {"idusuario","nomusuario","clave"};
    private static final String[] camposPropuestaPerfil = new String[] {"numero_tema", "codigo_grupo", "tema_perfil", "estado", "ano_propuesta"};
    private static final String[] camposTipoProyecto = new String[] {"numero_tema","nombre_tipo", "tipo_defensa", "tipo_realizacion"};
    private static final String[]camposEvaluacion = new String[] {"numero_evaluacion", "numero_tema", "nombre_evaluacion", "nota_global"};
    private static final String[]camposDetalle_Evaluacion = new String[] {"numero_evaluacion", "carnet"};
    private static final String[]camposLocal_Evaluacion = new String[] {"codigo_local", "numero_evaluacion", "nombre_local", "disponible", "lugar"};



    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "proyecto1.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE docente(codigodocente VARCHAR(7) NOT NULL PRIMARY KEY, nombredocente VARCHAR(30),apellidodocente VARCHAR(30),tipocontrato VARCHAR(30),correo VARCHAR(50),telefono VARCHAR(30));");
                db.execSQL("CREATE TABLE detalledocente(codigo VARCHAR(7) NOT NULL PRIMARY KEY,codigogrupo INTEGER,tiporol VARCHAR(30),nombredocente VARCHAR(30));");
                db.execSQL("CREATE TABLE usuario(idusuario VARCHAR(2) NOT NULL PRIMARY KEY,nomusuario VARCHAR(30),clave VARCHAR(30));");
                db.execSQL("CREATE TABLE opcioncrud(idopcion VARCHAR(3) NOT NULL PRIMARY KEY,desopcion VARCHAR2(30),numcrud INTEGER);");
                db.execSQL("CREATE TABLE accesousuario(idopcion VARCHAR(3) NOT NULL,idusuario VARCHAR(2) NOT NULL,primary key(idopcion,idusuario)constraint fk_accesousuaio_usuario foreign key (idusuario) references usuario(idusuario) on delete restrict,constraint fk_accesousuario_opcioncrud foreign key (idopcion) references opcioncrud(idopcion) on delete restrict);");
                db.execSQL("CREATE TABLE propuesta_perfil(numero_tema INTEGER NOT NULL PRIMARY KEY,codigo_grupo INTEGER NOT NULL,tema_perfil VARCHAR(30),estado VARCHAR(30),ano_propuesta INTEGER);");
                db.execSQL("CREATE TABLE tipo_proyecto(numero_tema INTEGER NOT NULL PRIMARY KEY,nombre_tipo VARCHAR(20),tipo_defensa VARCHAR(20),tipo_realizacion VARCHAR(20));");
                db.execSQL("CREATE TABLE evaluacion(numero_evaluacion INTEGER PRIMARY KEY NOT NULL,numero_tema INTEGER,nombre_evaluacion VARCHAR(25),nota_global REAL);");
                db.execSQL("CREATE TABLE detalle_evaluacion(numero_evaluacion INTEGER PRIMARY KEY NOT NULL,carnet VARCHAR(7));");
                db.execSQL("CREATE TABLE local_evaluacion(codigo_local INTEGER PRIMARY KEY NOT NULL,numero_evaluacion INTEGER,nombre_local VARCHAR(20),disponible VARCHAR(20),lugar VARCHAR(20));");

            }catch(SQLException e){
                e.printStackTrace();

            }

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }

    public String insertar(Evaluacion evaluacion){

        String regInsertados = "Registro Insertado N°= ";
        long contador = 0;

        if (verificarIntegridad(evaluacion, 13)) {

            ContentValues eval = new ContentValues();
            eval.put("numero_evaluacion", evaluacion.getNumero_evaluacion());
            eval.put("numero_tema", evaluacion.getNumero_tema());
            eval.put("nombre_evaluacion", evaluacion.getNombre_evaluacion());
            eval.put("nota_global", evaluacion.getNota_global());
            contador = db.insert("evaluacion", null, eval);
        }

        if(contador==-1 || contador==0)
        {
            regInsertados = "Error al Insertar el registro.";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Detalle_Evaluacion detalle_evaluacion){

        String regInsertados = "Registro Insertado N°= ";
        long contador = 0;

        if (verificarIntegridad(detalle_evaluacion, 15)) {

            ContentValues eval = new ContentValues();
            eval.put("numero_evaluacion", detalle_evaluacion.getNumero_evaluacion());
            eval.put("carnet", detalle_evaluacion.getCarnet());
            contador = db.insert("detalle_evaluacion", null, eval);
        }

        if(contador==-1 || contador==0)
        {
            regInsertados = "Error al insertar el registro.";
        }
        else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Local_Evaluacion local_evaluacion) {
        String regInsertados = "Registro Insertado N°= ";
        long contador = 0;

        if (verificarIntegridad(local_evaluacion, 10)) {

            ContentValues eval = new ContentValues();
            eval.put("codigo_local", local_evaluacion.getCodigo_local());
            eval.put("numero_evaluacion", local_evaluacion.getNumero_evaluacion());
            eval.put("nombre_local", local_evaluacion.getNombre_local());
            eval.put("disponible", local_evaluacion.getDisponible());
            eval.put("lugar", local_evaluacion.getLugar());
            contador = db.insert("local_evaluacion", null, eval);
        }

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al insertar el registro.";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Evaluacion evaluacion) {
        if (verificarIntegridad(evaluacion, 14)) {
            String[] id = {String.valueOf(evaluacion.getNumero_evaluacion()), String.valueOf(evaluacion.getNumero_tema())};
            ContentValues cv = new ContentValues();
            cv.put("nombre_evaluacion", evaluacion.getNombre_evaluacion());
            cv.put("nota_global", evaluacion.getNota_global());
            db.update("evaluacion", cv, "numero_evaluacion = ? AND numero_tema = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Numero de evaluacion " + evaluacion.getNumero_evaluacion() + " no existe";
        }
    }

    public String actualizar(Detalle_Evaluacion detalle_evaluacion){

        if (verificarIntegridad(detalle_evaluacion, 16)) {
            String[] id = {String.valueOf(detalle_evaluacion.getCarnet())};
            ContentValues cv = new ContentValues();
            cv.put("numero_evaluacion", detalle_evaluacion.getCarnet());
            db.update("detalle_evaluacion", cv, "carnet = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Carnet " + detalle_evaluacion.getCarnet() + " no existe";
        }
    }

    public String actualizar(Local_Evaluacion local_evaluacion) {

        if (verificarIntegridad(local_evaluacion, 12)) {
            String[] id = {String.valueOf(local_evaluacion.getCodigo_local()), String.valueOf(local_evaluacion.getNumero_evaluacion())};
            ContentValues cv = new ContentValues();
            cv.put("nombre_local", local_evaluacion.getNombre_local());
            cv.put("disponible", local_evaluacion.getDisponible());
            cv.put("lugar", local_evaluacion.getLugar());
            db.update("local_evaluacion", cv, "codigo_local = ? AND numero_evaluacion = ?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "No existe tabla evaluacion";
        }
    }

    public String eliminar(Evaluacion evaluacion){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if(verificarIntegridad(evaluacion, 11)){
            contador+=db.delete("local_evaluacion", "numero_evaluacion='"+evaluacion.getNumero_evaluacion()+"'", null);
            contador+=db.delete("detalle_evaluacion", "numero_evaluacion='"+evaluacion.getNumero_evaluacion()+"'", null);
        }
        String where="numero_evaluacion='"+evaluacion.getNumero_evaluacion()+"'";
        where="numero_evaluacion='"+evaluacion.getNumero_evaluacion()+"'";
        contador+=db.delete("evaluacion", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(Detalle_Evaluacion detalle_evaluacion){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="numero_evaluacion='"+detalle_evaluacion.getNumero_evaluacion()+"'";
        where="carnet='"+detalle_evaluacion.getCarnet()+"'";
        contador+=db.delete("detalle_evaluacion", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(Local_Evaluacion local_evaluacion){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="codigo_local='"+local_evaluacion.getCodigo_local()+"'";
        where="numero_evaluacion='"+local_evaluacion.getNumero_evaluacion()+"'";
        contador+=db.delete("local_evaluacion", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public Evaluacion consultarEvaluacion(int numero_evaluacion, int numero_tema){

        String[] id = {Integer.toString(numero_evaluacion), Integer.toString(numero_tema)};
        Cursor cursor = db.query("evaluacion", camposEvaluacion, "numero_evaluacion = ? AND numero_tema = ?", id, null, null, null);
        if(cursor.moveToFirst()){

            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setNumero_evaluacion(cursor.getInt(0));
            evaluacion.setNumero_tema(cursor.getInt(1));
            evaluacion.setNombre_evaluacion(cursor.getString(2));
            evaluacion.setNota_global(cursor.getFloat(3));
            return evaluacion;
        }else{
            return null;
        }
    }

    public Detalle_Evaluacion consultarDetalle_Evaluacion(String carnet){

        String[] id = {carnet};
        Cursor cursor = db.query("detalle_evaluacion", camposDetalle_Evaluacion, "carnet = ?", id, null, null, null);
        if(cursor.moveToFirst()){

            Detalle_Evaluacion detalle_evaluacion = new Detalle_Evaluacion();
            detalle_evaluacion.setNumero_evaluacion(cursor.getInt(0));
            detalle_evaluacion.setCarnet(cursor.getString(1));
            return detalle_evaluacion;
        }else{
            return null;
        }
    }

    public Local_Evaluacion consultarLocal_Evaluacion(int codigo_local, int numero_evaluacion){

        String[] id = {Integer.toString(codigo_local), Integer.toString(numero_evaluacion)};
        Cursor cursor = db.query("local_evaluacion", camposLocal_Evaluacion, "codigo_local = ? AND numero_evaluacion = ?", id, null, null, null);
        if(cursor.moveToFirst()){

            Local_Evaluacion local_evaluacion = new Local_Evaluacion();
            local_evaluacion.setCodigo_local(cursor.getInt(0));
            local_evaluacion.setNumero_evaluacion(cursor.getInt(1));
            local_evaluacion.setNombre_local(cursor.getString(2));
            local_evaluacion.setDisponible(cursor.getString(3));
            local_evaluacion.setLugar(cursor.getString(4));
            return local_evaluacion;
        }else{
            return null;
        }
    }



    public String insertar(PropuestaPerfil propuestaPerfil) {

        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;

        if(verificarIntegridad(propuestaPerfil,5)) {
            ContentValues proper = new ContentValues();
            proper.put("numero_tema", propuestaPerfil.getNumero_tema());
            proper.put("codigo_grupo", propuestaPerfil.getCodigo_grupo());
            proper.put("tema_perfil", propuestaPerfil.getTema_perfil());
            proper.put("estado", propuestaPerfil.getEstado());
            proper.put("ano_propuesta", propuestaPerfil.getAno_propuesta());
            contador = db.insert("propuesta_perfil", null, proper);
        }

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(TipoProyecto tipoProyecto) {

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if(verificarIntegridad(tipoProyecto,8)){
            ContentValues tipro = new ContentValues();
            tipro.put("numero_tema", tipoProyecto.getNumero_tema());
            tipro.put("nombre_tipo", tipoProyecto.getNombre_tipo());
            tipro.put("tipo_defensa", tipoProyecto.getTipo_defensa());
            tipro.put("tipo_realizacion", tipoProyecto.getTipo_realizacion());
            contador=db.insert("tipo_proyecto", null, tipro);
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }

        return regInsertados;
    }


    public String actualizar(PropuestaPerfil propuestaPerfil) {

        if(verificarIntegridad(propuestaPerfil, 6)){
            String[] id = {String.valueOf(propuestaPerfil.getNumero_tema()),String.valueOf(propuestaPerfil.getCodigo_grupo())};
            ContentValues cv = new ContentValues();
            cv.put("tema_perfil", propuestaPerfil.getTema_perfil());
            cv.put("estado", propuestaPerfil.getEstado());
            cv.put("ano_propuesta", propuestaPerfil.getAno_propuesta());
            db.update("propuesta_perfil", cv, "numero_tema = ? AND codigo_grupo =?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }

    public String actualizar(TipoProyecto tipoProyecto) {

        if(verificarIntegridad(tipoProyecto, 9)){
            String[] id = {String.valueOf(tipoProyecto.getNumero_tema())};
            ContentValues cv = new ContentValues();
            cv.put("nombre_tipo", tipoProyecto.getNombre_tipo());
            cv.put("tipo_defensa", tipoProyecto.getTipo_defensa());
            cv.put("tipo_realizacion", tipoProyecto.getTipo_realizacion());
            db.update("tipo_proyecto", cv, "numero_tema = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }

    public String eliminar(PropuestaPerfil propuestaPerfil)
    {
        String regAfectados="filas afectadas= ";
        int contador=0;
        if(verificarIntegridad(propuestaPerfil, 7)){
            contador+=db.delete("tipo_proyecto", "numero_tema='"+propuestaPerfil.getNumero_tema()+"'", null);
            contador+=db.delete("evaluacion", "numero_tema='"+propuestaPerfil.getNumero_tema()+"'", null);
        }
        String where="numero_tema='"+propuestaPerfil.getNumero_tema()+"'";
        where=where+" AND codigo_grupo='"+propuestaPerfil.getCodigo_grupo()+"'";
        contador+=db.delete("propuesta_perfil", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(TipoProyecto tipoProyecto) {
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="numero_tema='"+tipoProyecto.getNumero_tema()+"'";
        contador+=db.delete("tipo_proyecto", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public PropuestaPerfil consultarPropuestaPerfil(int numero_tema, int codigo_grupo) {

        String[] id = {String.valueOf(numero_tema),String.valueOf(codigo_grupo)};
        Cursor cursor = db.query("propuesta_perfil", camposPropuestaPerfil, "numero_tema = ? AND codigo_grupo =?", id, null, null, null);
        if(cursor.moveToFirst()){
            PropuestaPerfil propuestaPerfil = new PropuestaPerfil();
            propuestaPerfil.setNumero_tema(cursor.getInt(0));
            propuestaPerfil.setCodigo_grupo(cursor.getInt(1));
            propuestaPerfil.setTema_perfil(cursor.getString(2));
            propuestaPerfil.setEstado(cursor.getString(3));
            propuestaPerfil.setAno_propuesta(cursor.getInt(4));
            return propuestaPerfil;
        }else{
            return null;
        }
    }

    public TipoProyecto consultarTipoProyecto(int numero_tema) {

        String[] id = {String.valueOf(numero_tema)};
        Cursor cursor = db.query("tipo_proyecto", camposTipoProyecto, "numero_tema = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoProyecto tipoProyecto = new TipoProyecto();
            tipoProyecto.setNumero_tema(cursor.getInt(0));
            tipoProyecto.setNombre_tipo(cursor.getString(1));
            tipoProyecto.setTipo_defensa(cursor.getString(2));
            tipoProyecto.setTipo_realizacion(cursor.getString(3));
            return tipoProyecto;
        }else{
            return null;
        }
    }





    public Docente consultarDocente(String codigo) {
        String[] id = {codigo};

        Cursor cursor = db.query("docente", camposDocente, "codigodocente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Docente docente = new Docente();
            docente.setCodigoDocente(cursor.getString(0));
            docente.setNombreDocente(cursor.getString(1));
            docente.setApellidoDocente(cursor.getString(2));
            docente.setTipoContrato(cursor.getString(3));
            docente.setCorreo(cursor.getString(4));
            docente.setTelefono(cursor.getString(5));


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
            cv.put("tipocontrato", docente.getTipoContrato());
            cv.put("correo", docente.getCorreo());
            cv.put("telefono", docente.getTelefono());


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
        contador+=db.delete("grupo", "codigo_grupo='"+detalleDocente.getCodigoGrupo()+"'", null);
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
            doc.put("tipocontrato", docente.getTipoContrato());
            doc.put("correo", docente.getCorreo());
            doc.put("telefono", docente.getTelefono());


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


            case 5: {
                //verificar que al propuesta_perfil exista numero_grupo
                PropuestaPerfil propuestaPerfil = (PropuestaPerfil) dato;
                String[] idprop = {String.valueOf(propuestaPerfil.getCodigo_grupo())};
                abrir();
                Cursor curprop = db.query("grupo", null, "codigo_grupo = ?", idprop, null, null, null);
                if (curprop.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 6: {
                //verificar que al modificar propuesta_perfil exista numero_tema, codigo_grupo
                PropuestaPerfil propuestaPerfil1 = (PropuestaPerfil) dato;
                String[] idpropmod = {String.valueOf(propuestaPerfil1.getNumero_tema()), String.valueOf(propuestaPerfil1.getCodigo_grupo())};
                abrir();
                Cursor curprop12 = db.query("propuesta_perfil", null, "numero_tema = ? AND codigo_grupo =?", idpropmod, null, null, null);
                if (curprop12.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 7: {
                //Eliminar evaluacion y local_evaluacion//Eliminar evaluacion y local_evaluacion
                PropuestaPerfil propuestaPerfil2 = (PropuestaPerfil) dato;
                Cursor curpropl3 = db.query(true, "tipo_proyecto", new String[]{"numero_tema"}, "numero_tema='" + propuestaPerfil2.getNumero_tema() + "'", null, null, null, null, null);
                Cursor curpropl4 = db.query(true, "evaluacion", new String[]{"numero_tema"}, "numero_tema='" + propuestaPerfil2.getNumero_tema() + "'", null, null, null, null, null);
                if (curpropl3.moveToFirst() && curpropl4.moveToFirst())
                    return true;
                else
                    return false;
            }

            case 8: {
                //verificar que al insertar_tipo_proyecto exista numero_tema
                TipoProyecto tipoProyecto = (TipoProyecto) dato;
                String[] idtipo = {String.valueOf(tipoProyecto.getNumero_tema())};
                abrir();
                Cursor curtipo1 = db.query("propuesta_perfil", null, "numero_tema = ?", idtipo, null, null, null);
                if (curtipo1.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 9: {
                //verificar que al modificar tipo_proyecto exista numero_tema
                TipoProyecto tipoProyecto2 = (TipoProyecto) dato;
                String[] idtipo2 = {String.valueOf(tipoProyecto2.getNumero_tema())};
                abrir();
                Cursor curtipo12 = db.query("tipo_proyecto", null, "numero_tema = ?", idtipo2, null, null, null);
                if (curtipo12.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 10: {
                //verificar que al insertar_local_evaluacion exista numero_evaluacion
                Local_Evaluacion local_evaluacion = (Local_Evaluacion) dato;
                String[] idlocalins = {String.valueOf(local_evaluacion.getNumero_evaluacion())};
                abrir();
                Cursor curlocal1 = db.query("evaluacion", null, "numero_evaluacion = ?", idlocalins, null, null, null);
                if (curlocal1.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 11: {
                //Eliminar evaluacion y local_evaluacion//Eliminar evaluacion y local_evaluacion
                Evaluacion evaluacion = (Evaluacion) dato;
                Cursor curlocal2 = db.query(true, "local_evaluacion", new String[]{"numero_evaluacion"}, "numero_evaluacion='" + evaluacion.getNumero_evaluacion() + "'", null, null, null, null, null);
                Cursor curlocal3 = db.query(true, "detalle_evaluacion", new String[]{"numero_evaluacion"}, "numero_evaluacion='" + evaluacion.getNumero_evaluacion() + "'", null, null, null, null, null);
                if (curlocal2.moveToFirst() && curlocal3.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 12: {
                //verificar que al modificar local_evaluacion exista codigo_local, numero_evaluacion
                Local_Evaluacion local_evaluacion1 = (Local_Evaluacion) dato;
                String[] idlocalmod = {String.valueOf(local_evaluacion1.getCodigo_local()), String.valueOf(local_evaluacion1.getNumero_evaluacion())};
                abrir();
                Cursor curlocal2 = db.query("local_evaluacion", null, "codigo_local = ? AND numero_evaluacion =?", idlocalmod, null, null, null);
                if (curlocal2.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 13: {
                //verificar que al insertar evaluacion exista numero_tema
                Evaluacion evaluacion = (Evaluacion) dato;
                String[] idevaluacion = {String.valueOf(evaluacion.getNumero_tema())};
                abrir();
                Cursor cureval1 = db.query("propuesta_perfil", null, "numero_tema = ?", idevaluacion, null, null, null);
                if (cureval1.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            //Verificar que al modificar evaluacion exista numero_evaluacion y numero_tema
            case 14: {
                Evaluacion evaluacion1 = (Evaluacion) dato;
                String[] idevalmod = {String.valueOf(evaluacion1.getNumero_evaluacion()), String.valueOf(evaluacion1.getNumero_tema())};
                abrir();
                Cursor cureval2 = db.query("evaluacion", null, "numero_evaluacion = ? AND numero_tema = ?", idevalmod, null, null, null);
                if (cureval2.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 15: {
                //verificar que al insertar_detalle_evaluacion exista numero_evaluacion y carnet
                Detalle_Evaluacion detalle_evaluacion = (Detalle_Evaluacion)dato;
                String[] iddetalle = {String.valueOf(detalle_evaluacion.getNumero_evaluacion())};
                String[] iddetalle1 = {detalle_evaluacion.getCarnet()};
                abrir();
                Cursor curdeta1 = db.query("evaluacion", null, "numero_evaluacion = ?", iddetalle, null, null, null);
                Cursor curdeta2 = db.query("estudiante", null, "carnet = ?", iddetalle1, null, null, null);
                if (curdeta1.moveToFirst() && curdeta2.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 16:{
                //verificar que al modificar detalle_evaluacion numero_evaluacion y carnet
                Detalle_Evaluacion detalle_evaluacion1 = (Detalle_Evaluacion)dato;
                String[] iddetamod = {String.valueOf(detalle_evaluacion1.getNumero_evaluacion()), detalle_evaluacion1.getCarnet()};
                abrir();
                Cursor curdeta3 = db.query("detalle_evaluacion", null, "numero_evaluacion = ? AND carnet =?", iddetamod, null, null, null);
                if (curdeta3.moveToFirst()) {
                    //Se encontraron datos
                    return true;
                }
                return false;
            }


            default:
                return false;
        }

    }


    public String llenarBD(){

        final String[] VDcodigo = {"LV10022","SC12054"};
        final String[] VDnombre = {"Walter","Cristian"};
        final String[] VDapellido = {"Lemus","Sosa"};
        final int[] VDcodigogrupo = {01,02};
        final String[] VDtiporol = {"Jurado","Docente"};
        final String[] VDtipoContrato={"Ley de Salario","Contrato"};
        final String[] VDcorreo={"lv10022@ues.edu.sv","sc12054@ues.edu.sv"};
        final String[] VDtelefono={"22250001","22250002"};

        final int[] VPnumero_tema = {1, 2, 3, 4};
        final int[] VPcodigo_grupo = {1, 2, 3, 4};
        final String[] VPtema_perfil = {"Sistema Bienestar", "Investigacion ecnologia", "Sistema de inventario", "Sistema contable"};
        final String[] VPestado = {"Aprobado", "No Aprobado","Aprobado", "No Aprobado"};
        final int[] VPano_propuesta = {1992, 1993, 1997, 2005};

        final int[] VTnumero_tema = {1, 2, 3, 4};
        final String[] VTnombre_tipo = {"Sistema Informatico", "Investigacion", "Sistema Informatico", "Investigacion"};
        final String[] VTtipo_defensa = {"Demostracion", "Exposicion", "Demostracion", "Demostracion"};
        final String[] VTtipo_realizacion = {"Demostrar", "Hablar", "Demostrar", "Demostrar"};

        final int[]VEnumero_evaluacion = {1, 2, 3, 4, 5, 6};
        final int[]VEnumero_tema = {1, 2, 3, 4, 5, 6};
        final String[]VEnombre_evaluacion = {"Primera","Segunda","Tercera","Cuarta","Quinta","Sexta"};
        final double[]VEnota_global = {9.0,9.5,8.33,8.00,6.56,10.0};

        //Detalle_Evaluacion
        final int[]VDnumero_evaluacion = {1,2,3,4};
        final String[]VDcarnet = {"AA11011","BB12012","CC13013","EE14014"};

        //Local_Evaluacion
        final int[]VLcodigo_local = {301,21,22,33};
        final int[]VLnumero_evaluacion = {1,2,3,4};
        final String[]VLnombre_local = {"Primera Planta","Segunda Planta","Tercera Planta","Edificio Compartido"};
        final String[]VLdisponible = {"Disponible", "No disponible", "No disponible", "Disponible"};
        final String[]VLlugar = {"Facultad de Ingenieria","Facultad de Economia","Falcutad de Odontologia","Facultad de Agronomia"};


        final String[] VUidUsuario = {"00","01","02"};
        final String[] VUnomUsuario = {"admin","consultor","otro"};
        final String[] VUclave = {"pdm","pdm","12345"};

        final String[] VOidOpcion = {"000","001","002","003","004",
                "010","011","012","013","014",
                "020","021","022","023","024",
                "030","031","032","033","034",
                "040","041","042","043","044",
                "050","051","052","053","054",
                "060","061","062","063","064",
                /*"070","071","072","073","074",
                "080","081","082","083","084",
                "090","091","092","093","094",
                "100","101","102","103","104"*/};

        final String[] VOdesOpcion = {"Menu Docente","Ingresar Docente","Eliminar Docente","Actualizar Docente","Consultar Docente",
                "Menu Detalle Docente","Ingresar Detalle Docente","Eliminar Detalle Docente","Actualizar Detalle Docente","Consultar Detalle Docente",
                "Tabla Propuesta Perfil","Insertar Registro","Eliminar Registro","Consultar Registro","Actualizar Registro",
                "Tabla Tipo Proyecto","Insertar Registro","Eliminar Registro","Consultar Registro","Actualizar Registro",
                "Tabla Evaluación","Insertar Registro", "Eliminar Registro", "Consultar Registro", "Actualizar Registro",
                "Tabla Detalle Evaluación","Insertar Registro", "Eliminar Registro", "Consultar Registro", "Actualizar Registro",
                "Tabla Local Evaluación","Insertar Registro", "Eliminar Registro", "Consultar Registro", "Actualizar Registro",
                /*"Menu de Institución","Ingresar Institución","Eliminar Institución","Actualizar Institucion","Consultar Institución",
                "Menu Materia","Ingresar Materia","Eliminar Materia","Actualizar Materia","Consultar Materia",
                "Menu Materias Impartidas","Ingresar Materia Impartida","Eliminar Materia Impartida","Actualizar Materia Impartida","Consultar Materia Impartida",
                "Menu Tipo de Contratación","Ingresar Tipo","Eliminar Tipo","Actualizar Tipo","Consultar Tipo"*/};

        final int[] VOnumCrud = {0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,
                /*0,1,2,3,4,
                0,1,2,3,4,
                0,1,2,3,4,*/
                /*0/*,1,2,3,4*/};

        final String[] VAidUsuario = {"00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                /*"00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",
                "00","00","00","00","00",*/
                "01","01","01","01","01",
                "01","01","01","01","01",
                "01","01","01","01","01",
                /*"01","01","01","01","01",
                "01","01",*/"02","02"};
        final String[] VAidOpcion = {"000","001","002","003","004",
                "010","011","012","013","014",
                "020","021","022","023","024",
                "030","031","032","033","034",
                "040","041","042","043","044",
                "050","051","052","053","054",
                "060","061","062","063","064",
                /*"070","071","072","073","074",
                "080","081","082","083","084",
                "090","091","092","093","094",
                "100","101","102","103","104"*,*/
                "000","004","010","014","020",
                "024","030","034","040","044",
                "050","054","060","064","070",
                /*"074","080","084","090","094",
                "100","104",*/"000","004"};



        abrir();
        db.execSQL("DELETE FROM docente");
        db.execSQL("DELETE FROM detalledocente");
        db.execSQL("DELETE FROM usuario");
        db.execSQL("DELETE FROM opcioncrud");
        db.execSQL("DELETE FROM accesousuario");
        db.execSQL("DELETE FROM propuesta_perfil");
        db.execSQL("DELETE FROM tipo_proyecto");
        db.execSQL("DELETE FROM evaluacion");
        db.execSQL("DELETE FROM detalle_evaluacion");
        db.execSQL("DELETE FROM local_evaluacion");


        Docente docente = new Docente();
        for(int i=0;i<2;i++){
            docente.setCodigoDocente(VDcodigo[i]);
            docente.setNombreDocente(VDnombre[i]);
            docente.setApellidoDocente(VDapellido[i]);
            docente.setTipoContrato(VDtipoContrato[i]);
            docente.setCorreo(VDcorreo[i]);
            docente.setTelefono(VDtelefono[i]);

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

        PropuestaPerfil propuestaPerfil = new PropuestaPerfil();
        for (int i = 0; i < 4; i++) {
            propuestaPerfil.setNumero_tema(VPnumero_tema[i]);
            propuestaPerfil.setCodigo_grupo(VPcodigo_grupo[i]);
            propuestaPerfil.setTema_perfil(VPtema_perfil[i]);
            propuestaPerfil.setEstado(VPestado[i]);
            propuestaPerfil.setAno_propuesta(VPano_propuesta[i]);
            insertar(propuestaPerfil);
        }

        TipoProyecto tipoProyecto = new TipoProyecto();
        for (int i = 0; i < 4; i++) {
            tipoProyecto.setNumero_tema(VTnumero_tema[i]);
            tipoProyecto.setNombre_tipo(VTnombre_tipo[i]);
            tipoProyecto.setTipo_defensa(VTtipo_defensa[i]);
            tipoProyecto.setTipo_realizacion(VTtipo_realizacion[i]);
            insertar(tipoProyecto);
        }

        Evaluacion evaluacion = new Evaluacion();
        for(int i=0; i<6; i++)
        {
            evaluacion.setNumero_evaluacion(VEnumero_evaluacion[i]);
            evaluacion.setNumero_tema(VEnumero_tema[i]);
            evaluacion.setNombre_evaluacion(VEnombre_evaluacion[i]);
            evaluacion.setNota_global(VEnota_global[i]);
            insertar(evaluacion);
        }

        Local_Evaluacion local_evaluacion = new Local_Evaluacion();
        for(int i=0; i<4; i++)
        {
            local_evaluacion.setCodigo_local(VLcodigo_local[i]);
            local_evaluacion.setNumero_evaluacion(VLnumero_evaluacion[i]);
            local_evaluacion.setNombre_local(VLnombre_local[i]);
            local_evaluacion.setDisponible(VLdisponible[i]);
            local_evaluacion.setLugar(VLlugar[i]);
            insertar(local_evaluacion);
        }

        Detalle_Evaluacion detalle_evaluacion = new Detalle_Evaluacion();
        for(int i=0;i<4;i++) {
            detalle_evaluacion.setNumero_evaluacion(VDnumero_evaluacion[i]);
            detalle_evaluacion.setCarnet(VDcarnet[i]);
            insertar(detalle_evaluacion);
        }




        cerrar();
        return "Tablas LLenadas con exito";
    }




}
