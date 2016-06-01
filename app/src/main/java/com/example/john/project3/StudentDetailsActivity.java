package com.example.john.project3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class StudentDetailsActivity extends AppCompatActivity implements ApiConnector.ApiResponseHandler {
    LocalDBHelper helper;
    int id;
    float myRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ApiConnector.getInstance(StudentDetailsActivity.this).doRequest();

        helper = LocalDBHelper.getInstance(StudentDetailsActivity.this);
        if (id >= 0) {
            Cursor cursor = helper.getDescriptionById(id);
            myRating = Float.parseFloat(cursor.getString(cursor.getColumnIndex(helper.COL_RATING)));
            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    getRating(rating);
                }
            });

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }
    private float getRating(float rating) {
        myRating = rating;
        SQLiteDatabase myDB = helper.getReadableDatabase();
        String strFilter = "_id=" + id;
        ContentValues args = new ContentValues();
        args.put(LocalDBHelper.COL_RATING, myRating);
        myDB.update(helper.RATING_TABLE_NAME, args, strFilter, null);
        helper.close();
        return myRating;
    }

    //String[] idArray, String[] nameArray, String[] titleArray, String[] skillsArray, String[] openArray,
    //String[] gitHubArray, String[] gaArray, String[] linkedInArray, String[] otherArray, String[] imageArray, String[] urlArray,
    @Override
        public void handleResponseName (String[] idArray,String[] nameArray, String[] titleArray, String[] skillsArray, String[] openArray,
                                        String[] gitHubArray, String[] gaArray, String[] linkedInArray, String[] otherArray, String[] imageArray, String[] urlArray){
            Toast.makeText(StudentDetailsActivity.this, nameArray + "Student Details Activity", Toast.LENGTH_SHORT).show();
        }
    }

