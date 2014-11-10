package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.NotificationsCustomAdapter;

/**
 * Created by kent on 11/4/14.
 */
public class NotificationsActivity extends Activity {
    private SparseArray<NotificationsGroup> groups = new SparseArray<NotificationsGroup>();
    ArrayAdapter<String> coursesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Populate notifications data
        createData();

        Spinner courses = (Spinner) findViewById(R.id.course_spinner_n);
        coursesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesAdapter.add("CSCI 4131");
        coursesAdapter.add("CSCI 5115");
        coursesAdapter.add("KIN 5001");
        coursesAdapter.add("PSY 3011");
        courses.setAdapter(coursesAdapter);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.notifications_list);
        NotificationsCustomAdapter adapter = new NotificationsCustomAdapter(this, groups);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createData() {
        NotificationsGroup notificationsGroup = new NotificationsGroup("Exams");
        notificationsGroup.children.add("Midterm 1 Graded: 88%");
        groups.append(0, notificationsGroup);
        notificationsGroup = new NotificationsGroup("Quizzes");
        groups.append(1, notificationsGroup);
        notificationsGroup = new NotificationsGroup("Projects");
        groups.append(2, notificationsGroup);
        notificationsGroup = new NotificationsGroup("Assignments");
        notificationsGroup.children.add("Assignment 7 Due: Tuesday, 11:55pm");
        groups.append(3, notificationsGroup);
        notificationsGroup = new NotificationsGroup("Announcements");
        notificationsGroup.children.add("Class Cancelled Due to Snow: Tuesday, Nov 11th");
        groups.append(4, notificationsGroup);
        notificationsGroup = new NotificationsGroup("Personal");
        groups.append(5, notificationsGroup);
    }
}