package edu.umn.moodlemanaged.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.umn.moodlemanaged.Event;
import edu.umn.moodlemanaged.R.id;

public class EventsCustomAdapter extends ArrayAdapter<Event> {
    Context context;
    int layoutResourceId;
    ArrayList<Event> events = new ArrayList<Event>();

    public EventsCustomAdapter(Context context, int layoutResourceId, ArrayList<Event> events) {
        super(context, layoutResourceId, events);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EventHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new EventHolder();
            holder.eventName = (TextView) row.findViewById(id.event_list_event_name);
            row.setTag(holder);
        } else {
            holder = (EventHolder) row.getTag();
        }

        Event event = events.get(position);
        holder.eventName.setText(event.getID() + " " + event.getTitle() + " (" + event.getDueTime() + ")");

        return row;

    }

    static class EventHolder {
        TextView eventName;
    }
}
