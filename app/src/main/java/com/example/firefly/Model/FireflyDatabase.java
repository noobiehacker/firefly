package com.example.firefly.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version =1)
public abstract class FireflyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
