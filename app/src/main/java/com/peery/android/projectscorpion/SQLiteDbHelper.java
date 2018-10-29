package com.peery.android.projectscorpion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDbHelper extends SQLiteOpenHelper {

    private static final int database_version = 1;
    private static final String DATABASE_NAME = "ProjectScorpion.db";
    private static final String TABLE_NAME = "LogIn";
    private static final String USERNAME = "UserName";
    private static final String PASSWORD = "Password";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "  + USERNAME
            + " TEXT PRIMARY KEY NOT NULL, " + PASSWORD + " TEXT NOT NULL)";
    private static final String DROP_TABLE = "DROP TABLE IF EXITS " + TABLE_NAME;
    SQLiteDatabase DB;

    public SQLiteDbHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
            DB.execSQL(CREATE_TABLE);
    }

    public void insertUser(String userName, String userPass) {
        ContentValues values = new ContentValues();

        values.put(USERNAME, userName);
        values.put(PASSWORD, userPass);
        DB = this.getWritableDatabase();
        DB.insert(TABLE_NAME, null, values);
        DB.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS ' " + TABLE_NAME + "'");
        this.onCreate(DB);

    }

//    public User findLogIn(String username) {
//
//        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME + " = "
//                + "'" + username+ "'";
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery(query, null);
//        //User user = new User();
//        if (cursor.moveToFirst()){
//            cursor.moveToFirst();
//            //user.setUserName(cursor.getString(0));
//            //user.setUserPassword(cursor.getString(1));
//            cursor.close();
//        } else {
//            user = null;
//        }
//        DB.close();
//        return user;
//    }
}



