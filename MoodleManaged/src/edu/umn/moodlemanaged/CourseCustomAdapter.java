package edu.umn.moodlemanaged;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CourseCustomAdapter extends ArrayAdapter<String> {
	Context context;
	int layoutResourceId;
	ArrayList<String> courses = new ArrayList<String>();

	public CourseCustomAdapter(Context context, int layoutResourceId, ArrayList<String> courses) {
		super(context, layoutResourceId, courses);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.courses = courses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CourseHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new CourseHolder();
			holder.courseName = (TextView) row.findViewById(R.id.course_view_course_name);
			holder.btnNotifications = (Button) row.findViewById(R.id.notifications_bubble);
			holder.btnSyllabus = (Button) row.findViewById(R.id.syllabus_btn);
			holder.btnOfficeHours = (Button) row.findViewById(R.id.office_hours_btn);
			row.setTag(holder);
		} else {
			holder = (CourseHolder) row.getTag();
		}
		
		String user = courses.get(position);
		holder.courseName.setText(user);
		
		holder.btnNotifications.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Edit Button Clicked", "**********");
				Toast.makeText(context, "Edit button Clicked",
						Toast.LENGTH_LONG).show();
			}
		});
		
		holder.btnSyllabus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Delete Button Clicked", "**********");
				Toast.makeText(context, "Delete button Clicked",
						Toast.LENGTH_LONG).show();
			}
		});
		
		holder.btnOfficeHours.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Delete Button Clicked", "**********");
				Toast.makeText(context, "Delete button Clicked",
						Toast.LENGTH_LONG).show();
			}
		});
		
		return row;

	}

	static class CourseHolder {
		TextView courseName;
		Button btnNotifications;
		Button btnSyllabus;
		Button btnOfficeHours;
	}
}
