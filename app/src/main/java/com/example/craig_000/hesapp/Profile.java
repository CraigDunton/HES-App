package com.example.craig_000.hesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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

        final ArrayAdapter<?> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buttons);

        final ListView myListView = (ListView)findViewById(R.id.list);
        myListView.setAdapter(arrayAdapter);

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
    public void setPicture(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

}
