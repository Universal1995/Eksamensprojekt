package com.example.eksamensprojekt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eksamensprojekt.databasecomp.Activity;
import com.example.eksamensprojekt.databasecomp.AppDatabase;

import java.util.Random;

public class Fragment1 extends Fragment {
    private Fragment1Listener listener;
    private EditText editText1AN;
    private EditText editText1week;
    private TextView ANTextView;
    private TextView weekTextView;

    private Button buttonOK;

    protected AppDatabase db;

    public interface Fragment1Listener{
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, container,false);
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

        db = AppDatabase.getAppDatabase(v.getContext());

        editText1AN = v.findViewById(R.id.EditTextFra1AN);
        editText1week= v.findViewById(R.id.EditTextFra1week);
        ANTextView = v.findViewById(R.id.ANTextview);
        weekTextView = v.findViewById(R.id.weekTextview);

        buttonOK = v.findViewById(R.id.buttonFra1);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText1AN.getText().length() != 0 && editText1week.getText().length() != 0){

                    Activity activity = new Activity();
                    activity.activityName = editText1AN.getText().toString();
                    activity.weekday = editText1week.getText().toString();
                    db.activityDao().insert(activity);
                    Toast.makeText(getContext(), "Aktivitet tilføjet", Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(getContext(), "Aktivitets navn kan ikke være tomt", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return v;
    }






}
