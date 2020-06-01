package com.example.eksamensprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eksamensprojekt.databasecomp.AppDatabase;
import com.example.eksamensprojekt.databasecomp.User;
import com.example.eksamensprojekt.databasecomp.Weight;

public class FirstLogin extends AppCompatActivity {

    private Button nextButton;
    private EditText initHeightEdit;
    private EditText initWeightEdit;
    private AppDatabase db;
    private User user;
    private Weight weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        nextButton = findViewById(R.id.firstLoginNextButton);
        initHeightEdit = findViewById(R.id.initHeightEditText);
        initWeightEdit = findViewById(R.id.initWeightEditText);
        db = AppDatabase.getAppDatabase(this);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(initHeightEdit.getText().toString().matches("\\d+.\\d+") && initWeightEdit.getText().toString().matches("\\d+.\\d+") ){

                    if(initWeightEdit.getText().length() != 0 && initWeightEdit.getText().length() < 7 && initHeightEdit.getText().length() != 0 && initHeightEdit.getText().length() < 5){
                        if(Double.parseDouble(initWeightEdit.getText().toString()) < 200 && Double.parseDouble(initHeightEdit.getText().toString()) < 2.2 ) {
                            user = new User();
                            weight = new Weight();
                            user.height = Double.parseDouble(initHeightEdit.getText().toString());
                            user.initWeight = Double.parseDouble(initWeightEdit.getText().toString());
                            db.userDao().insert(user);
                            weight.weight = Double.parseDouble(initWeightEdit.getText().toString());
                            db.weightDao().insert(weight);
                            Toast.makeText(getApplicationContext(), "Velkommen", Toast.LENGTH_LONG).show();

                            finish();
                        }else{

                            Toast.makeText(getApplicationContext(), "du kan ikke veje over 200 eller være over 2.2 m høj", Toast.LENGTH_LONG).show();
                        }
                    }else {

                        Toast.makeText(getApplicationContext(), "højde skal være mellem 1 og 3 tal, vægt skal være mellem 1 og 5 tal med decimaler", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Vægt og højde kan kun indeholde tal", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
