package com.example.addictionbreaker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.addictionbreaker.model.User;

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
    public static final String COL_13 = "REMINDERHOUR";
    public static final String COL_14 = "REMINDERMINUTE";
    public static final String COL_15 = "PROGRESS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, " +
                "AGE INTEGER, ADDICTION TEXT, FREQUENCY INTEGER, COST INTEGER, DAY INTEGER, STARTYEAR INTEGER, STARTMONTH INTEGER,STARTDAY INTEGER,STARTHOUR INTEGER,STARTMINUTE INTEGER," +
                "REMINDERHOUR INTEGER, REMINDERMINUTE INTEGER, PROGRESS INTEGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean updateProgress(int progress){
        String numberOfDay = Integer.toString(progress);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_15, numberOfDay);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"1"});
        return true;
    }

    public boolean updateDay(long days){
        String numberOfDay = Long.toString(days);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7, numberOfDay);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"1"});
        return true;
    }

    public boolean updateName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"1"});
        return true;
    }

    public boolean updateStartInfo(String year, String month, String day, String hour, String minute){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_8, year);
        contentValues.put(COL_9, month);
        contentValues.put(COL_10, day);
        contentValues.put(COL_11, hour);
        contentValues.put(COL_12, minute);
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

    public String getUserName(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);
        String name = "";
        if(res.moveToFirst()){
            res.moveToFirst();
            name = res.getString(1);
        }
        res.close();
        return name;
    }

    public void addReminderTime(int hour, int minute){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_13, hour);
        contentValues.put(COL_14, minute);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public int[] getRemindertime(){
        int times[] = new int[2];
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME,null);
        if(res.moveToFirst()){
            res.moveToFirst();
            times[0] = res.getInt(12);
            times[1] = res.getInt(13);
        }
        res.close();
        return times;
    }
}
