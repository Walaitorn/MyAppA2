package com.a23279.egco428.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pam on 11/20/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_LOGIN = "login"; // Table's Name
    public static final String COLUMN_ID = "_id"; // column's Name
    public static final String COLUMN_USERNAME = "username"; // column's Name
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_LATI = "latitude";
    public static final String COLUMN_LONGI = "longitude";

    private static final String DATABASE_NAME = "login.db"; // file Database Name
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_LOGIN + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_USERNAME + " text not null, " + COLUMN_PASSWORD + " text not null, "+ COLUMN_LATI + " text not null, "+ COLUMN_LONGI
            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE); // execute SQL statement
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }
}
