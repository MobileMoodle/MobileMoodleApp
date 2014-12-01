package edu.umn.moodlemanaged;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        String s;
        Format formatter;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        formatter = new SimpleDateFormat("yyyy/MM/dd ");
        s = formatter.format(calendar.getTime());
        NewAssignmentActivity.tempEventDate = s;
    }
}

