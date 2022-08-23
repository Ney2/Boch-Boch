package com.example.spiningwheel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DBName = "BochBoch.db";

    public DBHelper(Context context ) {
        super(context, "BochBoch.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
       MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
       MyDB.execSQL("create Table PlayersDB(username TEXT primary key, amount INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
       MyDB.execSQL("drop Table if exists users");
       MyDB.execSQL("drop Table if exists PlayersDB");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long results = MyDB.insert("users", null, contentValues);
        if(results == -1) return false;
        else
            return true;
    }

    public Boolean playgame(String username, String amount) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("amount", amount);
        long results = MyDB.insert("users", null, contentValues);
        if(results == -1) return false;
        else
            return true;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String [] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String [] {username, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
