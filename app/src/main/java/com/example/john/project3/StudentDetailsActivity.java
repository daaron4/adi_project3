package com.example.john.project3;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StudentDetailsActivity extends AppCompatActivity {
    LocalDBHelper helper;
    int id;
    float myRating;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        int id = getIntent().getIntExtra("id", -1);

        LocalDBHelper dbHelper = LocalDBHelper.getInstance(StudentDetailsActivity.this);

        Cursor detailsCursor = dbHelper.getDescriptionById(id);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.robot_cartwheel);

        String captureImage = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_IMAGE));
        String captureName = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_NAME));
        String captureTitle = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_TITLE));
        String captureSkills = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_SKILLS));
        String captureOpen = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_OPEN));
        String captureGAProfile = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_GA));
        final String captureLinkedIn = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_LINKEDIN));
        String captureGithub = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_GITHUB));
        String captureOther = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_OTHER));
        final String captureEmail = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_EMAIL));
        final String capturePhone = detailsCursor.getString(detailsCursor.getColumnIndex(LocalDBHelper.COL_PHONE));

        ImageView robit = (ImageView)findViewById(R.id.details_robot);
        ImageView detailsImage = (ImageView) findViewById(R.id.student_details_image);
        TextView detailsName = (TextView) findViewById(R.id.student_details_name);
        TextView detailsTitle = (TextView) findViewById(R.id.student_details_title);
        TextView detailsSkills = (TextView) findViewById(R.id.details_skills);
        TextView detailsOpen = (TextView) findViewById(R.id.details_open);
        TextView detailsGAProfile = (TextView) findViewById(R.id.details_ga_profile);
        Button detailsLinkedIn = (Button)findViewById(R.id.details_linkedin);
        TextView detailsGithub = (TextView) findViewById(R.id.details_github);
        TextView detailsOther = (TextView) findViewById(R.id.details_other);
//        TextView detailsEmail = (TextView) findViewById(R.id.details_email);
//        TextView detailsPhone = (TextView) findViewById(R.id.details_phone);
        Button callButton = (Button) findViewById(R.id.call_button);
        Button detailsEmail = (Button) findViewById(R.id.details_email);

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
        callButton.setText(capturePhone);

        robit.startAnimation(animation);

        helper = LocalDBHelper.getInstance(StudentDetailsActivity.this);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + capturePhone));
                startActivity(callIntent);

            }
        });
        detailsEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",captureEmail, null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            }
        });
        detailsLinkedIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(captureLinkedIn));
                startActivity(i);

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailsImage.setTransitionName("moving_bug");
        }

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

