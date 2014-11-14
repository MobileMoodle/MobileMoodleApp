package edu.umn.moodlemanaged;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView date = (TextView)getActivity().findViewById(R.id.dateText);
        if(day < 10)
        {
            if(month < 10)
            {
                date.setText("Date: 0" + month + "/0" + day + "/" + year);
            }
            else
            {
                date.setText("Date: " + month + "/0" + day + "/" + year);
            }
        }
        else
        {
            if(month < 10)
            {
                date.setText("Date: 0" + month + "/" + day + "/" + year);
            }
            else
            {
                date.setText("Date: " + month + "/" + day + "/" + year);
            }
        }
    }
}

