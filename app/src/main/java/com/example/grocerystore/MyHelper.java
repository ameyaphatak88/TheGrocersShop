package com.example.grocerystore;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private static final String dbname = "mydb";
    private static final int version = 1;

    public MyHelper(Context context){
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE CUSTOMERS (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PHONENO TEXT, ADDR TEXT)";
        db.execSQL(sql);
    }

    public void insertData(String name, String email, String phoneno, String addr, SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("EMAIL", email);
        values.put("PHONENO", phoneno);
        values.put("ADDR", addr);
        database.insert("PRODUCTS", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
