package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b[][];
    Button reset;
    boolean player1Turn;
    int roundCount; //if it is 9 and there is no winner yet, so it is draw
    int w[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = new Button[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                String s = "b" + i + j;
                b[i][j] = (Button) findViewById(getResources().getIdentifier(s, "id", getPackageName()));
            }
        reset = (Button) findViewById(R.id.resert_button);
        w = new int[3][3];
        Reset();

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                b[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!((Button) v).getText().toString().equals(""))//Button already filled
                            return;
                        if (player1Turn) {
                            ((Button) v).setTextColor(getResources().getColor(R.color.xBlue));
                            ((Button) v).setText("X");
                            w[finalI][finalJ] = 1;
                        } else {
                            ((Button) v).setTextColor(getResources().getColor(R.color.oRed));
                            ((Button) v).setText("O");
                            w[finalI][finalJ] = 2;
                        }
                        roundCount++;
                        player1Turn = !player1Turn;

                        if (isWinner(1)) {
                            showGameOverDialog("Player 1 Wins!");
                        } else if (isWinner(2)) {
                            showGameOverDialog("Player 2 Wins!");
                        } else if (roundCount == 9) {
                            showGameOverDialog("Draw!");
                        }
                    }
                });
            }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });
    }

    public boolean isWinner(int playerNumber) {
        for (int i = 0; i < 3; i++)
            if (w[i][0] == playerNumber && w[i][0] == w[i][1] && w[i][1] == w[i][2])//Horizontal win
                return true;

        for (int j = 0; j < 3; j++)
            if (w[0][j] == playerNumber && w[0][j] == w[1][j] && w[1][j] == w[2][j])//Vertical win
                return true;

        if (w[0][0] == playerNumber && w[0][0] == w[1][1] && w[1][1] == w[2][2])//first diagonal
            return true;

        if (w[0][2] == playerNumber && w[0][2] == w[1][1] && w[1][1] == w[2][0])//second diagonal
            return true;

        return false;
    }

    public void Reset() {
        player1Turn = true;
        roundCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                w[i][j] = 0;
                b[i][j].setText("");
            }
        }
        Toast.makeText(getApplicationContext(), "New Game Started!", Toast.LENGTH_SHORT).show();

    }

    public void showGameOverDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Game Over!")
                .setMessage(message)
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Reset();
                    }
                })
                .setNeutralButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }
}
