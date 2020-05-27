package com.example.eksamensprojekt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eksamensprojekt.databasecomp.Activity;
import com.example.eksamensprojekt.databasecomp.AppDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    protected AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getAppDatabase(this);
        if(db.activityDao().countActivities() == 0){
            Activity activity = new Activity();
            activity.activityName = "sport";
            activity.weekday = "Onsdag";
            db.activityDao().insert(activity);
        }





        adapter = new RecyclerViewAdapter(this);

        Button buttonStats = findViewById(R.id.button);


        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextViewStats(v);
            }
        });

        Button buttonActivites = findViewById(R.id.button7);
        buttonActivites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextViewActivities(v);

            }
        });

        initRecyclerView();
    }


    public void nextViewStats(View v){
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }

    public void nextViewActivities(View v){
        Intent intent = new Intent(this, Activities.class);
        startActivity(intent);
    }





    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    protected void onResume() {
        super.onResume();
        adapter.resetView();

        //adapter.notifyDataSetChanged();

    }
}
