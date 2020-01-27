package com.notificationshare.contentpro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="conter_pro";
    public static final String DATABASE_TABLE="conter_table";
    public static final String COLOUM_ID="id";
    public static final String COLOUM_NAME="email";

    public SQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = ("CREATE TABLE " + DATABASE_TABLE + "(" + COLOUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLOUM_NAME + " TEXT" + ")");
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+DATABASE_TABLE);
        onCreate(db);
    }
}
