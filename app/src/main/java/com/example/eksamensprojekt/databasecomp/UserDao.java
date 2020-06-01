package com.example.eksamensprojekt.databasecomp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user where uid = 1")
    public User GetUser();


    @Insert
    void insert(User user);

    @Query("UPDATE User SET height = :newHight WHERE uid = 1")
    int UpdateHight(int newHight);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

}

