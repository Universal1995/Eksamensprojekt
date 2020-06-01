package com.example.eksamensprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eksamensprojekt.databasecomp.Activity;
import com.example.eksamensprojekt.databasecomp.AppDatabase;
import com.example.eksamensprojekt.databasecomp.User;
import com.example.eksamensprojekt.databasecomp.Weight;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Statistics extends AppCompatActivity {
    private AppDatabase db;
    private User mUser;
    private Activity[] mActivity;
    private Weight[] mWeights;
    private String text;
    private DecimalFormat numberFormat = new DecimalFormat("#.000");
    //text for text objects

    // fix this shit tomoz,
    private GraphView graph;
    private LineGraphSeries<DataPoint> series;
    private TextView dateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //Links our Graphview to the XML object
        graph = findViewById(R.id.Graph);
        dateTextView = findViewById(R.id.activityanswer);


        //Layout
        db = AppDatabase.getAppDatabase(this);
        if(db.weightDao().countWeight() == 0) {
            Weight weight = new Weight();
            weight.weight = 86.0;
            db.weightDao().insert(weight);
            weight.weight = 85.0;
            db.weightDao().insert(weight);
            weight.weight = 86.0;
            db.weightDao().insert(weight);
            weight.weight = 84.0;
            db.weightDao().insert(weight);

            mWeights = db.weightDao().loadAllWeights();
            mUser = db.userDao().GetUser();
            mActivity = db.activityDao().loadAllActivities();
        }
        else {
            mWeights = db.weightDao().loadAllWeights();
            mUser = db.userDao().GetUser();
            mActivity = db.activityDao().loadAllActivities();
        }


        dateTextView.setText(new Date(mWeights[0].date).toString());

        initGraf();
        graph.addSeries(series);

//rendering x axis as dates
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
//cropping size to the values of the series for x values
        graph.getViewport().setMinX(series.getLowestValueX());
        graph.getViewport().setMaxX(series.getHighestValueX());
        graph.getViewport().setXAxisBoundsManual(true);
//cropping size to the values of the series for y values
        graph.getViewport().setMaxY(series.getHighestValueY());

        graph.getViewport().setMinY(series.getLowestValueY());
        graph.getViewport().setYAxisBoundsManual(true);






        //Buttons and text objects


        //shows initial weight
        TextView initWeight = findViewById(R.id.startweightanswer);
        text = String.valueOf(mUser.initWeight);
        initWeight.setText(text);
        // shows initial BMI
        TextView initBMI = findViewById(R.id.startBMIanswer);
        text = String.valueOf(numberFormat.format(mUser.initWeight / (mUser.height * mUser.height)));
        initBMI.setText(text);
        //shows total weight loss from start
        TextView WeightLoss = findViewById(R.id.weightlostanswer);
        text = String.valueOf(numberFormat.format(mUser.initWeight - mWeights[mWeights.length-1].weight));
        WeightLoss.setText(text);
        //shows current BMI
        TextView CurrBMI = findViewById(R.id.BMIanswer);
        text = String.valueOf(numberFormat.format(mWeights[mWeights.length-1].weight / (mUser.height * mUser.height)));
        CurrBMI.setText(text);
        //shows current weight
        TextView currWeight = findViewById(R.id.WeightShowCurrent);
        text = mWeights[mWeights.length-1].weight.toString();
        currWeight.setText(text);



        Button buttonBack = findViewById(R.id.button9);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });










    }

    public void initGraf() {
        DataPoint[] dataPoints = new DataPoint[mWeights.length];

        for (int i = 0; i < mWeights.length; i++) {
            dataPoints[i] = new DataPoint(new Date(mWeights[i].date), mWeights[i].weight);
        }

        series = new LineGraphSeries<>(dataPoints);

    }



}
