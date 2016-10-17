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

        ListView list = (ListView)findViewById(R.id.myList);

        final ArrayList<String> events = new ArrayList<>();
        events.add("Example 1");
        events.add("Example 2");
        events.add("Example 3");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, events);
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