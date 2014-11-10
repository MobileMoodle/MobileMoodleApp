package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.PlanningCustomAdapter;

public class PlanningFragment extends Fragment {
    private ArrayList<PlanningGroupDate> groups = new ArrayList<PlanningGroupDate>();
    ArrayList<String> isCheckedStatus = new ArrayList<String>();
    ArrayAdapter<String> coursesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.planning_tab, container, false);

        // Populate upcoming events, and set their checkboxes to false (views are recycled)
        createData();
        for (int i = 0; i < groups.size(); i++) {
            isCheckedStatus.add("false");
        }

        // TODO add adapter for switch

        //TODO add adapter functionality for this spinner
        // Spinner
        Spinner courses = (Spinner) view.findViewById(R.id.course_spinner_p);
        coursesAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, new ArrayList<String>());
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesAdapter.add("CSCI 4131");
        coursesAdapter.add("CSCI 5115");
        coursesAdapter.add("KIN 5001");
        coursesAdapter.add("PSY 3011");
        courses.setAdapter(coursesAdapter);

        // List
        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.planning_list);
        final PlanningCustomAdapter adapter = new PlanningCustomAdapter(getActivity(), groups);
        listView.setAdapter(adapter);
        int count = adapter.getGroupCount();
        // Expand all groups, and throw away click events (i.e. cannot collapse)
        for (int position = 0; position < count; position++) {
            listView.expandGroup(position);
        }
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true; // This way the expander cannot be collapsed
            }
        });

        return view;
    }

    // TODO Integrate with cloud
    /**
     * Add Events (Mock-up ONLY)
     * TODO Add other views
     */
    public void createData() {

        int j=0;
        PlanningGroupDate group = new PlanningGroupDate("Today, November 8th");
        group.children.add(new Event("CSCI 5115: Read Mathis Chapters 27-35", false));
        groups.add(j, group);
        j++;
        group = new PlanningGroupDate("Tomorrow, November 9th");
        group.children.add(new Event("PSY 3011: Read Chapter 10", false));
        group.children.add(new Event("CSCI 5115: GradesGroup Meeting (5:00pm)", false));
        groups.add(j, group);
        j++;
        group = new PlanningGroupDate("Monday, November 10th");
        group.children.add(new Event("KIN 5001: Job Task Analysis", false));
        groups.add(j, group);
        j++;
        group = new PlanningGroupDate("Tuesday, November 11th");
        group.children.add(new Event("CSCI 4131: Assignment 7 Due (11:55pm)", false));
        groups.add(j, group);
        j++;
        group = new PlanningGroupDate("Wednesday, November 12th");
        group.children.add(new Event("PSY 3011: Chapter 10 Writing Due (11:59pm)", false));
        group.children.add(new Event("PSY 3011: Chapter 10 Quizzes", false));
        groups.add(j, group);
        j++;
        group = new PlanningGroupDate("Thursday, November 13th");
        group.children.add(new Event("KIN 5001: Job Task Analysis", false));
        groups.add(j, group);
        j++;
        group = new PlanningGroupDate("Friday, November 14th");
        group.children.add(new Event("CSCI 5115: Development Complete (1:30pm)", false));
        group.children.add(new Event("CSCI 5115: Finalize Testing Plan (1:30pm)", false));
        group.children.add(new Event("CSCI 5115: GradesGroup Meeting (4:00pm)", false));
        groups.add(j, group);
        j++;
        group = new PlanningGroupDate("Saturday, November 15th");
        group.children.add(new Event("CSCI 5115: User Testing", false));
        groups.add(j, group);
        j++;
        group = new PlanningGroupDate("View More +");
        groups.add(j, group);
        j++;
    }

}
