package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.umn.moodlemanaged.adapters.CustomTabListener;

public class MoodleManaged extends Activity {
    /** 
     * Called when the activity is first created. 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
       
        actionBar.setDisplayShowTitleEnabled(true);
        
        /** Creating Events Tab */
        Tab tab = actionBar.newTab()
        		.setText("Events")
        		.setTabListener(new CustomTabListener<EventsFragment>(this, "events", EventsFragment.class));
        
        actionBar.addTab(tab, 0, false);
        
        
        /** Creating Courses Tab */
        tab = actionBar.newTab()
        		.setText("Courses")
        		.setTabListener(new CustomTabListener<CoursesFragment>(this, "courses", CoursesFragment.class));

        actionBar.addTab(tab, 1, true);      
        
        /** Creating Grades Tab */
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
        switch (item.getItemId()) {
            case R.id.action_events:
                //TODO
                return true;
            case R.id.action_account:
                //TODO
                return true;
            case R.id.action_settings:
                //TODO
                return true;
            case R.id.action_info:
                //TODO
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}