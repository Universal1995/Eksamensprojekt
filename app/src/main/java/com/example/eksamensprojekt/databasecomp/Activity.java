package com.example.eksamensprojekt.databasecomp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "activity1")
public class Activity {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    public String activityName;
    public String weekday;

}
