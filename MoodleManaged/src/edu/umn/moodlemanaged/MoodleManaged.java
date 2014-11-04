package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
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
}