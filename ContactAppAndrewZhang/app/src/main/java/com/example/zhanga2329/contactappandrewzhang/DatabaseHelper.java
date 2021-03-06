package com.example.zhanga2329.contactappandrewzhang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhanga2329 on 5/12/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "contact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";
    public static final String COL_4 = "ADDRESS";

    private int currentID = 1;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME TEXT, AGE TEXT, ADDRESS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String age, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, currentID);
        currentID++;
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, age);
        contentValues.put(COL_4, address);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public int getCurrentID() {
        return currentID;
    }
    public void setCurrentID(int re) {
        currentID = re;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
