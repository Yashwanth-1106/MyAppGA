package com.example.myappga;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Student.class,exportSchema = false,version = 1)
public abstract class DataBase extends RoomDatabase {
    private static final String DB_NAME = "UserDb";
    private static DataBase instance;

    public static synchronized DataBase getDB(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context, DataBase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract DAO userDao();
}






