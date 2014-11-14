package edu.umn.moodlemanaged;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        TextView time = (TextView)getActivity().findViewById(R.id.timeText);
//        if(hourOfDay > 12)
//        {
//            if(minute < 10)
//            {
//                time.setText("Time: " + (hourOfDay-12) + ":0" + minute + " PM");
//            }
//            else
//            {
//                time.setText("Time: " + (hourOfDay-12) + ":" + minute + " PM");
//            }
//        }
//        else
//        {
//            if(minute < 10)
//            {
//                time.setText("Time: " + hourOfDay + ":0" + minute + " AM");
//            }
//            else
//            {
//                time.setText("Time: " + hourOfDay + ":" + minute + " AM");
//            }
//        }
    }
}