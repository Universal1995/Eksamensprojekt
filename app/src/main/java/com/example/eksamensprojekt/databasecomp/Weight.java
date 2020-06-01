package com.example.eksamensprojekt.databasecomp;

import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Entity(tableName = "Weight")
public class Weight {
    @PrimaryKey(autoGenerate = true)
    public int wId;
    @NonNull
    public Long date = new Date().getTime();
    public Double weight;
}