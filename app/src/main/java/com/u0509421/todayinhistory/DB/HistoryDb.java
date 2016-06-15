package com.u0509421.todayinhistory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Terry on 17/3/16.
 */
public class HistoryDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HistoryDb";
    public static final String TABLE_NAME = "history";

    public HistoryDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE history ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT not null unique, "+
                "date TEXT, "+
                "eid TEXT )";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
