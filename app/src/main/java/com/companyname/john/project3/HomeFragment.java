package com.companyname.john.project3;

/**
 * Created by John on 5/31/16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class HomeFragment  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView gaImage= (ImageView)view.findViewById(R.id.ga_image);
        Picasso.with(getContext()).load(R.drawable.thumb_adi).into(gaImage);
        return view;

    }
}
