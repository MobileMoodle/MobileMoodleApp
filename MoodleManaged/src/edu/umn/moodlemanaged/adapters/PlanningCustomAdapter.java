package edu.umn.moodlemanaged.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.umn.moodlemanaged.NewAssignmentActivity;
import edu.umn.moodlemanaged.PlanningGroupDate;
import edu.umn.moodlemanaged.R;
import edu.umn.moodlemanaged.ViewAssignmentActivity;

public class PlanningCustomAdapter extends BaseExpandableListAdapter {

    private final ArrayList<PlanningGroupDate> groups;
    public LayoutInflater inflater;
    public Activity activity;

    public PlanningCustomAdapter(Activity activity, ArrayList<PlanningGroupDate> groups) {
        this.activity = activity;
        this.groups = groups;
        this.inflater = activity.getLayoutInflater();
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
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.planning_tab_row, null);
        }

        // Event string
        final String ct = (String) groups.get(groupPosition).children.get(childPosition).text;
        final TextView text = (TextView) convertView.findViewById(R.id.planning_list_event);
        text.setText(ct);
        // Activity if selected
        text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ViewAssignmentActivity.class);
                activity.startActivity(intent);
            }
        });

        // Store checkbox state as views are recycled
        final CheckBox cBox = (CheckBox) convertView.findViewById(R.id.plan_check_box);
        cBox.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v.findViewById(R.id.plan_check_box);
                if (cb.isChecked()) {
                    text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    text.setTextColor(activity.getApplicationContext().
                            getResources().getColor(R.color.darker_gray));
                    groups.get(groupPosition).children.get(childPosition).isChecked = true;
                } else {
                    groups.get(groupPosition).children.get(childPosition).isChecked = false;
                    text.setPaintFlags(text.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                    text.setTextColor(activity.getApplicationContext().
                            getResources().getColor(R.color.black));
                }
            }
        });
        cBox.setChecked(groups.get(groupPosition).children.get(childPosition).isChecked);
        cBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        text.setTextColor(activity.getApplicationContext().
                                getResources().getColor(R.color.darker_gray));
                    } else {
                        text.setPaintFlags(text.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                        text.setTextColor(activity.getApplicationContext().
                                getResources().getColor(R.color.black));
                    }
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
            convertView = inflater.inflate(R.layout.planning_tab_group, null);
        }
        PlanningGroupDate group = (PlanningGroupDate) getGroup(groupPosition);
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