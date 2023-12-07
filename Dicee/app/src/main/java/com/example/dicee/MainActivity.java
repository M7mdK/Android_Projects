package com.example.dicee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button rollButton = (Button) findViewById(R.id.rollButton);

        final ImageView leftDice = (ImageView) findViewById(R.id.im_leftDice);
        final ImageView rightDice = (ImageView) findViewById(R.id.im_rightDice);

        final int[] diceArray = {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };

        rollButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Random RNG = new Random();
                        int rn = RNG.nextInt(6);
                        leftDice.setImageResource(diceArray[rn]);
                        rn = RNG.nextInt(6);
                        rightDice.setImageResource(diceArray[rn]);
                    }
                }
        );
    }
}
