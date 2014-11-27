package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.CoursesCustomAdapter;

public class CoursesFragment extends Fragment {
	private ListView coursesList;
	private CoursesCustomAdapter courseAdapter;
	private ArrayList<Course> courseArray = new ArrayList<Course>();
	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        // TODO Integrate with cloud

        DBHelper db = MoodleManaged.mydb;
        ArrayList<Course> list = db.getCourses();
        for (Course course : list){
            courseArray.add(course);
        }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.courses_tab, container, false);	
		
		/**
		  * Set item into adapter
		  */
		courseAdapter = new CoursesCustomAdapter(getActivity(), R.layout.courses_tab_row, courseArray);
		coursesList = (ListView) view.findViewById(R.id.courses_list);
		coursesList.setItemsCanFocus(false);
		coursesList.setAdapter(courseAdapter);
		
		return view;
	}	
}
