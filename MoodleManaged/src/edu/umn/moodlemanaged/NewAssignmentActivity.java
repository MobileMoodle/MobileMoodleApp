package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
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
    private ArrayList<Course> courselist ;
    private ArrayAdapter<String> courseNameAdapter;
    public static String tempEventText;
    public static String tempEventDate;
    public static String tempEventDescription;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_assignment);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        courseNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        final Spinner courses = (Spinner) findViewById(R.id.course_spinner_nassign);
        courseNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DBHelper mydb = MoodleManaged.mydb;
        courselist = mydb.getCourses();
        for(Course c : courselist){
            courseNameAdapter.add(c.number);
        }

       /* coursesAdapter.add("CSCI 4131");
        coursesAdapter.add("CSCI 5115");
        coursesAdapter.add("KIN 5001");
        coursesAdapter.add("PSY 3011");*/
        courses.setAdapter(courseNameAdapter);

        final EditText assignmentTitle = (EditText) findViewById(R.id.editText);
        final EditText descriptionInput = (EditText) findViewById(R.id.descriptionInput);
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
                int pos = courses.getSelectedItemPosition();
                Course currentClass = courselist.get(pos);

                // + ": " + assignmentTitle.getText();
                tempEventText = assignmentTitle.getText().toString();
                tempEventDescription = descriptionInput.getText().toString();
                Log.i("Add Event :", "Event Name = "+tempEventText+"Course Id = "+currentClass.id+" Description = "+tempEventDescription);

                if(tempEventDate != "" && tempEventDescription != "" && tempEventText != "")
                {
                    int id = MoodleManaged.mydb.getEvents().size() + 1;
                    DBHelper db = MoodleManaged.mydb;
                    Event temp = new Event(tempEventText, false,tempEventDate, tempEventDescription,id,currentClass.id);
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
