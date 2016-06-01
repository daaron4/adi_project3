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

public class ListFragment extends Fragment implements ApiConnector.ApiResponseHandler {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_frag_format, container, false);

    }

    @Override
    public void handleResponseName(String address) {

    }
}
