package com.example.biggernumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity<override> extends AppCompatActivity {

    Button leftB;
    Button rightB;
    TextView points;
    int score=0;
    int rnL;
    int rnR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftB =(Button)  findViewById(R.id.leftButton);
        rightB = (Button) findViewById(R.id.rightButton);
        points = (TextView) findViewById(R.id.points_TextView);

        if(savedInstanceState !=null){
            score = savedInstanceState.getInt("scoreKey");
        }

        update();

        leftB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rnL>=rnR){
                    Toast.makeText(getApplicationContext() , R.string.correctToast , Toast.LENGTH_SHORT).show();
                    score++;
                }
                else{
                    Toast.makeText(getApplicationContext() , R.string.incorrectToast , Toast.LENGTH_SHORT).show();
                    score--;
                }
                update();
            }
        });

        rightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rnR>=rnL){
                    Toast.makeText(getApplicationContext() , R.string.correctToast , Toast.LENGTH_SHORT).show();
                    score++;
                }
                else{
                    Toast.makeText(getApplicationContext() , R.string.incorrectToast , Toast.LENGTH_SHORT).show();
                    score--;
                }
                update();
            }
        });
    }

    public void update(){
        Random RNG = new Random();
        rnL = RNG.nextInt(100);
        rnR = RNG.nextInt(100);
        leftB.setText(" " + rnL);
        rightB.setText(" " + rnR);
        points.setText("Points: " + score);
    }


    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("scoreKey" , score);
    }


}
