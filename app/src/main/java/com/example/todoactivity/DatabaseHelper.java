package com.example.todoactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.database.DatabaseUtils.dumpCursorToString;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String TAG="DatabaseHelper";
    public static final String DATABASE_NAME="todo.db";
    public static final String TABLE_NAME="todolist";
    public static final String ID="ID";
    public static final String ITEM_NAME="ITEMNAME";
    public static final String DATE="DATE";
    public static final String DUE_DATE="DUEDATE";

    public static final String CREATE_TABLE_TODOLIST="create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ITEMNAME TEXT, DATE TEXT, DUEDATE TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODOLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String date, String dueDate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME,name);
        contentValues.put(DATE,date);
        contentValues.put(DUE_DATE,dueDate);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getRowData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME+" where "+ITEM_NAME+"=?",new String[]{name});
//        String s = dumpCursorToString(result);
//        Log.d(TAG, "getRowData: "+s);
        return result;
    }
    public Cursor getNames()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor names=db.rawQuery("select "+ITEM_NAME+" from "+TABLE_NAME+" ;",null);
        return names;
    }
    public boolean updateData(String id, String name, String date, String dueDate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME,name);
        contentValues.put(DATE,date);
        contentValues.put(DUE_DATE,dueDate);
        int res =db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        if(res>0)
            return true;
        else
            return false;
    }
    public Integer deleteData(String id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
}
