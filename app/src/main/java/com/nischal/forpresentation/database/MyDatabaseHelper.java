package com.nischal.forpresentation.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lenevo on 1/11/2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = MyDatabaseHelper.class.getSimpleName();
    private static String DATABASE_NAME = "test";
    private static String TABLE_NAME = "table_one";
    private static String ID = "id";
    private static String INPUT_TEXT = "text";
    private static int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void createTable() {
        String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "("
                + ID + " integer primary key autoincrement,"
                + INPUT_TEXT + " text)";
        Log.e(LOG_TAG, TABLE_NAME + " created");
        getWritableDatabase().execSQL(CREATE_TABLE);
    }

    public void insertIntoTable(String input) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(INPUT_TEXT, input);
        getWritableDatabase().insert(TABLE_NAME, null, contentValues);
        Log.e(LOG_TAG, "inserted");
    }

    public ArrayList<ObjectTable> getTableData() {
        ArrayList<ObjectTable> list = new ArrayList<ObjectTable>();
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            ObjectTable objectTable = new ObjectTable();
            objectTable.id = cursor.getString(cursor.getColumnIndex(ID));
            objectTable.text = cursor.getString(cursor.getColumnIndex(INPUT_TEXT));
            list.add(objectTable);
        }
        Log.e(LOG_TAG, "getTabledata"+list.size());
        return list;
    }
    public void deleteTable(){
        getWritableDatabase().delete(TABLE_NAME,null,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
