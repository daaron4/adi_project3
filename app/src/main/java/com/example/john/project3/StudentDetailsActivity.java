package com.example.john.project3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StudentDetailsActivity extends AppCompatActivity {
    LocalDBHelper helper;
    int id;
    float myRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        int id = getIntent().getIntExtra("id", -1);

        LocalDBHelper dbHelper = LocalDBHelper.getInstance(StudentDetailsActivity.this);

        Cursor detailsCursor = dbHelper.getDescriptionById(id);

        String captureImage = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_IMAGE));
        String captureName = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_NAME));
        String captureTitle = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_TITLE));
        String captureSkills = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_SKILLS));
        String captureOpen = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_OPEN));
        String captureGAProfile = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_GA));
        String captureLinkedIn = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_LINKEDIN));
        String captureGithub = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_GITHUB));
        String captureOther = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_OTHER));
        String captureEmail = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_GA));

        ImageView detailsImage = (ImageView)findViewById(R.id.student_details_image);
        TextView detailsName = (TextView)findViewById(R.id.student_details_name);
        TextView detailsTitle = (TextView)findViewById(R.id.student_details_title);
        TextView detailsSkills = (TextView)findViewById(R.id.details_skills);
        TextView detailsOpen = (TextView)findViewById(R.id.details_open);
        TextView detailsGAProfile = (TextView)findViewById(R.id.details_ga_profile);
        TextView detailsLinkedIn = (TextView)findViewById(R.id.details_linkedin);
        TextView detailsGithub = (TextView)findViewById(R.id.details_github);
        TextView detailsOther = (TextView)findViewById(R.id.details_other);
        TextView detailsEmail = (TextView)findViewById(R.id.details_email);

        Picasso.with(this).load(captureImage).into(detailsImage);
        detailsName.setText(captureName);
        detailsTitle.setText(captureTitle);
        detailsSkills.setText(captureSkills);
        detailsOpen.setText(captureOpen);
        detailsGAProfile.setText(captureGAProfile);
        detailsLinkedIn.setText(captureLinkedIn);
        detailsGithub.setText(captureGithub);
        detailsOther.setText(captureOther);
        detailsEmail.setText(captureEmail);

        helper = LocalDBHelper.getInstance(StudentDetailsActivity.this);
//        if (id >= 0) {
//            Cursor cursor = helper.getDescriptionById(id);
//            myRating = Float.parseFloat(cursor.getString(cursor.getColumnIndex(helper.COL_RATING)));
//            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
//
//            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                @Override
//                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                    updateRating(rating);
//                }
//            });
//        }
    }
    private float updateRating(float rating) {
        myRating = rating;
        SQLiteDatabase myDB = helper.getReadableDatabase();
        String strFilter = "_id=" + id;
        ContentValues args = new ContentValues();
        args.put(LocalDBHelper.COL_RATING, myRating);
        myDB.update(helper.RATINGBAR_VALUE_TABLE, args, strFilter, null);
        helper.close();
        return myRating;
    }
    }

