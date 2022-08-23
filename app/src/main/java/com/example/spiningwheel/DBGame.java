package com.example.spiningwheel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBGame extends SQLiteOpenHelper {

    DBHelper DB;

    public static final String DBName = "players.db";
    public DBGame(Context context) {
        super(context, "players.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase PlayerDB) {
        PlayerDB.execSQL("create Table players(username TEXT primary key, amount INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
