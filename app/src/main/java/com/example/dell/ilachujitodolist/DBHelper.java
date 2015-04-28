package com.example.dell.ilachujitodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 4/26/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "school_db", null,1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "create table todo_db (" +
                " _id integer primary key autoincrement, " +
                " title text"+
                "due text);");
    }
    public void onUpgrade(SQLiteDatabase db , int oldVer, int newVer){
        db.execSQL("DROP TABLE IF EXISTS todo_db");
        // Recreate table
        onCreate(db);
    }
}
