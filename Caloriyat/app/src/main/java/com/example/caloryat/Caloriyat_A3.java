package com.example.caloryat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Caloriyat_A3 extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloriyat__a3);

        result = (TextView) findViewById(R.id.resultTextView);
        Intent intent = getIntent();
        int r = intent.getIntExtra("Calories" , 0);
        result.setText("You need to eat " + r + " calories per day");
    }
}
