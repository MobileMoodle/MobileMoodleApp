package edu.umn.moodlemanaged.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.umn.moodlemanaged.MMNotification;
import edu.umn.moodlemanaged.R;

public class NotificationsCustomAdapter extends ArrayAdapter<MMNotification> {
    Context context;
    int layoutResourceId;
    ArrayList<MMNotification> notifications = new ArrayList<MMNotification>();

    public NotificationsCustomAdapter(Context context, int layoutResourceId, ArrayList<MMNotification> notifications) {
        super(context, layoutResourceId, notifications);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.notifications_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.not_list_item);
        String text = notifications.get(position).not;
        textView.setText(text);

        return rowView;
    }

    public MMNotification getItem(int i) {
        return notifications.get(i);
    }

    public MMNotification remove(int i) {
        return notifications.remove(i);
    }

    public void insert(int i, MMNotification n) {
        notifications.add(i, n);
    }

}