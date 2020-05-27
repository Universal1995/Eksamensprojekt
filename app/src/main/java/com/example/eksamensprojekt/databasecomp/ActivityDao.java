package com.example.eksamensprojekt.databasecomp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ActivityDao {
    @Query("SELECT * FROM activity")
    public Activity[] loadAllActivities();

    @Query("SELECT * FROM activity where activityDone = 0")
    public Activity[] loadAllUndoneActivities();

    @Query("SELECT * FROM activity where activityDone = 1")
    public Activity[] loadAllDoneActivities();

    @Query("UPDATE activity SET activityDone = 1 WHERE uid = :uid")
    int updateActivityDone(int uid);
    @Update
    int update(Activity activity);

    @Insert
    void insert(Activity activity);

    @Query("SELECT COUNT(*) from activity")
    int countActivities();


    @Query("SELECT * FROM activity WHERE uid BETWEEN :startDate AND :endDate")
    public Activity[] loadAllActivitiesBetweenDates(int startDate, int endDate);



}
