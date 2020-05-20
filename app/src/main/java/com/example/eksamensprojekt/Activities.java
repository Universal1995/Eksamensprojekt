package com.example.eksamensprojekt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activities extends AppCompatActivity implements Fragment1.Fragment1Listener {
    private Fragment1 fragment1;
    private boolean isOn;
    private TextView headerA;
    private FrameLayout fragmentRemover;
    private Button buttonNewActivities;
    private Button buttonBack;

    private ArrayList<String> mActivityNames = new ArrayList<>();
    private ArrayList<String> mWeekdays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        buttonBack = findViewById(R.id.button5);
        buttonNewActivities = findViewById(R.id.button6);
        headerA = findViewById(R.id.headerActivities);
        fragmentRemover = findViewById(R.id.fragmentRemover);

        fragment1 = new Fragment1();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    //.replace(R.id.main_fragment, new WhiteFragment())
                    //.addToBackStack(null)
                    .commit();
        }



        initOnClickMethods();
        initActivityList();
        int i;
    }



    public void initOnClickMethods(){

        headerA.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
            buttonNewActivities.setText("New Activity");
            isOn = false;
        }

        });

        buttonNewActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOn == false) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container1,fragment1).commit();

                    isOn = true;
                    buttonNewActivities.setText("Close");
                }
                else if (isOn){
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    isOn = false;
                    buttonNewActivities.setText("New Activity");
                }

            }
        });

        fragmentRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                buttonNewActivities.setText("New Activity");
                isOn = false;
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void initActivityList(){

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");

        mActivityNames.add("ddd");
        mWeekdays.add("Canada");



        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewActivities);
        activitiesRecyclerViewAdapter adapter = new activitiesRecyclerViewAdapter(mActivityNames,mWeekdays,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
