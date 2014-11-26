package edu.umn.moodlemanaged;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by bbiiggppiigg on 14/11/17.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "test.db";
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("db on create","");
        sqLiteDatabase.execSQL("create table events( id integer primary key ,cid integer , event_type text,name text ,due text , done boolean)");
        sqLiteDatabase.execSQL("create table courses(id integer primary key, name text, instructor text, syllabus text)");
        sqLiteDatabase.execSQL("create table scores(id integer primary key, eid integer, score real, total real, percentage integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        Log.i("db on Upgrade","");
        sqLiteDatabase.execSQL("drop table if exists events");
        onCreate(sqLiteDatabase);
    }
    public void clearAll(){
        Log.i("db on clear all","");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("truncate table if exists events");
        db.execSQL("truncate table if exists courses");
        db.execSQL("truncate table if exists scores");
    }
    public boolean insertEvent(Event e){
        Log.i("debug","inserting event :"+e.text);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",e.id);
        cv.put("cid",0);
        cv.put("name",e.text);
        cv.put("event_type",e.event_type);
        cv.put("due",e.time.toString());
        cv.put("done", e.isChecked);
        db.insertWithOnConflict("events",null,cv,SQLiteDatabase.CONFLICT_REPLACE);
        return true;
    }
    public ArrayList<Event> getEvents(){
        ArrayList<Event> ret = new ArrayList<Event>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from events",null );
        Log.i("debug","calling get Events"+cursor.getCount());

        cursor.moveToFirst();
        while(!cursor.isLast()){
            Log.i("event name",cursor.getString(cursor.getColumnIndex("name"))+" id = "+cursor.getString(cursor.getColumnIndex("id")));
            Log.i("time",cursor.getString(cursor.getColumnIndex("due")));
            ret.add(new Event(cursor));
            cursor.moveToNext();
        }
        Log.i("event name",cursor.getString(cursor.getColumnIndex("name"))+" id = "+cursor.getString(cursor.getColumnIndex("id")));
        ret.add(new Event(cursor));
        Collections.sort(ret);
        return ret;
    }
}
