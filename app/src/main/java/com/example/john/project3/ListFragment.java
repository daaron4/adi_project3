package com.example.john.project3;

/**
 * Created by John on 5/31/16.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
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

public class ListFragment extends Fragment implements ApiConnector.ApiResponseHandler {
    private View myFragmentView;
    // ToDo: wtf is this counter?
    static int counter;
    private SharedPreferences sharedPreferences;
    private Cursor cursor;
    private CursorAdapter cursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ToDo: wtf is this package?
        sharedPreferences = getActivity().getSharedPreferences("ly.generalassemb.drewmahrt.tictactoe;", Context.MODE_PRIVATE);
        ApiConnector.getInstance(ListFragment.this).doRequest();
        counter = sharedPreferences.getInt("counter", 1);
        myFragmentView = inflater.inflate(layout.fragment_list, container, false);
        return myFragmentView;

    }

    @Override
    public void handleResponse(PersonModel[] data) {
        // ToDo: wtf is this counter?
        if (counter == 1) {
            LocalDBHelper.getInstance(getActivity()).seedData(data);
            counter++;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("counter", counter);
            editor.apply();
        }

        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        // Get access to the underlying writable database
        // SQLiteDatabase db = handler.getWritableDatabase();
        // Query for items from the database and get a cursor back
        cursor = LocalDBHelper.getInstance(getActivity()).getAll();
        //final Cursor ratingCursor = helper.getRating();

        //db.rawQuery("SELECT  * FROM RATINGS", null);

        // Find ListView to populate
        ListView lvItems = (ListView) myFragmentView.findViewById(R.id.main_list_tab_two);
        if (cursorAdapter == null) {
            cursorAdapter = new CursorAdapter(getActivity(), cursor, 0) {
                @Override
                public View newView(Context context, Cursor cursor, ViewGroup parent) {
                    // ToDo: wtf is this counter?
                    counter++;
                    if (counter % 2 == 0) {
                        return LayoutInflater.from(context).inflate(layout.list_frag_format, parent, false);
                    } else {
                        return LayoutInflater.from(context).inflate(layout.list_frag_format_right, parent, false);
                    }
                }

                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView tvBody = (TextView) view.findViewById(R.id.main_list_name);
                    ImageView imageView = (ImageView)view.findViewById(R.id.main_list_image);
                    String body = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String imageUrl = cursor.getString(cursor.getColumnIndexOrThrow("image"));
                    tvBody.setText(body + "\n" + "____________");
                    Picasso.with(context).load(imageUrl).into(imageView);
                    TextView summaryView = (TextView) view.findViewById(R.id.ratings_placeholder);
                    String summary = cursor.getString(cursor.getColumnIndexOrThrow("open"));
                    summaryView.setText("I am an Android developer looking to work with a team to create high quality applications. Working on Android applications is what I do 24/7, and I can't wait to do this as a profession and not a hobby. I have experience in QA, and I understand what it is like to get through a sprint when the walls are closing in.");
                }
            };
            lvItems.setAdapter(cursorAdapter);
        } else {
            cursorAdapter.changeCursor(cursor);
        }

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View localView, int position, long id) {
                Intent detailsIntent = new Intent(getContext(), StudentDetailsActivity.class);

                // ToDo: this is never used?
                Cursor selectedCursor = (Cursor) parent.getAdapter().getItem(position);
                View profilePic = localView.findViewById(R.id.main_list_image);
                ActivityOptionsCompat profilePicTransition = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), profilePic, "moving_bug");

                // TODO: this line below should help capture the ID associated with the database row, maybe?
                cursor.moveToPosition(position);
               // ratingCursor.moveToPosition(position);
                detailsIntent.putExtra("id", cursor.getInt(cursor.getColumnIndex(LocalDBHelper.COL_ID)));
               // detailsIntent.putExtra("id", ratingCursor.getInt(ratingCursor.getColumnIndex(helper.COL_ID)));

                startActivity(detailsIntent, profilePicTransition.toBundle());
            }
        });
    }

}

