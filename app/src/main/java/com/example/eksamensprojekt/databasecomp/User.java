package com.example.eksamensprojekt.databasecomp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uId;
    public double initWeight;
    public double height;

}