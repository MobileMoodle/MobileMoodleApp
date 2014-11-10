package edu.umn.moodlemanaged;

import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.TextView;

import edu.umn.moodlemanaged.adapters.GradesCustomAdapter;

public class GradesFragment extends Fragment {
	private SparseArray<Group> groups = new SparseArray<Group>();
    private String desiredGrade = "";
    private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = inflater.inflate(R.layout.grades_tab, container, false);

        // Populate grades data
		createData();

        TextView textView = (TextView) view.findViewById(R.id.tv_grade_i_want);
        textView.setText("Grade I want: " + desiredGrade);

        SeekBar seekBar = (SeekBar) view.findViewById(R.id.sb_grade_i_want);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch (i) {
                    case 0: desiredGrade = "";
                        break;
                    case 1: desiredGrade = "F";
                        break;
                    case 2: desiredGrade = "D-";
                        break;
                    case 3: desiredGrade = "D";
                        break;
                    case 4: desiredGrade = "D+";
                        break;
                    case 5: desiredGrade = "C-";
                        break;
                    case 6: desiredGrade = "C";
                        break;
                    case 7: desiredGrade = "C+";
                        break;
                    case 8: desiredGrade = "B-";
                        break;
                    case 9: desiredGrade = "B";
                        break;
                    case 10: desiredGrade = "B+";
                        break;
                    case 11: desiredGrade = "A-";
                        break;
                    case 12: desiredGrade = "A";
                        break;
                    default: desiredGrade = "";
                        break;
                }

                TextView tv = (TextView) view.findViewById(R.id.tv_grade_i_want);
                tv.setText("Grade I want: " + desiredGrade);

                // Change percents
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
