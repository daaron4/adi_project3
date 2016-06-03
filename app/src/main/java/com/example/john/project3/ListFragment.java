package com.example.john.project3;

/**
 * Created by John on 5/31/16.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.john.project3.R.layout;

public class ListFragment extends Fragment implements ApiConnector.ApiResponseHandler {
    private View myFragmentView;

    LocalDBHelper helper;
    TodoCursorAdapter todoAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ApiConnector.getInstance(ListFragment.this).doRequest();
        helper = LocalDBHelper.getInstance(getContext());

        myFragmentView = inflater.inflate(layout.fragment_list, container, false);

        return myFragmentView;
    }

    @Override
    public void handleResponseName(String[] idArray, String[] nameArray, String[] titleArray, String[] skillsArray, String[] openArray, String[] gitHubArray, String[] gaArray, String[] linkedInArray, String[] otherArray, String[] imageArray, String[] urlArray) {

        Storage.idArrayList = new ArrayList<>(Arrays.asList(idArray));
        Storage.nameArrayList = new ArrayList<>(Arrays.asList(nameArray));
        Storage.titleArrayList = new ArrayList<>(Arrays.asList(titleArray));
        Storage.skillsArrayList = new ArrayList<>(Arrays.asList(skillsArray));
        Storage.openArrayList = new ArrayList<>(Arrays.asList(openArray));
        Storage.gitHubArrayList = new ArrayList<>(Arrays.asList(gitHubArray));
        Storage.gaArrayList = new ArrayList<>(Arrays.asList(gaArray));
        Storage.linkedInArrayList = new ArrayList<>(Arrays.asList(linkedInArray));
        Storage.otherArrayList = new ArrayList<>(Arrays.asList(otherArray));
        Storage.imageArrayList = new ArrayList<>(Arrays.asList(imageArray));
        Storage.urlArrayList = new ArrayList<>(Arrays.asList(urlArray));
        SQLiteDatabase myDB = helper.getReadableDatabase();
        myDB.delete(LocalDBHelper.DATA_TABLE_NAME, null, null);

        helper.seedData(Storage.idArrayList,
                Storage.nameArrayList,
                Storage.titleArrayList,
                Storage.skillsArrayList,
                Storage.openArrayList,
                Storage.gitHubArrayList,
                Storage.gaArrayList,
                Storage.linkedInArrayList,
                Storage.otherArrayList,
                Storage.imageArrayList,
                Storage.urlArrayList);



        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        LocalDBHelper handler = new LocalDBHelper(getContext());
// Get access to the underlying writeable database
//        SQLiteDatabase db = handler.getWritableDatabase();
// Query for items from the database and get a cursor back
        Cursor todoCursor = handler.getAll();

        //db.rawQuery("SELECT  * FROM RATINGS", null);

        // Find ListView to populate
        ListView lvItems = (ListView) myFragmentView.findViewById(R.id.main_list_tab_two);
        if (todoAdapter == null) {
            todoAdapter = new TodoCursorAdapter(getActivity(), todoCursor);
            lvItems.setAdapter(todoAdapter);
        } else {
            todoAdapter.changeCursor(todoCursor);
        }

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

            TextView tvBody = (TextView) view.findViewById(R.id.main_list_name);

            ImageView imageView = (ImageView) view.findViewById(R.id.main_list_image);

            String body = cursor.getString(cursor.getColumnIndexOrThrow("name"));

            tvBody.setText(body);
            // TODO Set the 'url' value from database
            Picasso.with(context).load("http://i.imgur.com/IWGvno3.jpg").into(imageView);

        }
    }
}

