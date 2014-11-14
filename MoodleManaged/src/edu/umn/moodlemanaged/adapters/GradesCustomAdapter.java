package edu.umn.moodlemanaged.adapters;

import android.app.Activity;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import edu.umn.moodlemanaged.Grade;
import edu.umn.moodlemanaged.GradesGroup;
import edu.umn.moodlemanaged.R;

public class GradesCustomAdapter extends BaseExpandableListAdapter {

	private final SparseArray<GradesGroup> groups;
	public LayoutInflater inflater;
	public Activity activity;

	public GradesCustomAdapter(Activity act, SparseArray<GradesGroup> groups) {
		activity = act;
		this.groups = groups;
		inflater = act.getLayoutInflater();
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Grade child = (Grade) getChild(groupPosition, childPosition);


        if (convertView == null) {
            //Log.i("b :child: ",child.getCoursework());
            convertView = inflater.inflate(R.layout.grades_tab_row, null);
        }
        TextView tv1 = (TextView) convertView.findViewById(R.id.grades_list_item);
        tv1.setText(child.getCoursework()+"("+child.getPercentage()+"%)");
        TextView tv2 = (TextView) convertView.findViewById(R.id.grades_list_percent);
        if(child.isFinal()){
            double score = child.getScore();

            tv2.setText(score+"%");
        }else{
            if(child.getScore()<0) {
                tv2.setText("- -%");
            }
            else {
                tv2.setText(new DecimalFormat("#.#").format(child.getScore() / child.getPercentage() * 100)+"%");
            }

        }




		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(child.isFinal())
                    Toast.makeText(activity, child.getCoursework()+" "+child.getScore()+"%", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(activity,"HI",Toast.LENGTH_LONG).show();
				}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).children.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.grades_tab_group, null);
		}
		GradesGroup gradesGroup = (GradesGroup) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(gradesGroup.name+" ("+gradesGroup.getPercentage()+"% )");
		((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}