package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import edu.umn.moodlemanaged.adapters.GradesCustomAdapter;

public class GradesFragment extends Fragment {
	private SparseArray<Group> groups = new SparseArray<Group>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.grades_tab, container, false);

        // Populate grades data
		createData();
		
		ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.grades_list);
		GradesCustomAdapter adapter = new GradesCustomAdapter(getActivity(), groups);
		listView.setAdapter(adapter);
		
		return view;
	}

    // TODO Integrate with cloud
    /**
     * Add Grades (Mock-up ONLY)
     * TODO Add tabs for other courses
     */
	public void createData() {
        Group group = new Group("Exams (40%)");
        group.children.add("Midterm (15%)");
        group.children.add("Final (25%)");
        groups.append(0, group);
        group = new Group("Assignments (60%)");
        group.children.add("Assignment 1 (5%)");
        group.children.add("Assignment 2 (5%)");
        group.children.add("Assignment 3 (10%)");
        group.children.add("Assignment 4 (5%)");
        group.children.add("Assignment 5 (5%)");
        group.children.add("Assignment 6 (5%)");
        group.children.add("Assignment 7 (10%)");
        group.children.add("Assignment 8 (5%)");
        groups.append(1, group);

	}

}
