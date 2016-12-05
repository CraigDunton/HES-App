package com.example.craig_000.hesapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by craig_000 on 10/2/2016.
 */

public class ServiceEvents extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_events);

        Event one = new Event("10/22/2016","2:00pm","Clean up trash");
        Event two = new Event("10/24/2016","1:30pm","Touch Ricky");
        Event three = new Event("10/30/2016","9:00am","Turn up LMAO");

        final ArrayList<Event> events = new ArrayList<>();
        events.add(one);
        events.add(two);
        events.add(three);

        final EventAdapter adapter = new EventAdapter(this, events);


        ListView list = (ListView)findViewById(R.id.myList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                events.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

}