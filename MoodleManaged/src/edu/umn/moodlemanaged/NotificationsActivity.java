package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import de.timroes.android.listview.EnhancedListView;
import edu.umn.moodlemanaged.adapters.NotificationsCustomAdapter;

/**
 * Created by kent on 11/4/14.
 */
public class NotificationsActivity extends Activity {
    private ArrayList<MMNotification> mmNotificationArray = new ArrayList<MMNotification>();
    private NotificationsCustomAdapter notificationAdapater;
    private ArrayAdapter<String> coursesAdapter;
    private EnhancedListView notList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Spinner courses = (Spinner) findViewById(R.id.course_spinner_n);
        coursesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesAdapter.add("CSCI 4131");
        coursesAdapter.add("CSCI 5115");
        coursesAdapter.add("KIN 5001");
        coursesAdapter.add("PSY 3011");
        courses.setAdapter(coursesAdapter);

        mmNotificationArray.add(new MMNotification("Midterm 1 Graded: 88%"));
        mmNotificationArray.add(new MMNotification("Class Cancelled Due to Snow (11/11)"));
        mmNotificationArray.add(new MMNotification("Assignment 7 Due Tuesday, November 12 (11:55pm)"));

        /**
         * Set item into adapter
         */
        notificationAdapater = new NotificationsCustomAdapter(this, R.layout.notifications, mmNotificationArray);
        notList = (EnhancedListView) findViewById(R.id.notifications_list);
        notList.setDismissCallback(new EnhancedListView.OnDismissCallback() {
            @Override
            public EnhancedListView.Undoable onDismiss(EnhancedListView enhancedListView, int i) {
                final int position = i;
                // Store item for later restore
                final MMNotification item = notificationAdapater.getItem(position);
                notificationAdapater.remove(position);
                // return an Undoable
                return new EnhancedListView.Undoable() {
                    // Reinsert the item to the adapter
                    @Override public void undo() {
                        notificationAdapater.insert(position, item);
                        notificationAdapater.notifyDataSetChanged();
                    }

                    // Return a string for your item
                    @Override public String getTitle() {
                        return "Deleted '" + item.not + "'";
                    }
            };
        }});
        notList.setAdapter(notificationAdapater);
        notList.enableSwipeToDismiss();
        notList.setSwipeDirection(EnhancedListView.SwipeDirection.END);
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

}