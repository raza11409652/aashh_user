package com.aasshh.user.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = ProductDb.class , exportSchema = false   ,version = 2)
public abstract class DatabaseHelper extends RoomDatabase {

    private  static final String dbName = "product_db";
    private static  DatabaseHelper instancee ;
    public  static synchronized DatabaseHelper getInstance(Context context){
        if (instancee==null){
           instancee = Room.databaseBuilder(context.getApplicationContext() ,
                   DatabaseHelper.class , dbName)
                   .fallbackToDestructiveMigration()
                   .build();
        }

        return instancee ;
    }

    public abstract ProductDbDao productDbDao();


}
