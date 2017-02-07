package com.example.craig_000.hesapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.craig_000.hesapp.adapters.EventAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by craig_000 on 10/2/2016.
 */

public class HESEvents extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hesevents);


        final ArrayList<Event> events = new ArrayList<>();
        

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference hesEventsRef = database.getReference("hes_events");

        hesEventsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>)dataSnapshot.getValue();
                events.add(new Event((String)map.get("date"), (String)map.get("time"), (String)map.get("title")));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
