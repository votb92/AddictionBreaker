package com.example.addictionbreaker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";
    public static final String COL_4 = "ADDICTION";
    public static final String COL_5 = "FREQUENCY";
    public static final String COL_6 = "COST";
    public static final String COL_7 = "DAY";
    public static final String COL_8 = "STARTYEAR";
    public static final String COL_9 = "STARTMONTH";
    public static final String COL_10 = "STARTDAY";
    public static final String COL_11 = "STARTHOUR";
    public static final String COL_12 = "STARTMINUTE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, " +
                "AGE INTEGER, ADDICTION TEXT, FREQUENCY INTEGER, COST INTEGER, DAY INTEGER, STARTYEAR INTEGER, STARTMONTH INTEGER,STARTDAY INTEGER,STARTHOUR INTEGER,STARTMINUTE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean updateDay(long days){
        String numberOfDay = Long.toString(days);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7, numberOfDay);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"1"});
        return true;
    }

    public boolean insertData(String name, String age, String addiction, String frequency, String cost, String startYear, String startMonth, String startDay, String startHour, String startMinute){
        String firstDay = "0";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, age);
        contentValues.put(COL_4, addiction);
        contentValues.put(COL_5, frequency);
        contentValues.put(COL_6, cost);
        contentValues.put(COL_7, firstDay);
        contentValues.put(COL_8, startYear);
        contentValues.put(COL_9, startMonth);
        contentValues.put(COL_10, startDay);
        contentValues.put(COL_11, startHour);
        contentValues.put(COL_12, startMinute);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);
        return res;
    }
}
