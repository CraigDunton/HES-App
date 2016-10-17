package com.example.craig_000.hesapp;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.Manifest;

/**
 * Created by craig_000 on 10/2/2016.
 */

public class HESEvents extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hesevents);

        ListView list = (ListView)findViewById(R.id.myList);

//        HttpTransport transport = AndroidHttp.newCompatibleTransport();
//        JsonFactory factory = JacksonFactory.getDefaultInstance();
//        GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
//                getApplicationContext(), Arrays.asList(CalendarScopes.CALENDAR_READONLY))
//                .setBackOff(new ExponentialBackOff());
//
//
//        Calendar service = new Calendar.Builder(transport,factory,credential)
//                .setApplicationName("HES App")
//                .build();
//
//
//        try {
//            com.google.api.services.calendar.model.Calendar cal = service.calendars().get("ereed1272@gmail.com").execute();
//            Log.i("Calendar Info", cal.getDescription());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
