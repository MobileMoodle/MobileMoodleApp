package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import edu.umn.moodlemanaged.adapters.PlanningCustomAdapter;

public class PlanningFragment extends Fragment {
    private ArrayList<Group2> groups = new ArrayList<Group2>();
    ArrayList<String> isCheckedStatus = new ArrayList<String>();
    CheckBox check_document;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.planning_tab, container, false);

        createData();
        for (int i = 0; i < groups.size(); i++) {
            isCheckedStatus.add("false");
        }

        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.planning_list);
        final PlanningCustomAdapter adapter = new PlanningCustomAdapter(getActivity(), groups);
        listView.setAdapter(adapter);
        int count = adapter.getGroupCount();
        for (int position = 0; position < count; position++)
            listView.expandGroup(position);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true; // This way the expander cannot be collapsed
            }
        });

        return view;
    }

    public void createData() {

        int j=0;
        Group2 group = new Group2("Today, November 5th");
        for (int i = 0; i < 5; i++) {
            group.children.add(new Eve("CSCI 8011 Assignmnet " + i + " (11:00PM)", false));
        }
        groups.add(j, group);
        j++;
        group = new Group2("Tomorrow, November 6th");
        for (int i = 0; i < 5; i++) {
            group.children.add(new Eve("CSCI 8011 Assignmnet " + i + " (11:00PM)", false));
        }
        groups.add(j, group);
        j++;
        group = new Group2("Friday, November 7th");
        for (int i = 0; i < 5; i++) {
            group.children.add(new Eve("CSCI 8011 Assignmnet " + i + " (11:00PM)", false));
        }
        groups.add(j, group);
        j++;

    }

}
