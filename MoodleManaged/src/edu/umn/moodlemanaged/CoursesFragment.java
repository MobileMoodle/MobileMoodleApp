package edu.umn.moodlemanaged;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import edu.umn.moodlemanaged.adapters.CoursesCustomAdapter;

public class CoursesFragment extends Fragment {
	private ListView coursesList;
	private CoursesCustomAdapter courseAdapter;
	private ArrayList<Course> courseArray = new ArrayList<Course>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.courses_tab, container, false);
		
		/**
		 * Add Courses
		 */
		courseArray.add(new Course("CSCI 5801", "Software Engineering"));
		courseArray.add(new Course("GCD 3022", "Genetics"));
		courseArray.add(new Course("CSCI 3081", "Program Design"));
		courseArray.add(new Course("CSCI 5115", "User Interface Design ..."));
		
		/**
		  * set item into adapter
		  */
		courseAdapter = new CoursesCustomAdapter(getActivity(), R.layout.courses_tab_row, courseArray);
		coursesList = (ListView) view.findViewById(R.id.courses_list);
		coursesList.setItemsCanFocus(false);
		coursesList.setAdapter(courseAdapter);
		
		/**
		  * get on item click listener
		  */
		coursesList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
			Log.i("List View Clicked", "**********");
		    Toast.makeText(getActivity(), "List View Clicked:" + position, Toast.LENGTH_LONG).show();
		   }
		});
		
		return view;
	}	
}
