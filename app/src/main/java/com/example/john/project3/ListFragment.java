package com.example.john.project3;

/**
 * Created by John on 5/31/16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.john.project3.R.id;
import static com.example.john.project3.R.layout;

public class ListFragment extends Fragment{
    private View myFragmentView;
    private TextView myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(layout.fragment_list, container, false);
        myView = (TextView) myFragmentView.findViewById(id.test);
//        myView.setText(Storage.nameArrayList.toString());
        return myFragmentView;
    }
}
