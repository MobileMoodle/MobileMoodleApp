package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.DialogFragment;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.PlanningCustomAdapter;

public class NewAssignmentActivity extends FragmentActivity
{
    private ArrayAdapter<String> coursesAdapter;
    public static String tempEventText;
    public static String tempEventDate;
    public static String tempEventDescription;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_assignment);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Spinner courses = (Spinner) findViewById(R.id.course_spinner_nassign);
        coursesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesAdapter.add("CSCI 4131");
        coursesAdapter.add("CSCI 5115");
        coursesAdapter.add("KIN 5001");
        coursesAdapter.add("PSY 3011");
        courses.setAdapter(coursesAdapter);

        final EditText assignmentTitle = (EditText) findViewById(R.id.editText);
        assignmentTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                // if keydown and "enter" is pressed
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    return true;
                }
                return false;
            }
        });
        //TIME picker
        Button timePicker = (Button) findViewById(R.id.chooseTime);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timepicker");
            }
        });
        //DATE picker
        Button datePicker = (Button) findViewById(R.id.chooseDate);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });
        //SUBMIT button
        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                Toast toast = Toast.makeText(getApplicationContext(), "Event Added!", Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER|Gravity.BOTTOM, 0, 0);
//                toast.show();
                String currentClass = courses.getSelectedItem().toString() + ": " + assignmentTitle.getText();
                tempEventText = currentClass;
                tempEventDescription = "TESTIES 1";
                if(tempEventDate != "" && tempEventDescription != "" && tempEventText != "")
                {
                    int id = MoodleManaged.mydb.getEvents().size() + 1;
                    Event temp = new Event(tempEventText, false,tempEventDate, tempEventDescription, id);
                    MoodleManaged.mydb.insertEvent(temp);
                    tempEventText = "";
                    tempEventDescription = "";
                    tempEventDate = "";

                }
                finish();

            }
        });

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
