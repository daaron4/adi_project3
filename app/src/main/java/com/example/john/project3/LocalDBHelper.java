package com.example.john.project3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by DarrellG on 6/1/16.
 */
public class LocalDBHelper extends SQLiteOpenHelper{
    String id;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RATINGS.db";
    public static final String RATING_TABLE_NAME = "RATINGS";

    public static final String COL_ID = "_id";
    public static final String COL_RATING = "Rating";
    public static final String COL_NAME = "name";
    public static final String COL_TITLE = "title";
    public static final String COL_SKILLS = "skills";
    public static final String COL_OPEN = "open";
    public static final String COL_GITHUB = "github";
    public static final String COL_GA = "ga";
    public static final String COL_LINKEDIN = "linkedin";
    public static final String COL_OTHER = "other";
    public static final String COL_IMAGE = "image";
    public static final String COL_URL = "url";

    public static final String[] RATING_COLUMNS = {COL_ID, COL_RATING, COL_NAME, COL_TITLE, COL_SKILLS,
            COL_OPEN, COL_GITHUB, COL_GA, COL_LINKEDIN, COL_OTHER, COL_IMAGE, COL_URL};

    private static final String CREATE_RATING_TABLE =
            "CREATE TABLE " + RATING_TABLE_NAME +
                    "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                    COL_RATING + " INTEGER, " +
                    COL_NAME + " TEXT, " +
                    COL_TITLE + " TEXT, " +
                    COL_SKILLS + " TEXT, " +
                    COL_OPEN + " TEXT, " +
                    COL_GITHUB + " TEXT, " +
                    COL_GA + " TEXT, " +
                    COL_LINKEDIN + " TEXT, " +
                    COL_OTHER + " TEXT, " +
                    COL_IMAGE + " TEXT, " +
                    COL_URL + " TEXT);";

    private static LocalDBHelper instance;

    public static LocalDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDBHelper(context);
        }
        return instance;
    }

    private LocalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RATING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RATING_TABLE_NAME);
        this.onCreate(db);
    }
    public Cursor getRatings(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(RATING_TABLE_NAME, // a. table
                RATING_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
    public Cursor getDescriptionById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(RATING_TABLE_NAME,
                new String[]{COL_RATING, COL_NAME, COL_TITLE, COL_SKILLS,
                        COL_OPEN, COL_GITHUB, COL_GA, COL_LINKEDIN, COL_OTHER, COL_IMAGE, COL_URL},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor;
        } else {
            return null;
        }
    }
    public void updateData(ArrayList<String> id,
                           ArrayList<String> name,
                            ArrayList<String> title,
                            ArrayList<String> skills,
                            ArrayList<String> open,
                            ArrayList<String> github,
                            ArrayList<String> ga,
                            ArrayList<String> linkedin,
                            ArrayList<String> other,
                            ArrayList<String> image,
                            ArrayList<String> url ) {
        SQLiteDatabase myDB = getReadableDatabase();

        for(int i = 0 ; i < name.size(); i++){

            ContentValues args = new ContentValues();
            args.put(COL_NAME, name.get(i));
            String strFilter = "_id=?";
            String[] selArgs = new String[]{id.get(i)};
            myDB.update(RATING_TABLE_NAME, args, strFilter, selArgs);

        }

close();
//        ContentValues args = new ContentValues();
//        args.put(LocalDBHelper.COL_RATING, myRating);
//        myDB.update(helper.RATING_TABLE_NAME, args, strFilter, null);
//        helper.close();
//        return myRating;
    }
}
