package com.example.john.project3;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class StudentDetailsActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        id = getIntent().getIntExtra("id", -1);

        Cursor detailsCursor = LocalDBHelper.getInstance(StudentDetailsActivity.this).getDescriptionById(id);
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

        RatingBar ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        ImageView robit = (ImageView) findViewById(R.id.details_robot);
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

        detailsCursor = LocalDBHelper.getInstance(StudentDetailsActivity.this).getRatingAtId(id);
        DatabaseUtils.dumpCursor(detailsCursor);
        float captureRating = detailsCursor.getFloat(detailsCursor.getColumnIndex(LocalDBHelper.COL_RATING));

        ratingBar1.setRating(captureRating);
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

        // ToDo: vigorous testing needed:
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

        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                LocalDBHelper.getInstance(StudentDetailsActivity.this).updateRating(id, rating);
            }
        });

        // ToDo: what is this for?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailsImage.setTransitionName("moving_bug");
        }

    }

}

