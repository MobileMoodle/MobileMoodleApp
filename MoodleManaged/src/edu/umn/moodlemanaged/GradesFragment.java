package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import edu.umn.moodlemanaged.adapters.GradesCustomAdapter;

//TODO Mockup something we would use
public class GradesFragment extends Fragment {
	private SparseArray<Group> groups = new SparseArray<Group>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.grades_tab, container, false);

		createData();
		
		ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.grades_list);
		GradesCustomAdapter adapter = new GradesCustomAdapter(getActivity(), groups);
		listView.setAdapter(adapter);
		
		return view;
	}

	public void createData() {
		for (int j = 0; j < 2; j++) {
			Group group = new Group("Category " + (j+1) + " 50%");
			for (int i = 0; i < 5; i++) {
				group.children.add("Assignment " + (i+1) + " 10%");
			}
			groups.append(j, group);
		}
	}

}
