package com.example.craig_000.hesapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.PopupWindowCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.craig_000.hesapp.Event;
import com.example.craig_000.hesapp.adapters.EventAdapter;
import com.example.craig_000.hesapp.R;
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

public class ServiceEvents extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.service_events,container, false);

        ListView list = (ListView) v.findViewById(R.id.list);
        final ArrayList<Event> events = new ArrayList<>();
        final EventAdapter adapter = new EventAdapter(getActivity(), events);
        list.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference hesEventsRef = database.getReference("cs_events");

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


        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Popup window
                TextView mText = new TextView(v.getContext());
                mText.setText("Testing");
                PopupWindow mPop = new PopupWindow(mText, 100,100);
                mPop.showAtLocation(v, Gravity.CENTER,0,0);
                mPop.update(50, 50, 320, 90);
                //on OK, add to calendar if not already

                //add to firebase if not already

                events.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

}