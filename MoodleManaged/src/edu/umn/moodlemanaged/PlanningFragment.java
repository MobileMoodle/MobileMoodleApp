package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

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

        // TODO add adapter for switch;

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

        Button newAssignment = (Button) view.findViewById(R.id.add_event_p);
        newAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newAssignment = new Intent(getActivity(), NewAssignmentActivity.class);
                getActivity().startActivity(newAssignment);
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
        DBHelper mydb = MoodleManaged.mydb;
        ArrayList<Event> list = mydb.getEvents();

        Date tmp=list.get(0).time;
        PlanningGroupDate group = new PlanningGroupDate(""+tmp);

        int j=0,i=0;
        for (j=0,i=0;j<list.size();j++){
            if(list.get(j).time.getDay()==tmp.getDay()){
                group.children.add(list.get(j));
            }else{
               groups.add(i,group);
               i++;
               tmp = list.get(j).time;
               group = new PlanningGroupDate(""+tmp);
               group.children.add(list.get(j));
            }
        }
        groups.add(i,group);

    }


}
