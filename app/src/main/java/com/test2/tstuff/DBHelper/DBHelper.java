package com.test2.tstuff.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.test2.tstuff.List.Tlist;

import java.io.File;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


   private static String DATABASE_PATH = "/data/data/com.test2.tstuff/databases/";
    private static final String DB_NAME = "list.db";// database name
    private static final int DB_VER = 1; // database version
    private static final String Table = "gList"; // grocery list table
    private static final String Table1 = "needs"; // needs list

    // column for grocery list
    private static String ItemCol = "Item"; // item
    private static String Quantity = "Quantity"; // Quantity

    // column for needs list
    private static String NItemCol = "Item"; // item
    private static String NQuantity = "Quantity"; // Quantity

    private static String TAG = DBHelper.class.getSimpleName(); // get name for database exception

    private SQLiteDatabase db; //SQLIte database
    private Context context; // context variables


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Item text, Quantity integer)");
        db.execSQL("create table "+Table1+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Item text, Quantity integer)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table);
        db.execSQL("DROP TABLE IF EXISTS "+Table1);

        Log.w(TAG,"Update from old version");

        onCreate(db);

    }

    // check if database is exist
    public boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(DATABASE_PATH);// return true if in exist
            checkDB = file.exists();
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB; // return false if in exist
    }

    public void openDataBase() throws SQLException {
        String path = DATABASE_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY); // open database
        Log.d("open","Open");
    }

    // copy database from assets folder

    public ArrayList<Tlist> getAllList ()
    {
        ArrayList<Tlist> groceryList = new ArrayList<>(); // create and object
        db = getReadableDatabase();// get readable database
        Cursor c = db.rawQuery("SELECT * FROM " + Table,null);

        if(c.moveToFirst()){
            do {

                Tlist gl = new Tlist(); // get new Tlist object
                gl.setTlist(c.getString(c.getColumnIndex(ItemCol))); // grocery list
                gl.setQuantity(c.getInt(c.getColumnIndex(Quantity))); // quantity in the list
            }while(c.moveToNext()); // move to next record in the database
        }
        c.close();
        return groceryList;
    }

    public boolean addData(String item, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("Item",item);
        c.put("Quantity",quantity);


        long result = db.insert(Table,null,c);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    // for deleting database if needed
    public void deleteDb() {
        File file = new File(DATABASE_PATH);
        if (file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }
}
