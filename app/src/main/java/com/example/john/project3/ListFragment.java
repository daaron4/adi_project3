package com.example.john.project3;

/**
 * Created by John on 5/31/16.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.john.project3.R.layout;

public class ListFragment extends Fragment{
    private View myFragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(layout.fragment_list, container, false);

        LocalDBHelper handler = new LocalDBHelper(getContext());
        Cursor todoCursor = handler.getAll();
        ListView lvItems = (ListView) myFragmentView.findViewById(R.id.main_list_tab_two);
        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(getActivity(),todoCursor);
        lvItems.setAdapter(todoAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View localView, int position, long id) {
                Intent detailsIntent = new Intent(getContext(), StudentDetailsActivity.class);

//                TODO: commented-out lines below are used for animated transitions
//                Cursor selectedCursor = (Cursor) parent.getAdapter().getItem(position);
//                View view = localView.findViewById(R.id.card_view);
//                ActivityOptionsCompat bugOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, view, "moving_bug");

//                 TODO: this line below should help capture the ID associated with the database row, maybe?
//                detailsIntent.putExtra("id", selectedCursor.getInt(selectedCursor.getColumnIndex(.....COL_ID)));

                startActivity(detailsIntent);
            }
        });
        return myFragmentView;
    }
    public class TodoCursorAdapter extends CursorAdapter {
        public TodoCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(layout.list_frag_format, parent, false);
        }
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tvBody = (TextView)view.findViewById(R.id.main_list_name);
            ImageView imageView = (ImageView)view.findViewById(R.id.main_list_image);
            String body = cursor.getString(cursor.getColumnIndexOrThrow("name"));

            tvBody.setText(body);
            // TODO Set the 'url' value from database
            Picasso.with(context).load("http://i.imgur.com/IWGvno3.jpg").into(imageView);

        }
    }


}

