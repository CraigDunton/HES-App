package com.example.craig_000.hesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.craig_000.hesapp.adapters.EventAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by ariana
 */

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        final ArrayList<String> buttons = new ArrayList<>();
        buttons.add(0, "History");
        buttons.add(1, "Future Events");

        ListView list = (ListView) findViewById(R.id.signedUp);
        final ArrayList<Event> signedUpEvents = new ArrayList<>();

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference HESSignUpEventsRef = database.getReference("hes_signed_up").child(mFirebaseAuth.getCurrentUser().getUid());
        final DatabaseReference CSSignUpEventsRef = database.getReference("cs_signed_up").child(mFirebaseAuth.getCurrentUser().getUid());

        HESSignUpEventsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>)dataSnapshot.getValue();
                signedUpEvents.add(new Event((String)map.get("date"), (String)map.get("time"), (String)map.get("title"),(String)map.get("description"),(String)map.get("location"), dataSnapshot.getKey()));
                Log.i("HESEventAdded", signedUpEvents.toString());
                Collections.sort(signedUpEvents, new EventComparator());
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

        CSSignUpEventsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>)dataSnapshot.getValue();
                signedUpEvents.add(new Event((String)map.get("date"), (String)map.get("time"), (String)map.get("title"),(String)map.get("description"),(String)map.get("location"), dataSnapshot.getKey()));
                Log.i("CSEventAdded", signedUpEvents.toString());
                Collections.sort(signedUpEvents, new EventComparator());
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

        //adapters and stuff for list of buttons (future and past events)
        final ArrayAdapter<?> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buttons);
        final ListView myListView = (ListView)findViewById(R.id.list);
        myListView.setAdapter(arrayAdapter);

        //adapters and stuff for sign up event list
        final EventAdapter signUpArrayAdapter = new EventAdapter(this, signedUpEvents);
        final ListView signedUpListView = (ListView)findViewById(R.id.signedUp);
        signedUpListView.setAdapter(signUpArrayAdapter);

        //CREATE A COMPARATOR TO SORT BY DATE
        Collections.sort(signedUpEvents, new EventComparator());
        signUpArrayAdapter.notifyDataSetChanged();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    historyButtonClick(myListView);
                }
                //Log.i("Button Tapped: ", buttons.get(i));
            }
        });

    }


    public void historyButtonClick(View view){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
