package com.peery.android.projectscorpion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.peery.android.projectscorpion.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_USER = "user";
    private static final String USER_ID = "userID";
    private static final String USER_EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "( " + USER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USERNAME + "TEXT NOT NULL, "
            + USER_EMAIL + "TEXT, "
            + PASSWORD + "TEXT NOT NULL)";


    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public SQLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL(DROP_USER_TABLE);
        onCreate(DB);
    }

    public void insertUser(User user) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_EMAIL, user.getEmail());
        values.put(USERNAME, user.getUserName());
        values.put(PASSWORD, user.getPassword());

        DB.insert(TABLE_USER, null, values);
        DB.close();
    }

    public List<User> getAllUser() {
        String[] columns = {USER_ID, USERNAME, USER_EMAIL, PASSWORD};
        String sorting = USERNAME + " ASC";
        List<User> allUsers = new ArrayList<>();
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.query(TABLE_USER, columns, null, null, null, null, sorting);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                allUsers.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB.close();

        return allUsers;
    }

    public void updateUser(User user) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUserName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());

        DB.update(TABLE_USER, values, USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
        DB.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase DB = this.getReadableDatabase();

        DB.delete(TABLE_USER, USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
        DB.close();
    }

    public boolean doesUserExist(String email) {
        String[] columns = {USER_ID};
        SQLiteDatabase DB = this.getWritableDatabase();

        String entry = USER_EMAIL + " = ?";
        String[] entryArg = {email};

        Cursor cursor = DB.query(TABLE_USER, columns, entry, entryArg, null, null, null);
        int cCount = cursor.getCount();
        cursor.close();
        DB.close();
        if (cCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doesUserExist(String email, String password) {
        String[] columns = {USER_ID};
        SQLiteDatabase DB = this.getReadableDatabase();

        String entry = USER_EMAIL + " = ?" + " AND " + PASSWORD + " = ?";
        String[] entryArg = {email, password};

        Cursor cursor = DB.query(TABLE_USER, columns, entry, entryArg, null, null, null);
        int cCount = cursor.getCount();
        cursor.close();
        DB.close();
        if (cCount > 0) {
            return true;
        } else {
            return false;
        }
    }
}