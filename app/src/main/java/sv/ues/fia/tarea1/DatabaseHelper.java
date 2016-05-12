package sv.ues.fia.tarea1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TruenoBlanco on 11/5/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.s3db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_ROL = "rol";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " + "name text not null , email text not null , uname text not null , pass text not null, rol integer not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }



    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();

        values.put(COLUMN_ID , count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());
        values.put(COLUMN_ROL,1);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public String lleanarBD(){

        final int[] id = {00,01,02,03};
        final String[] name = {"Walter","Carlos","Alberto","Hernan"};
        final String[] email = {"walter.lemus@hotmail.com","carlos@hotmail.com","alberto@hotmail.com","hernan@hotmail.com"};
        final String[] uname = {"walter5lemus","carlos","alberto","hernan"};
        final String[] pass = {"walter92","Ch1q2","jA3f2","gD21d"};
        final int[] rol = {2,1,1,2};



        Contact contacts = new Contact();
        for(int i=0;i<4;i++){
            contacts.setId(id[i]);
            contacts.setName(name[i]);
            contacts.setEmail(email[i]);
            contacts.setUname(uname[i]);
            contacts.setPass(pass[i]);
            contacts.setRol(rol[i]);
            insertContact(contacts);
        }

        return "Tablas LLenadas con exito";
    }


}
