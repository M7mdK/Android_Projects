package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText firstNb;
    EditText secondNb;
    Button computeButton;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        firstNb = findViewById(R.id.firstNb);
        secondNb = findViewById(R.id.secondNb);
        computeButton = findViewById(R.id.computeButton);
        result = findViewById(R.id.resultTextView);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = firstNb.getText().toString();
                String s = secondNb.getText().toString();
                if(f.equals("") || s.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter both numbers", Toast.LENGTH_SHORT).show();
                    return;
                }
                double r = 0;
                double fn = Double.parseDouble(f);
                double sn = Double.parseDouble(s);
                int checkedRadio = radioGroup.getCheckedRadioButtonId();
                if(checkedRadio == -1){
                    Toast.makeText(MainActivity.this, "Choose operation", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(checkedRadio == R.id.addRadioButton){
                    r = fn + sn;
                }
                else if(checkedRadio == R.id.subtractRadioButton){
                    r = fn - sn;
                }
                else if(checkedRadio == R.id.multipyRadioButton){
                    r = fn * sn;
                }
                else if(checkedRadio == R.id.divideRadioButton){
                    r = fn/sn;
                }
                result.setText("Result: " + r);
            }
        });
    }
}
