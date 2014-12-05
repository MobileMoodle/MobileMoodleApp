package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import edu.umn.moodlemanaged.adapters.CustomTabListener;

public class MoodleManaged extends Activity {
    public static DBHelper mydb;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.deleteDatabase("local.db");
        this.deleteDatabase("test.db");
        mydb = new DBHelper(this);

        Event e = new Event("Read Mathis Chapters 27-35", "CSCI-5115", false,"2014/11/08 13:24:36","assignment",1,1,5,100, 95,true);
        Event e2 = new Event("Read ABC Chapters 27-35", "CSCI-5609",false,"2014/11/18 13:24:36","assignment",2,2,10,100, -1,true);
        Event e3 = new Event("Exam", "CSCI-5609",false,"2014/11/18 13:44:36","exam",3,2,35,100, 94,true);
        Event e4 = new Event("Exam 2", "CSCI-5115",false,"2014/11/18 13:44:36","exam",4,1,35,100, 88,true);
        Course c1 = new Course(1,"CSCI 5115","User Interface Design, Implementation & Evaluation","This is a course.");
        Course c2 = new Course(2,"CSCI 5609","Visualization","This is another course.");
        mydb.insertEventWithGrade(e);
        mydb.insertEventWithGrade(e2);
        mydb.insertEventWithGrade(e3);
        mydb.insertEventWithGrade(e4);

        mydb.insertCourse(c1);
        mydb.insertCourse(c2);
        mydb.getGrades(2,"assignment");
        mydb.getGrades(2,"exam");
        //mydb.getCourses();
        //mydb.getEvents();
        getData();
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
       
        actionBar.setDisplayShowTitleEnabled(true);
        
        // Creating Planning Tab
        Tab tab = actionBar.newTab()
        		.setText("Schedule")
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
    public void getData(){
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue rq = new RequestQueue(cache,network);
        rq.start();
        //String url = "http://104.131.162.115/";
        String url = "http://127.0.0.1/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.i("response",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.i("Error",error.toString());
                    }
                });
        rq.add(stringRequest);
    }


}