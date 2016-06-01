package com.example.john.project3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DarrellG on 6/1/16.
 */
public class LocalDBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RATINGS.db";
    public static final String RATING_TABLE_NAME = "RATINGS";
    public static final String COL_ID = "_id";
    public static final String COL_RATING = "Rating";
    public static final String[] RATING_COLUMNS = {COL_ID, COL_RATING};
    private static final String CREATE_RATING_TABLE =
            "CREATE TABLE " + RATING_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                    COL_RATING + " INTEGER )";

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
                new String[]{COL_RATING},
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
}
