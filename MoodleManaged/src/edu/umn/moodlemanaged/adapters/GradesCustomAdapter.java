package edu.umn.moodlemanaged.adapters;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import edu.umn.moodlemanaged.Grade;
import edu.umn.moodlemanaged.GradesFragment;
import edu.umn.moodlemanaged.GradesGroup;
import edu.umn.moodlemanaged.MoodleManaged;
import edu.umn.moodlemanaged.R;

public class GradesCustomAdapter extends BaseExpandableListAdapter {

	private SparseArray<GradesGroup> groups;
	public LayoutInflater inflater;
	public Activity activity;

	public GradesCustomAdapter(Activity act, SparseArray<GradesGroup> groups) {
		activity = act;
		this.groups = groups;
		inflater = act.getLayoutInflater();
	}
    public void setGroup(SparseArray<GradesGroup> groups){
        this.groups = groups;
    }

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).children.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild,View convertView, ViewGroup parent) {
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
            Log.i("debug", "CHILD IS FINAL");
            tv2.setText(score+"%");
            tv2.setTextColor(Color.parseColor("#000000"));
        }else{
            if(child.getScore()<0) {
                Log.i("debug", "CHILD IS < 0");
                tv2.setText("- -%");
                tv2.setTextColor(Color.parseColor("#000000"));
            }
            else {
                if(child.setStudent)
                {
                    Log.i("debug", "CHILD IS SET BY STUDENT, value is: " + child.score);
                    tv2.setText(child.score+"%");
                    tv2.setTextColor(activity.getResources().getColor(R.color.holo_blue_light));
                }
                else
                {
                    Log.i("debug", "CHILD IS NOT SET BY STUDENT, value is: " + (child.getScore() / child.getPercentage() * 100)+"%");
                    tv2.setText(new DecimalFormat("#.#").format(child.getScore() / child.getPercentage() * 100)+"%");
                    tv2.setTextColor(activity.getResources().getColor(R.color.holo_blue_light));
                    //tv2.setTextColor(Color.parseColor(R.color.holo_blue_bright+""));
                    //Color c = new Color(R.color.holo_blue_bright);

                }
            }

        }


		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(child.isFinal())
                    Toast.makeText(activity, child.getCoursework()+" "+child.getScore()+"%", Toast.LENGTH_SHORT).show();
                else
                {
                    RelativeLayout linearLayout = new RelativeLayout(GradesFragment.thiscontext);
                    final NumberPicker aNumberPicker = new NumberPicker(GradesFragment.thiscontext);
                    aNumberPicker.setMaxValue(100);
                    aNumberPicker.setMinValue(0);

                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
                    RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

                    linearLayout.setLayoutParams(params);
                    linearLayout.addView(aNumberPicker,numPicerParams);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GradesFragment.thiscontext);
                    alertDialogBuilder.setTitle("Select Your Expected Grade");
                    alertDialogBuilder.setView(linearLayout);
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            if(GradesFragment.currentWanted != -1)
                                            {
                                                child.score = aNumberPicker.getValue();
                                                child.setSetStudent(true);
                                                GradesFragment.assignFakeGrades(GradesFragment.currentWanted);
                                                //temp.assignFakeGrades(temp.currentWanted);
                                                Log.e("", "New Quantity Value : " + aNumberPicker.getValue());
                                                Log.e("debug", "Result is: " + child.getScore());
                                            }
                                            else
                                            {
                                                Log.e("debug", "No value assigned, set is -1");
                                            }
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
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
		return groupPosition;
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