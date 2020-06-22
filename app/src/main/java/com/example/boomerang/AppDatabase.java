package com.example.boomerang;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = MovieEntity.class,version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase instance;

    public  static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (new Object()){
                instance = Room.databaseBuilder(context.getApplicationContext()
                        ,AppDatabase.class,"Movies")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return instance;
    }
    public abstract MovieDao movieDao();
}
