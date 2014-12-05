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
import java.util.Comparator;

/**
 * Created by bbiiggppiigg on 14/11/17.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "test.db";
    private static final String[ ] EVENT_TYPES= {"assignment","exam","project","quiz"};
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("db on create","");
        sqLiteDatabase.execSQL("create table events( id integer primary key ,cid integer , event_type text,name text, course_name text, due text , done boolean)");
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
        Log.e("insert grades isFInal",""+g.fixed);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",g.id);
        cv.put("eid",g.eid);
        //cv.put("name",g.name);

        cv.put("fixed",g.fixed?1:0);
        cv.put("percentage",g.percentage);
        cv.put("score",g.score);
        cv.put("total",g.total);
        Log.i("Insertin score",""+g.score);
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
        cv.put("course_name", e.courseName);
        cv.put("event_type",e.event_type);
        cv.put("due",e.time.toString());
        cv.put("done", e.isChecked);
        db.insertWithOnConflict("events",null,cv,SQLiteDatabase.CONFLICT_REPLACE);
        return true;
    }
    public void insertEventWithGrade(Event e ){
        Log.i("debug","inserting event :"+e.text);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",e.id);
        cv.put("cid",e.cid);
        cv.put("name",e.text);
        cv.put("course_name", e.courseName);
        cv.put("event_type",e.event_type);
        cv.put("due",e.time.toString());
        cv.put("done", e.isChecked);
        db.insertWithOnConflict("events",null,cv,SQLiteDatabase.CONFLICT_REPLACE);
        for ( String etype : EVENT_TYPES){
            //Log.i("insert with grade event type = ",e.event_type+ " "+etype);
            if(etype.equals(e.event_type)){
               /* if(e.score != -1)
                {
                    Log.e("debug","isFinal? " + e.isFinal + ". Text is: " + e.text);
                    Grade temp = new Grade(e.id,e.id,e.text,e.isFinal,e.percentage, 100,e.total);
                    Log.e("debug","isFinalAFTER? " + temp.isFinal() + ". Text is: " + temp.name);
                    insertGrade(temp);
                }
                else
                {*/
                    Log.e("debug","isFinal? " + e.isFinal + ". Text is: " + e.text);
                    insertGrade(new Grade(e.id,e.id,e.text,e.isFinal,e.percentage,e.total));
                //}
            }
        }


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
        cursor.close();
        return ret;
    }
    public void setGrade(int id,double score){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor  cursor;
        cursor = db.rawQuery("select g.id as id, g.fixed as fixed, g.total as total , g.score as score , g.percentage as percentage ,e.name as name from grades as g, events as e where g.id ="+id+" and e.id = g.id",null);
        cursor.moveToFirst();

        //Log.i("num",cursor.getString(0));

        Grade g = new Grade(cursor);
        g.score  = score;
        g.fixed = true;
        insertGrade(g);

    }
    public ArrayList<Event> getEventsSortName(){
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
        Collections.sort(ret, StringDescComparator);
        cursor.close();
        return ret;
    }
    /*public Grade createGrade(Cursor cursor){
        boolean fixed = new Boolean(cursor.getString(cursor.getColumnIndex("fixed")));
        int percentage = new Integer(cursor.getString(cursor.getColumnIndex("percentage")));
        int id = new Integer(cursor.getString(cursor.getColumnIndex("id")));
        int eid = new Integer(cursor.getString(cursor.getColumnIndex("eid")));
        double score = new Double(cursor.getString(cursor.getColumnIndex("score")));
        double total = new Double(cursor.getString(cursor.getColumnIndex("total")));
        return Grade(id,eid,fixed,score,total)
    }*/
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
        cursor.close();
        return ret;
    }

    public ArrayList<Grade> getGrades(){
        ArrayList<Grade> ret = new ArrayList<Grade>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select g.id as id,  g.fixed as fixed , g.score as score, g.total  as total , g.percentage  as percentage, e.name  as name, e.event_type as event_type, c.number as number from grades as g , events as e , courses as c where e.id = g.eid and e.cid =  c.id",null);
            cursor.moveToFirst();
            Log.i("debug","calling get Grades "+cursor.getCount());
            while(!cursor.isAfterLast()){
            Log.i("grade",cursor.getString(cursor.getColumnIndex("score")));
            ret.add(new Grade(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return ret;
    }
    public ArrayList<Grade> getGrades(String type){
        ArrayList<Grade> ret = new ArrayList<Grade>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        //String query = );
        //Log.i("query = ",query);
        cursor = db.rawQuery("select g.fixed as fixed , g.score as score, g.total  as total , g.percentage  as percentage, e.name  as name, e.event_type as event_type, c.number as number from grades as g , events as e , courses as c where e.id = g.eid and e.cid =  c.id and e.event_type = ?",new String[]{type});
        cursor.moveToFirst();
        Log.i("debug","calling get Grades of type "+type);
        while(!cursor.isAfterLast()){
            Log.i("grade name ",cursor.getString(cursor.getColumnIndex("name"))+ " type = "+cursor.getString(cursor.getColumnIndex("event_type")));
            ret.add(new Grade(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return ret;
    }

    public ArrayList<Grade> getGrades(int cid,String type){
        ArrayList<Grade> ret = new ArrayList<Grade>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        //String query = );
        //Log.i("query = ",query);
        cursor = db.rawQuery("select g.id as id ,g.fixed as fixed , g.score as score, g.total  as total , g.percentage  as percentage, e.name  as name, e.event_type as event_type, c.number as number from grades as g , events as e , courses as c where e.id = g.eid and e.cid =  c.id and cid = ? and e.event_type = ?",new String[]{cid+"",type});
        cursor.moveToFirst();
        Log.i("debug","calling get Grades of type "+type);
        while(!cursor.isAfterLast()){
            Log.i("grade name ",cursor.getString(cursor.getColumnIndex("name"))+ " type = "+cursor.getString(cursor.getColumnIndex("event_type")));
            ret.add(new Grade(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return ret;
    }
    public int numOfEvent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select count(*) as num from events", null);
        cursor.moveToFirst();

        int ret =  new Integer(cursor.getString(cursor.getColumnIndex("num")));
        cursor.close();
        return ret;
    }

    public static Comparator<Event> StringDescComparator = new Comparator<Event>()
    {
        @Override
        public int compare(Event lhs, Event rhs) {
            String courseName1 = lhs.courseName;
            String courseName2 = rhs.courseName;

            return courseName2.compareToIgnoreCase(courseName1);
        }
    };
}
