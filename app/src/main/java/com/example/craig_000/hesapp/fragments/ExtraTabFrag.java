package com.example.craig_000.hesapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.craig_000.hesapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraTabFrag extends Fragment {


    public ExtraTabFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.extra_tab,container, false);
        return v;
    }

}
