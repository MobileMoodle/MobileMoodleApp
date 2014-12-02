package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.umn.moodlemanaged.adapters.PlanningCustomAdapter;

public class PlanningFragment extends Fragment {
    public static ArrayList<PlanningGroupDate> groups = new ArrayList<PlanningGroupDate>();
    public static ExpandableListView listView;
    public static Switch sortSwitch;
    public static PlanningCustomAdapter adapter;
    ArrayList<String> isCheckedStatus = new ArrayList<String>();
    ArrayAdapter<String> coursesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.planning_tab, container, false);
        // Populate upcoming events, and set their checkboxes to false (views are recycled)
        groups.clear();
        sortDataDate();
        for (int i = 0; i < groups.size(); i++) {
            isCheckedStatus.add("false");
        }

        // List
        listView = (ExpandableListView) view.findViewById(R.id.planning_list);
        adapter = new PlanningCustomAdapter(getActivity(), groups);
        listView.setAdapter(adapter);
        int count = adapter.getGroupCount();
        Log.i("debug", "Count is: " + count);
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

        // TODO add adapter for switch;
        sortSwitch = (Switch) view.findViewById(R.id.sw_due_v_course);
        sortSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("debug", "isChecked: " + sortSwitch.isChecked());
                if(sortSwitch.isChecked())
                {
                    groups.clear();
                    //listView.invalidate();
                    sortDataName();
                    Log.i("debug", "Groups size is: " + groups.size());
                    int count = adapter.getGroupCount();
                    Log.i("debug", "Count is: " + count);
                    for (int position = 0; position < count; position++) {
                        Log.i("debug", "Position is: " + position);
                        listView.expandGroup(position);
                    }
                    //adapter.notifyDataSetInvalidated();
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    groups.clear();
                    //listView.invalidate();
                    sortDataDate();
                    Log.i("debug", "Groups size is: " + groups.size());
                    int count = adapter.getGroupCount();
                    Log.i("debug", "Count is: " + count);
                    for (int position = 0; position < count; position++) {
                        Log.i("debug", "Position is: " + position);
                        listView.expandGroup(position);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }

//    @Override
//    public void onResume(){
//        if(sortSwitch.isChecked())
//        {
//            groups.clear();
//            sortDataName();
//            int count = adapter.getGroupCount();
//            for (int position = 0; position < count; position++) {
//                listView.expandGroup(position);
//            }
//            //adapter.notifyDataSetInvalidated();
//            adapter.notifyDataSetChanged();
//        }
//        else
//        {
//            groups.clear();
//            sortDataDate();
//            int count = adapter.getGroupCount();
//            for (int position = 0; position < count; position++) {
//                listView.expandGroup(position);
//            }
//            //adapter.notifyDataSetInvalidated();
//            adapter.notifyDataSetChanged();
//        }
//        super.onResume();
//    }

    // TODO Integrate with cloud
    /**
     * Add Events (Mock-up ONLY)
     * TODO Add other views
     */
    public void sortDataDate() {
        Log.i("debug", "SORT DATA BY DATE CALLED");
        DBHelper mydb = MoodleManaged.mydb;
        ArrayList<Event> list = mydb.getEvents();
        Date tmp=list.get(0).time;
        PlanningGroupDate group = new PlanningGroupDate(""+tmp);

        int j;
        for (j=0;j<list.size();j++)
        {
            if(list.get(j).time.getDay()==tmp.getDay())
            {
                group.children.add(list.get(j));
            }
            else
            {
               groups.add(group);
               tmp = list.get(j).time;
               group = new PlanningGroupDate(""+tmp);
               group.children.add(list.get(j));
            }
        }
        groups.add(group);
    }

    public void sortDataName() {
        Log.i("debug", "SORT DATA BY NAME CALLED");
        DBHelper mydb = MoodleManaged.mydb;
        ArrayList<Event> list = mydb.getEventsSortName();
        String tmp=list.get(0).courseName;
        PlanningGroupDate group = new PlanningGroupDate(""+tmp);

        int j;
        for (j=0;j<list.size();j++)
        {
            Log.i("debug", "Tmp is: \"" + tmp + "\". list(j).courseName is: \"" + list.get(j).courseName + "\"");
            if(list.get(j).courseName.compareToIgnoreCase(tmp) == 0)
            {
                Log.i("debug", "Names were the same!");
                group.children.add(list.get(j));
            }
            else
            {
                groups.add(group);
                tmp = list.get(j).courseName;
                group = new PlanningGroupDate(""+tmp);
                group.children.add(list.get(j));
            }
        }
        groups.add(group);
    }


}
