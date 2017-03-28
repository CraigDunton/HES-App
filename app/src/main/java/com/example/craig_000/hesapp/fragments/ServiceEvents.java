package com.example.craig_000.hesapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.craig_000.hesapp.Event;
import com.example.craig_000.hesapp.R;
import com.example.craig_000.hesapp.adapters.EventAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by craig_000 on 10/2/2016.
 */

public class ServiceEvents extends Fragment {

    private ListView list;
    private ArrayList<Event> events;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference serviceEventsRef;
    private EventAdapter mEventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.service_events,container, false);

        list = (ListView) v.findViewById(R.id.myList);
        events = new ArrayList<>();

        mFirebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        serviceEventsRef = database.getReference("cs_events");
        final DatabaseReference serviceSignUpEventsRef = database.getReference("cs_signed_up").child(mFirebaseAuth.getCurrentUser().getUid());

        serviceEventsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> serviceEvents = dataSnapshot.getChildren().iterator();
                while (serviceEvents.hasNext()){
                    DataSnapshot eventData = serviceEvents.next();
                    Event event = eventData.getValue(Event.class);
                    events.add(event);
                }
                mEventAdapter = new EventAdapter(getActivity(), events);
                list.setAdapter(mEventAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        serviceEventsRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                Map<String, Object> map = (Map<String, Object>)dataSnapshot.getValue();
//                events.add(new Event((String)map.get("date"), (String)map.get("time"), (String)map.get("title"),(String)map.get("description"),(String)map.get("location"), dataSnapshot.getKey()));
//                mEventAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //Popup window
                View desc = (View) v.findViewById(R.id.myList);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setTitle("Sign up for this event?");
                alertDialogBuilder.setMessage("This will add the event to your calendar as well");
                alertDialogBuilder.setPositiveButton("YES",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Map<String, String> userData = new HashMap<String, String>();
                        Event eventToSignUp = events.get(position);
                        DatabaseReference signUpRef = serviceSignUpEventsRef.child(eventToSignUp.getID());

                        userData.put("date", eventToSignUp.getDate());
                        userData.put("description", eventToSignUp.getDescription());
                        userData.put("location", eventToSignUp.getLocation());
                        userData.put("time", eventToSignUp.getTime());
                        userData.put("title", eventToSignUp.getTitle());

                        signUpRef.setValue(userData);

                        events.remove(position);
                        mEventAdapter.notifyDataSetChanged();
                    }
                });
                alertDialogBuilder.setNegativeButton("NO",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
                //on OK, add to calendar if not already
                //add to firebase if not already

            }
        });

        return v;
    }


}
