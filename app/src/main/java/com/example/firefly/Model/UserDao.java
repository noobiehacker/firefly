package com.example.firefly.Model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    public void addUser(User user);

    @Query("select * from users where user_name = :username AND hashed_password = :hashedpassword")
    public User login(String username, String hashedpassword);
}
