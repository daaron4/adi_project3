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
    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "RATINGS_DB";
    public static final String DATA_TABLE_NAME = "RATINGS";
    public static final String RATINGBAR_VALUE_TABLE = "BAR";

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

    public static final String[] DATA_COLUMNS = {COL_ID, COL_NAME, COL_TITLE, COL_SKILLS,
            COL_OPEN, COL_GITHUB, COL_GA, COL_LINKEDIN, COL_OTHER, COL_IMAGE, COL_URL};

    private static final String CREATE_DATA_TABLE =
            "CREATE TABLE " + DATA_TABLE_NAME +
                    "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT, " +
                    COL_TITLE + " TEXT, " +
                    COL_SKILLS + " TEXT, " +
                    COL_OPEN + " TEXT, " +
                    COL_GITHUB + " TEXT, " +
                    COL_GA + " TEXT, " +
                    COL_LINKEDIN + " TEXT, " +
                    COL_OTHER + " TEXT, " +
                    COL_IMAGE + " TEXT, " +
                    COL_URL + " TEXT )";

    private static final String CREATE_RATINGBAR_VALUES_TABLE =
            "CREATE TABLE " + RATINGBAR_VALUE_TABLE +
                    "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_RATING + " INTEGER )";

    private static LocalDBHelper instance;

    public static LocalDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDBHelper(context.getApplicationContext());
        }
        return instance;
    }

    public LocalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATA_TABLE);
        db.execSQL(CREATE_RATINGBAR_VALUES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATA_TABLE_NAME);
        this.onCreate(db);
    }
    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATA_TABLE_NAME, // a. table
                DATA_COLUMNS, // b. column names
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

        Cursor cursor = db.query(DATA_TABLE_NAME,
                new String[]{COL_ID, COL_NAME, COL_TITLE, COL_SKILLS,
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
    public void seedData(ArrayList<String> id,
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
        ContentValues values = new ContentValues();
        myDB.delete(DATA_TABLE_NAME, null, null);
        long returnId = 0;
        for(int i = 0 ; i < name.size(); i++) {
            values.put(COL_NAME, name.get(i));
            values.put(COL_TITLE, title.get(i));
            values.put(COL_SKILLS, skills.get(i));
            values.put(COL_OPEN, open.get(i));
            values.put(COL_GITHUB, github.get(i));
            values.put(COL_GA, ga.get(i));
            values.put(COL_LINKEDIN, linkedin.get(i));
            values.put(COL_OTHER, other.get(i));
            values.put(COL_IMAGE, image.get(i));
            values.put(COL_URL, url.get(i));
            myDB.insert(DATA_TABLE_NAME, null, values);
            }
        close();
    }
}
