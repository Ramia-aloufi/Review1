package com.example.flickrbrowserapproom_optional

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [MyImg::class],version = 1,exportSchema = false)
abstract class MyImageDatabase:RoomDatabase() {
    abstract fun MyImgDao(): MyImgDao;
    companion object{
        var instance:MyImageDatabase?=null;
        fun getInstance(ctx: Context):MyImageDatabase{
            if(instance!=null)
            {
                return  instance as MyImageDatabase;
            }
            instance = Room.databaseBuilder(ctx,MyImageDatabase::class.java,"raam11").run { allowMainThreadQueries() }.build()
            return instance as MyImageDatabase;
        }
    }
}