package com.peery.android.projectscorpion;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


// todo: add userAutoFill class after created inside {} ex. {User.class, userAutoFill.class}
@Database(entities = {User.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {

    // turn this class into Singleton so the same instance of the database is used everywhere in our app
    // which can be accessed by the static variable
    private static UserDataBase instance;

    // used to access UserDao. ROOM takes car of all the code inside the method
    public abstract UserDao userDao();

    //create Singleton database that is synchronized. this will keep multiple threads from being able to
    // access the database at the same time
    public static synchronized UserDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDataBase.class, "projectScorpion_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
