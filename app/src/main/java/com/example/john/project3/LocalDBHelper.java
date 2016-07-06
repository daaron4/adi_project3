package com.example.john.project3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.CursorAdapter;

/**
 * Created by DarrellG on 6/1/16.
 */
public class LocalDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "RATINGS_DB";
    public static final String DATA_TABLE_NAME = "People";
    public static final String RATINGBAR_VALUE_TABLE = "Rating";

    public static final String COL_ID = "_id";
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
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";

    public static final String COL_RATING = "rating";
    public static final String COL_NOTE = "note";
    public static final String COL_RELATION_ID = "relation";

    public static final String[] DATA_COLUMNS = {COL_ID, COL_NAME, COL_TITLE, COL_SKILLS,
            COL_OPEN, COL_GITHUB, COL_GA, COL_LINKEDIN, COL_OTHER, COL_IMAGE, COL_URL, COL_EMAIL, COL_PHONE};

    public static final String[] RATING_COLUMNS = {COL_ID, COL_NOTE, COL_RATING, COL_RELATION_ID};

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
                    COL_EMAIL + " TEXT, " +
                    COL_PHONE + " TEXT, " +
                    COL_URL + " TEXT )";

    private static final String CREATE_RATINGBAR_VALUES_TABLE =
            "CREATE TABLE " + RATINGBAR_VALUE_TABLE +
                    "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOTE + " TEXT, " +
                    COL_RATING + " REAL, " + COL_RELATION_ID + " INTEGER )";

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
        seedRatingsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + RATINGBAR_VALUE_TABLE);
        this.onCreate(db);
    }

    // ToDo: seed ratings for new people when profiles are made:
    private void seedRatingsTable(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 12);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 13);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 14);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 15);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 16);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 17);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 18);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 19);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 20);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

        values = new ContentValues();
        values.put(COL_NOTE, "");
        values.put(COL_RATING, 0);
        values.put(COL_RELATION_ID, 21);
        db.insert(RATINGBAR_VALUE_TABLE, null, values);

    }

//    // ToDo: decide if this is needed:
//    public Cursor getAll() {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT " + DATA_TABLE_NAME + "." + COL_ID + ", " +
//                COL_NAME + ", " +
//                COL_GA + ", " +
//                COL_IMAGE + ", " +
//                COL_EMAIL + ", " +
//                COL_GITHUB + ", " +
//                COL_LINKEDIN + ", " +
//                COL_OPEN + ", " +
//                COL_OTHER + ", " +
//                COL_PHONE + ", " +
//                COL_SKILLS + ", " +
//                COL_TITLE + ", " +
//                COL_URL + " FROM " + DATA_TABLE_NAME + " JOIN " + RATINGBAR_VALUE_TABLE + " ON " + DATA_TABLE_NAME + "." + COL_ID + "=" + RATINGBAR_VALUE_TABLE + "." + COL_RELATION_ID, null, null);
//
//        return cursor;
//    }

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

    public Cursor getRatingAtId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selections = COL_RELATION_ID + " = ?";
        String[] selectionArgs = new String[] {
                String.valueOf(id)
        };

        Cursor cursor = db.query(RATINGBAR_VALUE_TABLE, // a. table
                RATING_COLUMNS, // b. column names
                selections, // c. selections
                selectionArgs, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getDescriptionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATA_TABLE_NAME, DATA_COLUMNS,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            return cursor;
        } else {
            return null;
        }
    }

    public void seedData(PersonModel[] data) {
        SQLiteDatabase myDB = getWritableDatabase();
        ContentValues values = new ContentValues();
        myDB.delete(DATA_TABLE_NAME, null, null);

        // ToDo: should be changed when URL's are correct on API:
        for (PersonModel aPerson : data) {
//            // ToDo: should probably get rid of this:
//            rating.put(COL_RELATION_ID, aPerson.getId());
//            myDB.insert(RATINGBAR_VALUE_TABLE, null, rating);
            values.put(COL_ID, aPerson.getId());
            values.put(COL_NAME, aPerson.getName());
            values.put(COL_TITLE, aPerson.getTitle());
            values.put(COL_SKILLS, aPerson.getSkills());
            values.put(COL_OPEN, aPerson.getOpen());
            values.put(COL_GITHUB, aPerson.getGithub());
            values.put(COL_GA, aPerson.getGa());
            values.put(COL_LINKEDIN, aPerson.getLinkedin());
            values.put(COL_OTHER, aPerson.getOther());
            values.put(COL_IMAGE, aPerson.getImage());
            values.put(COL_EMAIL, aPerson.getUser_email());
            values.put(COL_PHONE, aPerson.getPhone());
            values.put(COL_URL, aPerson.getUrl());
            myDB.insert(DATA_TABLE_NAME, null, values);
        }
        myDB.close();
    }

    public void updateRating(int id, float newRating) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_RATING, newRating);
        String selection =  COL_RELATION_ID + " = ?";
        String[] selectionArgs = new String[] {
                String.valueOf(id)
        };

        db.update(RATINGBAR_VALUE_TABLE, values, selection, selectionArgs);
        db.close();
    }

}
