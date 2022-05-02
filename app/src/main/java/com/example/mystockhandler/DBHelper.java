package com.example.mystockhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "MyStock.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Productdetails(name TEXT primary key, bPrice INT, sPrice INT, bNumber TEXT, exDate DATE )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Productdetails");

    }
    public Boolean insertitemdata(String name, String bPrice, String sPrice, String bNumber, String exDate)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("bPrice",bPrice);
        cv.put("sPrice",sPrice);
        cv.put("bNumber",bNumber);
        cv.put("exDate",exDate);
        long result=DB.insert("Productdetails",null, cv);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean updateitemdata(String name, String bPrice, String sPrice, String bNumber, String exDate)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bPrice",bPrice);
        cv.put("sPrice",sPrice);
        cv.put("bNumber",bNumber);
        cv.put("exDate",exDate);
        Cursor cursor = DB.rawQuery("Select * from Productdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {

            long result = DB.update("Productdetails", cv, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

    public Boolean deletedata(String name)
    {
        SQLiteDatabase DB =this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Productdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {

            long result = DB.delete("Productdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB =this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Productdetails",null);
        return cursor;

    }

}
