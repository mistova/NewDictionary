package com.work.newdictionary;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DataSource {
    SQLiteDatabase db;
    SQLite_Level mydb;

    public DataSource(Context context) {
        mydb = new SQLite_Level(context);
    }

    public void open() {
        db = mydb.getWritableDatabase(); // read and write
    }

    public void close() {
        mydb.close();
    }

    public void clear() {
        db.execSQL("delete  from Hist");
    }

    public void createWord(String word) {
        ContentValues val = new ContentValues();
        val.put("word", word);
        val.put("cnt", 1);
        db.insert("Hist", null, val);
    }

    public void createId(Integer id) {
        ContentValues val = new ContentValues();
        val.put("baseId", id);
        db.insert("Base", null, val);
    }

    public void updateId(Integer id) {
        ContentValues val = new ContentValues();
        val.put("baseId", id);
        db.update("Base", val, "id = 1", null);
    }

    public int getId(){
        String columns[] = {"id", "baseId"};
        Cursor c = db.query("Base", columns, null, null, null, null, "id");
        c.moveToFirst();
        return c.getInt(1);
    }

    public boolean isSet(String w){
        Cursor c = db.rawQuery("SELECT * FROM " + "Hist" + " WHERE " + "word" + " ='" + w + "'", null);
        return c.getCount() == 0;
    }
    public void deleteWord(String word) {
        Cursor c = db.rawQuery("DELETE FROM " + "Hist" + " WHERE " + "word" + " ='" + word + "'", null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            int id = c.getInt(0);

            db.delete("Hist", "id=" + id, null);
            c.moveToNext();
        }
    }

    public ArrayList<String> listWords(){
        String columns[] = {"id", "word", "cnt"};
        Cursor c = db.query("Hist", columns, null, null, null, null, "id");
        c.moveToFirst();
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> arrayList2 = new ArrayList<String>();
        while (!c.isAfterLast()) {
            String word = c.getString(1);
            arrayList.add(word);
            c.moveToNext();
        }
        int i = 0, n = arrayList.size();
        while (n != arrayList2.size()){
            arrayList2.add(arrayList.get(n - i - 1));
            i++;
        }
        return arrayList2;
    }
}