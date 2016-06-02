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
import android.widget.Toast;
import java.util.Arrays;

import static com.example.john.project3.R.id;
import static com.example.john.project3.R.layout;

public class ListFragment extends Fragment implements ApiConnector.ApiResponseHandler {
    private View myFragmentView;
    private TextView myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ApiConnector.getInstance(this).doRequest();
        myFragmentView = inflater.inflate(layout.fragment_list, container, false);
        myView = (TextView) myFragmentView.findViewById(id.test);

        return myFragmentView;
    }
//

    @Override
    public void handleResponseName(String[] idArray,String[] nameArray, String[] titleArray, String[] skillsArray, String[] openArray,
    String[] gitHubArray, String[] gaArray, String[] linkedInArray, String[] otherArray, String[] imageArray, String[] urlArray) {

        Toast.makeText(getContext(), nameArray + "fragment", Toast.LENGTH_SHORT).show();

        String x = Arrays.toString(nameArray);
        myView.setText(x);
    }
}
