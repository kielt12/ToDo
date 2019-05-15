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


    private static String id = "ID";
    private static final String DB_NAME = "list.db";// database name
    private static final int DB_VER = 1; // database version
    private static final String Table = "gList"; // grocery list table
    private static final String DeletedTable = "deleteList"; // needs list

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
        db.execSQL("create table " + Table + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Item text, Quantity integer)");
        db.execSQL("create table "+DeletedTable+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Item text, Quantity integer)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table);
        db.execSQL("DROP TABLE IF EXISTS "+DeletedTable);

        Log.w(TAG, "Update from old version");

        onCreate(db);

    }

    // check if database is exist


    // copy database from assets folder


    public boolean addData(String item, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("Item", item);
        c.put("Quantity", quantity);


        long result = db.insert(Table, null, c);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor delgetData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DeletedTable;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean deladdData(String item, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("Item", item);
        c.put("Quantity", quantity);


        long result = db.insert(DeletedTable, null, c);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public void update(String name, String oldname, int quan ) {
        ContentValues updateValues = new ContentValues();

        updateValues.put(ItemCol,name);
        updateValues.put(Quantity,quan);
        String where = id + "=?";
        String whereArgs[] = {oldname};
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(Table,updateValues,where,whereArgs);


    }

    public void delete(String name){
        String where = id + "=?";
        String whereArgs[] = {name};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table,where,whereArgs);
    }

    public void DelListDelete(String name){
        String where = id + "=?";
        String whereArgs[] = {name};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DeletedTable,where,whereArgs);
    }
}
