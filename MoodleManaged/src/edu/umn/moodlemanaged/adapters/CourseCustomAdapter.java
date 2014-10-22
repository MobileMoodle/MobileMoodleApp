package edu.umn.moodlemanaged.adapters;

import java.util.ArrayList;

import edu.umn.moodlemanaged.Course;
import edu.umn.moodlemanaged.R;
import edu.umn.moodlemanaged.R.id;

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

public class CourseCustomAdapter extends ArrayAdapter<Course> {
	Context context;
	int layoutResourceId;
	ArrayList<Course> courses = new ArrayList<Course>();

	public CourseCustomAdapter(Context context, int layoutResourceId, ArrayList<Course> courses) {
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
			holder.courseID = (TextView) row.findViewById(R.id.course_view_course_id);
			holder.btnNotifications = (Button) row.findViewById(R.id.notifications_bubble);
			holder.btnSyllabus = (Button) row.findViewById(R.id.syllabus_btn);
			holder.btnOfficeHours = (Button) row.findViewById(R.id.office_hours_btn);
			row.setTag(holder);
		} else {
			holder = (CourseHolder) row.getTag();
		}
		
		Course course = courses.get(position);
		holder.courseName.setText(course.getName());
		holder.courseID.setText(course.getID());
		
		holder.btnNotifications.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Edit Button Clicked", "**********");
				Toast.makeText(context, "Notifications button Clicked",
						Toast.LENGTH_LONG).show();
			}
		});
		
		holder.btnSyllabus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Delete Button Clicked", "**********");
				Toast.makeText(context, "Syllabus button Clicked",
						Toast.LENGTH_LONG).show();
			}
		});
		
		holder.btnOfficeHours.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Delete Button Clicked", "**********");
				Toast.makeText(context, "Office Hours button Clicked",
						Toast.LENGTH_LONG).show();
			}
		});
		
		return row;

	}

	static class CourseHolder {
		TextView courseID;
		TextView courseName;
		Button btnNotifications;
		Button btnSyllabus;
		Button btnOfficeHours;
	}
}
