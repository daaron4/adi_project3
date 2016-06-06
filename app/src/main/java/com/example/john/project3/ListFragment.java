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

public class ListFragment extends Fragment implements ApiConnector.ApiResponseHandler {
    private View myFragmentView;
    static int counter = 1;
    LocalDBHelper helper;
    TodoCursorAdapter todoAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ApiConnector.getInstance(ListFragment.this).doRequest();

        helper = LocalDBHelper.getInstance(getActivity());
        myFragmentView = inflater.inflate(layout.fragment_list, container, false);
        return myFragmentView;

    }

    @Override
    public void handleResponseName(String[] idArray, String[] nameArray, String[] titleArray, String[] skillsArray, String[] openArray, String[] gitHubArray, String[] gaArray, String[] linkedInArray, String[] otherArray, String[] imageArray, String[] urlArray, String[] emailArray, String[] phoneArray) {


        helper.getReadableDatabase();
        if (counter == 1) {
            helper.seedData(idArray,
                    nameArray,
                    titleArray,
                    skillsArray,
                    openArray,
                    gitHubArray,
                    gaArray,
                    linkedInArray,
                    otherArray,
                    imageArray,
                    urlArray,
                    emailArray,
                    phoneArray
                    );
            counter++;
        }

        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
// Get access to the underlying writeable database
//        SQLiteDatabase db = handler.getWritableDatabase();
// Query for items from the database and get a cursor back
        final Cursor cursor = helper.getAll();
//        final Cursor ratingCursor = helper.getRating();

        //db.rawQuery("SELECT  * FROM RATINGS", null);

        // Find ListView to populate
        ListView lvItems = (ListView) myFragmentView.findViewById(R.id.main_list_tab_two);
        if (todoAdapter == null) {
            todoAdapter = new TodoCursorAdapter(getActivity(), cursor);

            lvItems.setAdapter(todoAdapter);
        } else {
            todoAdapter.swapCursor(cursor);
        }

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View localView, int position, long id) {
                Intent detailsIntent = new Intent(getContext(), StudentDetailsActivity.class);

                Cursor selectedCursor = (Cursor) parent.getAdapter().getItem(position);
//                 TODO: commented-out lines below are used for animated transitions
//  View view = localView.findViewById(R.id.card_view);
//                ActivityOptionsCompat bugOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, view, "moving_bug");

//                 TODO: this line below should help capture the ID associated with the database row, maybe?
                cursor.moveToPosition(position);
               // ratingCursor.moveToPosition(position);
                detailsIntent.putExtra("id", cursor.getInt(cursor.getColumnIndex(helper.COL_ID)));
               // detailsIntent.putExtra("id", ratingCursor.getInt(ratingCursor.getColumnIndex(helper.COL_ID)));

                startActivity(detailsIntent);
            }
        });
    }

    public class TodoCursorAdapter extends CursorAdapter {

        int counter;

        public TodoCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
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
                tvBody.setText(body);

            Picasso.with(context).load(imageUrl).into(imageView);
            }
    }


}

