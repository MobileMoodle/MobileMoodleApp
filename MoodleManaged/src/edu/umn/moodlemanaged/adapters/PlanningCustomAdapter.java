package edu.umn.moodlemanaged.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.umn.moodlemanaged.Group2;
import edu.umn.moodlemanaged.R;

public class PlanningCustomAdapter extends BaseExpandableListAdapter {

    private final ArrayList<Group2> groups;
    public LayoutInflater inflater;
    public Activity activity;

    public PlanningCustomAdapter(Activity act, ArrayList<Group2> groups) {
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
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String ct = (String) groups.get(groupPosition).children.get(childPosition).text;
        TextView text = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.planning_tab_row, null);
        }
        text = (TextView) convertView.findViewById(R.id.planning_list_event);
        text.setText(ct);
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, ct, Toast.LENGTH_SHORT).show();
            }
        });

        final CheckBox cBox = (CheckBox) convertView.findViewById(R.id.plan_check_box);
        cBox.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v.findViewById(R.id.plan_check_box);
                if (cb.isChecked()) {
                    groups.get(groupPosition).children.get(childPosition).isChecked = true;
                } else {
                    groups.get(groupPosition).children.get(childPosition).isChecked = false;
                }
            }
        });
        cBox.setChecked(groups.get(groupPosition).children.get(childPosition).isChecked);

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
            convertView = inflater.inflate(R.layout.planning_tab_group, null);
        }
        Group2 group = (Group2) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
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