package com.example.john.project3;

/**
 * Created by John on 5/31/16.
 */

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.john.project3.R.layout;

public class ListFragment extends Fragment implements ApiConnector.ApiResponseHandler {
    private View myFragmentView;
    private TextView myView;
    LocalDBHelper helper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ApiConnector.getInstance(ListFragment.this).doRequest();
        helper = LocalDBHelper.getInstance(getContext());



        myFragmentView = inflater.inflate(layout.fragment_list, container, false);

       // todoAdapter.changeCursor(newCursor);



//        myView = (TextView) myFragmentView.findViewById(id.test);
//        myView.setText(Storage.nameArrayList.toString());
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
// Setup cursor adapter using cursor from last step
        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(getActivity(),todoCursor);
// Attach cursor adapter to the ListView
        lvItems.setAdapter(todoAdapter);


    }

    public class TodoCursorAdapter extends CursorAdapter {
        public TodoCursorAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        // The newView method is used to inflate a new view and return it,
        // you don't bind any data to the view at this point.
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(layout.list_frag_format, parent, false);
        }

        // The bindView method is used to bind all data to a given view
        // such as setting the text on a TextView.
        @Override
        public void bindView(View view, Context context, Cursor cursor) {



            // Find fields to populate in inflated template
            TextView tvBody = (TextView) view.findViewById(R.id.main_list_name);
//            TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);
            // Extract properties from cursor
            String body = cursor.getString(cursor.getColumnIndexOrThrow("name"));

//            int priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));
            // Populate fields with extracted properties
            tvBody.setText(body);
//            tvPriority.setText(String.valueOf(priority));
        }
    }
}

