package edu.umn.moodlemanaged;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
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
        sqLiteDatabase.execSQL("create table courses(id integer primary key, number text, name text, syllabus text)");
        sqLiteDatabase.execSQL("create table grades(id integer primary key, eid integer,fixed int , score real, total real, percentage integer)");
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
        db.execSQL("truncate table if exists grades");
    }
    public boolean insertGrade(Grade g){
        Log.i("insert grades ",""+g.id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",g.id);
        cv.put("eid",g.eid);
        //cv.put("name",g.name);
        cv.put("fixed",g.fixed);
        cv.put("percentage",g.percentage);
        cv.put("score",g.score);
        cv.put("total",g.total);
        db.insertWithOnConflict("grades",null,cv,SQLiteDatabase.CONFLICT_REPLACE);
        return true;
    }
    public boolean insertCourse(Course c){
        Log.i("insert course",c.name);
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",c.id);
        cv.put("number",c.number);
        cv.put("name",c.name);
        cv.put("syllabus",c.syllabus);
        db.insertWithOnConflict("courses",null,cv,SQLiteDatabase.CONFLICT_REPLACE);
        return true;
    }

    public boolean insertEvent(Event e){
        Log.i("debug","inserting event :"+e.text);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",e.id);
        cv.put("cid",e.cid);
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
        while(!cursor.isAfterLast()){
            Log.i("event name",cursor.getString(cursor.getColumnIndex("name"))+" id = "+cursor.getString(cursor.getColumnIndex("id")));
            Log.i("time",cursor.getString(cursor.getColumnIndex("due")));
            ret.add(new Event(cursor));
            cursor.moveToNext();
        }

        Collections.sort(ret);
        return ret;
    }

    public ArrayList<Course> getCourses(){
        ArrayList<Course> ret = new ArrayList<Course>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from courses",null);
        cursor.moveToFirst();
        Log.i("debug","calling get Courses"+cursor.getCount());
        while(!cursor.isAfterLast()){
            Log.i("course name",cursor.getString(cursor.getColumnIndex("number"))+" "+cursor.getString(cursor.getColumnIndex("name")));
            ret.add(new Course(cursor));
            cursor.moveToNext();
        }
        return ret;
    }

    public ArrayList<Grade> getGrades(){
        ArrayList<Grade> ret = new ArrayList<Grade>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select g.fixed as fixed , g.score as score, g.total  as total , g.percentage  as percentage, e.name  as name, e.event_type as event_type, c.number as number from grades as g , events as e , courses as c where e.id = g.eid and e.cid =  c.id",null);
        cursor.moveToFirst();
        Log.i("debug","calling get Grades "+cursor.getCount());
        while(!cursor.isAfterLast()){
            Log.i("grade name",cursor.getString(cursor.getColumnIndex("name")));
            ret.add(new Grade(cursor));
            cursor.moveToNext();
        }
        return ret;
    }
    public int numOfEvent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select count(*) as num from events", null);
        cursor.moveToFirst();

        return new Integer(cursor.getString(cursor.getColumnIndex("num")));
    }
}
