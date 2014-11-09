package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.PlanningCustomAdapter;

public class PlanningFragment extends Fragment {
    private ArrayList<EventGroupDate> groups = new ArrayList<EventGroupDate>();
    ArrayList<String> isCheckedStatus = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.planning_tab, container, false);

        // Populate upcoming events, and set their checkboxes to false (views are recycled)
        createData();
        for (int i = 0; i < groups.size(); i++) {
            isCheckedStatus.add("false");
        }

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
        EventGroupDate group = new EventGroupDate("Today, November 8th");
        group.children.add(new Event("CSCI 5115: Read Mathis Chapters 27-35", false));
        groups.add(j, group);
        j++;
        group = new EventGroupDate("Tomorrow, November 9th");
        group.children.add(new Event("PSY 3011: Read Chapter 10", false));
        group.children.add(new Event("CSCI 5115: Group Meeting (5:00PM)", false));
        groups.add(j, group);
        j++;
        group = new EventGroupDate("Monday, November 10th");
        group.children.add(new Event("KIN 5001: Job Task Analysis", false));
        groups.add(j, group);
        j++;
        group = new EventGroupDate("Tuesday, November 11th");
        group.children.add(new Event("CSCI 4131: Assignment 7 Due (11:55PM)", false));
        groups.add(j, group);
        j++;
        group = new EventGroupDate("Wednesday, November 12th");
        group.children.add(new Event("PSY 3011: Chapter 10 Writing Due (11:59PM)", false));
        group.children.add(new Event("PSY 3011: Chapter 10 Quizzes", false));
        groups.add(j, group);
        j++;
        group = new EventGroupDate("Thursday, November 13th");
        group.children.add(new Event("KIN 5001: Job Task Analysis", false));
        groups.add(j, group);
        j++;
        group = new EventGroupDate("Friday, November 14th");
        group.children.add(new Event("CSCI 5115: Development Complete (1:30PM)", false));
        group.children.add(new Event("CSCI 5115: Finalize Testing Plan (1:30PM)", false));
        group.children.add(new Event("CSCI 5115: Group Meeting (4:00PM)", false));
        groups.add(j, group);
        j++;
        group = new EventGroupDate("Saturday, November 15th");
        group.children.add(new Event("CSCI 5115: User Testing", false));
        groups.add(j, group);
        j++;
        group = new EventGroupDate("View More +");
        groups.add(j, group);
        j++;
    }

}
