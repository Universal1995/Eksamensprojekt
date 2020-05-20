package com.example.eksamensprojekt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mActivityNames = new ArrayList<>();
    private ArrayList<String> mWeekdays = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        adapter = new RecyclerViewAdapter(mActivityNames,mWeekdays,this);

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

        initTableData();
    }


    public void nextViewStats(View v){
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }

    public void nextViewActivities(View v){
        Intent intent = new Intent(this, Activities.class);
        startActivity(intent);
    }

    public void initTableData(){

        mActivityNames.clear();
        mWeekdays.clear();
        GetDataFromDatabase getDataFromDatabase = new GetDataFromDatabase();
        getDataFromDatabase.execute();

        adapter.notifyDataSetChanged();


    }



    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class GetDataFromDatabase extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // start loading icon

        }

        @Override
        protected Void doInBackground(Void... Voids) {


            mActivityNames.clear();
            mWeekdays.clear();

            mActivityNames.add("hahaha");
            mWeekdays.add("wendsday");


            mActivityNames.add("hahaha");
            mWeekdays.add("wendsday");


            mActivityNames.add("hahaha");
            mWeekdays.add("wendsday");


            mActivityNames.add("hahaha");
            mWeekdays.add("wendsday");


            mActivityNames.add("hahaha");
            mWeekdays.add("wendsday");


            mActivityNames.add("hahaha");
            mWeekdays.add("wendsday");


            mActivityNames.add("hahaha");
            mWeekdays.add("wendsday");



            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //load ui
            initRecyclerView();
        }
    }
}
