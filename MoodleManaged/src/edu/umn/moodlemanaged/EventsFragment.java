package edu.umn.moodlemanaged;

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

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.EventsCustomAdapter;

public class EventsFragment extends Fragment {
	private ListView eventsList;
	private EventsCustomAdapter eventAdapter;
	private ArrayList<Event> eventsArray = new ArrayList<Event>();
	
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * Add Courses
		 */
        eventsArray.add(new Event("Assignment 1", "CSCI 5115", "", "", "11.5", "11:00PM", "", ""));
        eventsArray.add(new Event("Assignment 2", "CSCI 5115", "", "", "11.5", "11:00PM", "", ""));
        eventsArray.add(new Event("Assignment 3", "CSCI 5115", "", "", "11.5", "11:00PM", "", ""));
        eventsArray.add(new Event("Assignment 4", "CSCI 5115", "", "", "11.5", "11:00PM", "", ""));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.events_tab, container, false);
		
		/**
		  * set item into adapter
		  */
        eventAdapter = new EventsCustomAdapter(getActivity(), R.layout.events_tab_row, eventsArray);
        eventsList = (ListView) view.findViewById(R.id.event_list);
        eventsList.setItemsCanFocus(false);
        eventsList.setAdapter(eventAdapter);
		
		/**
		  * get on item click listener
		  */
        eventsList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
			Log.i("List View Clicked", "**********");
		    Toast.makeText(getActivity(), "List View Clicked:" + position, Toast.LENGTH_LONG).show();
		   }
		});
		
		return view;
	}	
}
