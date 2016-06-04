package com.example.john.project3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentDetailsActivity extends AppCompatActivity {
    LocalDBHelper helper;
    int id;
    float myRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        id = getIntent().getIntExtra("id", -1);
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
        Button button = (Button)findViewById(R.id.button);

        detailsImage.setImageResource(R.drawable.imgres_ga);
        detailsName.setText("George Jorge");
        detailsTitle.setText("The Georgian");
        detailsSkills.setText("Jumping \n Climbing \n Playing");
        detailsOpen.setText("Fulltime \n Part Time \n Per Diem");
        detailsGAProfile.setText("https://generalassemb.ly/");
        detailsLinkedIn.setText("http://www.linkedin.com");
        detailsGithub.setText("www.github.com");
        detailsOther.setText("www.myblog.com");
        detailsEmail.setText("thisemail@gmail.com");

        helper = LocalDBHelper.getInstance(StudentDetailsActivity.this);


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

