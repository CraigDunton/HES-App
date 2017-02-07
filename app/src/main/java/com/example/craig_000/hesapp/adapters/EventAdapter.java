package com.example.craig_000.hesapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.craig_000.hesapp.Event;
import com.example.craig_000.hesapp.R;

import java.util.ArrayList;

/**
 * Created by craig_000 on 10/22/2016.
 */

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Activity context, ArrayList<Event> events){

        super(context,0,events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
        }
        Event currentEvent = getItem(position);

        //find title textview from the custom layout
        TextView title = (TextView) listItemView.findViewById(R.id.Title);
        //set its text to the title of the current event
        title.setText(currentEvent.getTitle());

        //find time textview from the custom layout
        TextView time = (TextView) listItemView.findViewById(R.id.Time);
        //set its text to the current event's time
        time.setText(currentEvent.getTime());

        //find date textview from custom layout
        TextView date = (TextView) listItemView.findViewById(R.id.Date);
        //set its text to the current event's date
        date.setText(currentEvent.getDate());

    return listItemView;
    }
}
