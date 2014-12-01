package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.umn.moodlemanaged.adapters.CustomTabListener;

public class MoodleManaged extends Activity {
    public static DBHelper mydb;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.deleteDatabase("local.db");
       //this.deleteDatabase("test.db");
        mydb = new DBHelper(this);

        Event e = new Event("CSCI 5115: Read Mathis Chapters 27-35", false,"2014/11/08 13:24:36","assignment",1,1);
        Event e2 = new Event("CSCI 5609: Read ABC Chapters 27-35", false,"2014/11/18 13:24:36","assignment",2,2);
        Event e3 = new Event("CSCI 8001: Exam", false,"2014/11/18 13:44:36","exam",3,2);
        Course c1 = new Course(1,"CSCI 5115","User Interface Design, Implementation & Evaluation","This is a course.");
        Course c2 = new Course(2,"CSCI 5609","Visualization","This is another course.");
        Grade g1 = new Grade(1,1,"Homework 1",true,5,20,30);
        mydb.insertEvent(e);
        mydb.insertEvent(e2);
        mydb.insertEvent(e3);
        mydb.insertCourse(c1);
        mydb.insertCourse(c2);
        mydb.insertGrade(g1);
        mydb.getGrades();
        //mydb.getCourses();
        //mydb.getEvents();

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
       
        actionBar.setDisplayShowTitleEnabled(true);
        
        // Creating Planning Tab
        Tab tab = actionBar.newTab()
        		.setText("Planning")
        		.setTabListener(new CustomTabListener<PlanningFragment>(this, "planning", PlanningFragment.class));
        
        actionBar.addTab(tab, 0, false);
        
        
        // Creating Courses Tab
        tab = actionBar.newTab()
        		.setText("Courses")
        		.setTabListener(new CustomTabListener<CoursesFragment>(this, "courses", CoursesFragment.class));

        actionBar.addTab(tab, 1, true);      
        
        // Creating Grades Tab
        tab = actionBar.newTab()
        		.setText("Grades")
        		.setTabListener(new CustomTabListener<GradesFragment>(this, "grades", GradesFragment.class));

        actionBar.addTab(tab, 2, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        int id = item.getItemId();
        if (id == R.id.action_events) {
            //TODO
            return true;
        } else if (id == R.id.action_account) {
            //TODO
            return true;
        } else if (id == R.id.action_settings) {
            //TODO
            return true;
        } else if (id == R.id.action_info) {
            //TODO
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Prevent accidental exiting of app
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .show();
    }
}