package com.example.eksamensprojekt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eksamensprojekt.databasecomp.Activity;
import com.example.eksamensprojekt.databasecomp.AppDatabase;
import com.example.eksamensprojekt.databasecomp.Weight;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private AppDatabase db;
    private EditText enterWeightEditter;
    private Button enterWeightButton;
    private Weight weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterWeightEditter = findViewById(R.id.enterWeightEditText);
        enterWeightButton = findViewById(R.id.submitButton);

        db = AppDatabase.getAppDatabase(this);
        if(db.userDao().countUsers() == 0){

            firstTimeLoginActivity();
        }
        if(db.userDao().countUsers() == 1 && db.activityDao().countActivities() == 0){
            nextViewActivities();
        }








        enterWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight = new Weight();
                if (!new SimpleDateFormat("dd/MM/yy").format(db.weightDao().getLastWeightDate().date).matches(new SimpleDateFormat("dd/MM/yy").format(new Date().getTime())))  {
                    if (enterWeightEditter.getText().length() != 0 && enterWeightEditter.getText().toString().matches("\\d+.\\d+") && enterWeightEditter.getText().length() < 7) {

                        weight = new Weight();
                        weight.weight = Double.parseDouble(String.format("%.2f", enterWeightEditter.getText().toString()));
                        db.weightDao().insert(weight);

                        Toast.makeText(getApplicationContext(), "shit", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Daglige vægt er allerede indtastet", Toast.LENGTH_SHORT).show();
                }
            }
        });





        adapter = new RecyclerViewAdapter(this);

        Button buttonStats = findViewById(R.id.button);


        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextViewStats();
            }
        });

        Button buttonActivites = findViewById(R.id.button7);
        buttonActivites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextViewActivities();

            }
        });

        initRecyclerView();
    }


    public void nextViewStats(){
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }

    public void nextViewActivities(){
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

    }
}
