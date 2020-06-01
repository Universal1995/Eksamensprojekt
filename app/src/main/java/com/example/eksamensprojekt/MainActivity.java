package com.example.eksamensprojekt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eksamensprojekt.databasecomp.Activity;
import com.example.eksamensprojekt.databasecomp.AppDatabase;
import com.example.eksamensprojekt.databasecomp.Weight;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private AppDatabase db;
    private EditText enterWeightEditter;
    private Button enterWeightButton;
    private Weight weight;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterWeightEditter = findViewById(R.id.enterWeightEditText);
        enterWeightButton = findViewById(R.id.submitButton);

        db = AppDatabase.getAppDatabase(this);
        if(db.userDao().countUsers() == 0){
            /*Activity activity = new Activity();
            activity.activityName = "sport";
            activity.weekday = "Onsdag";
            db.activityDao().insert(activity);*/
            firstTimeLoginActivity();
        }

        if(new Date(db.weightDao().getLastWeightDate()) != new Date(new Date().getTime())){
            enterWeightEditter.setVisibility(View.GONE);
            enterWeightButton.setVisibility(View.GONE);
        }
        else{
          enterWeightButton.setVisibility(View.VISIBLE);
          enterWeightEditter.setVisibility(View.VISIBLE);
        }



        enterWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterWeightEditter.getText().length() != 0 && enterWeightEditter.getText().toString().contains("[a-zA-Z]+") == false) {
                    weight = new Weight();
                    weight.weight = Double.parseDouble(String.format("%.2f", enterWeightEditter.getText().toString()));
                    db.weightDao().insert(weight);
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                thread.sleep(500);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            enterWeightButton.setVisibility(View.GONE);
                            enterWeightEditter.setVisibility(View.GONE);

                        }
                    });
                    thread.start();
                }
            }
        });





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

    public void firstTimeLoginActivity(){
        Intent intent = new Intent(this, FirstLogin.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
