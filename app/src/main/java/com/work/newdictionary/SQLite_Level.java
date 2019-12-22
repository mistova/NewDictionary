package com.work.newdictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite_Level extends SQLiteOpenHelper {

    public SQLite_Level(Context context) {
        super(context, "PekMan", null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        String sqlHist = " create table Hist ( id Integer primary key autoincrement, word String, cnt Integer)";
        db.execSQL(sqlHist);
        String sqlBase = " create table Base ( id Integer primary key autoincrement, baseId Integer)";
        db.execSQL(sqlBase);
        ContentValues val = new ContentValues();
        val.put("baseId", 1);
        db.insert("Base", null, val);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Hist");
    }
}