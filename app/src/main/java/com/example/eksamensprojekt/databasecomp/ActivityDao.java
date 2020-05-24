package com.example.eksamensprojekt.databasecomp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ActivityDao {

    @Query("SELECT * FROM activity1")
    public Activity[] loadAllActivities();

    @Update
    int update(Activity activity);

    @Insert
    void insert(Activity activity);

    @Query("SELECT COUNT(*) from activity1")
    int countActivities();


    @Query("SELECT * FROM activity1 WHERE uid BETWEEN :startDate AND :endDate")
    public Activity[] loadAllActivitiesBetweenDates(int startDate, int endDate);

}
