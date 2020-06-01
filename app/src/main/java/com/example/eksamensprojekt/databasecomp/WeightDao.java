package com.example.eksamensprojekt.databasecomp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WeightDao {

    @Query("SELECT * FROM weight")
    public Weight[] loadAllWeights();

    @Query("SELECT COUNT(*) from weight")
    int countWeight();

    @Insert
    void insert(Weight weight);

   @Query("SELECT * FROM weight ORDER BY date DESC LIMIT 1")
    Weight getLastWeightDate();








}