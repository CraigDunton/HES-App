package com.example.craig_000.hesapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by ariana
 */

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        final ArrayList<String> pastEvents = new ArrayList<>();
        pastEvents.add(0, "History");
        pastEvents.add(1, "Future Events");

        final ArrayAdapter<?> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pastEvents);

        ListView myListView = (ListView)findViewById(R.id.list_element);
        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Button Tapped: ", pastEvents.get(i));
            }
        });

    }
}
