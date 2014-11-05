package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;

import java.sql.SQLOutput;

import edu.umn.moodlemanaged.adapters.PlanningCustomAdapter;

public class PlanningFragment extends Fragment {
    private SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.planning_tab, container, false);

        createData();

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
        Group group = new Group("Today, November 5th");
        for (int i = 0; i < 5; i++) {
            group.children.add("CSCI 5115 Assignmnet " + i + " (11:00PM)");
        }
        groups.append(j, group);
        j++;
        group = new Group("Tomorrow, November 6th");
        for (int i = 0; i < 5; i++) {
            group.children.add("CSCI 4131 Assignmnet " + i + " (11:00PM)");
        }
        groups.append(j, group);
        j++;
        group = new Group("Friday, November 7th");
        for (int i = 0; i < 5; i++) {
            group.children.add("CSCI 8011 Assignmnet " + i + " (11:00PM)");
        }
        groups.append(j, group);
        j++;

    }

}
