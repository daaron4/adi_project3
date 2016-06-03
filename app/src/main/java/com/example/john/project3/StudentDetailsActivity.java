package com.example.john.project3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class StudentDetailsActivity extends AppCompatActivity {
    LocalDBHelper helper;
    int id;
    float myRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        helper = LocalDBHelper.getInstance(StudentDetailsActivity.this);
//        if (id >= 0) {
//            Cursor cursor = helper.getDescriptionById(id);
//            myRating = Float.parseFloat(cursor.getString(cursor.getColumnIndex(helper.COL_RATING)));
//            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
//
//            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                @Override
//                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                    getRating(rating);
//                }
//            });
//
//            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });
//        }
    }
    private float getRating(float rating) {
        myRating = rating;
        SQLiteDatabase myDB = helper.getReadableDatabase();
        String strFilter = "_id=" + id;
        ContentValues args = new ContentValues();
        args.put(LocalDBHelper.COL_RATING, myRating);
        myDB.update(helper.DATA_TABLE_NAME, args, strFilter, null);
        helper.close();
        return myRating;
    }
    }

