package com.example.caloryat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Caloriyat_A2 extends AppCompatActivity {

    EditText weightEditText;
    EditText heightEditText;
    Button howFitButton;

    RadioGroup radioGroup;
    EditText ageEditText;
    Button howMuchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloriyat__a2);

        weightEditText = (EditText) findViewById(R.id.weightEditText);
        heightEditText = (EditText) findViewById(R.id.heightEditText);
        howFitButton =(Button) findViewById(R.id.howFitButton);

        howFitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int BMI = getBMI();
                if(BMI >= 0){
                    String bmiC = getBMICatagory();
                    Toast.makeText(Caloriyat_A2.this, "Your BMI is: " + BMI + "\n that's " + bmiC, Toast.LENGTH_LONG).show();
                }
            }
        });
        radioGroup = findViewById(R.id.radioGroup);
        ageEditText = findViewById(R.id.ageEditText);
        howMuchButton = findViewById(R.id.howMuchButton);

        howMuchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioChecked = radioGroup.getCheckedRadioButtonId();
                String sAge = ageEditText.getText().toString();
                double w = getWeight();
                double h = getHeight();
                if(radioChecked == -1)
                    Toast.makeText(Caloriyat_A2.this, "Specify your Gender", Toast.LENGTH_SHORT).show();
                else if(sAge.equals(""))
                    Toast.makeText(Caloriyat_A2.this, "Enter your Age", Toast.LENGTH_SHORT).show();
                else if(w>0 && h>0){
                    int calories = 0;
                   if(radioChecked == R.id.maleRadioButton)
                       calories = (int) (665 + 6.3*toPounds(w) + 12.9*toInches(h) - (6.8*24));
                   else if(radioChecked == R.id.femaleRadioButton){
                       int age = Integer.parseInt(sAge);
                       if(age < 24){
                           calories = (int) (655 + 4.3*toPounds(w) + 4.7*toInches(h) - (4.7*24));
                       }
                       else{
                           calories = (int) (455 + 4.3*toPounds(w) + 4.7*toInches(h) - (4.7*24));
                       }
                   }
                    Intent intent = new Intent(Caloriyat_A2.this , Caloriyat_A3.class);
                    intent.putExtra("Calories" , calories);
                    startActivity(intent);
                }
            }
        });
    }

    public double getWeight(){
        String sw = weightEditText.getText().toString();
        if(sw.equals("")){
            Toast.makeText(this, "Enter your weight", Toast.LENGTH_SHORT).show();
            return -1;
        }
        else
            return Double.parseDouble(sw);
    }
    public double getHeight(){
        String sh = heightEditText.getText().toString();
        if(sh.equals("")){
            Toast.makeText(this, "Enter your height", Toast.LENGTH_SHORT).show();
            return -1;
        }
        else
            return Double.parseDouble(sh);
    }

    public int getBMI(){
        int BMI = -1;
        double w = getWeight();
        double h = 0.01 * getHeight();
        if(w>0 && h>0)
             BMI =(int) (w/(h*h));
        return BMI;
    }
    public String getBMICatagory(){
        String s = null;
        int BMI = getBMI();
        if(BMI >= 0){
            if(BMI ==0)
                s = "impossible!";
            else if(BMI <= 18)
                s = "UnderWeight";
            else if(BMI >= 19 && BMI <= 24)
                s = "Normal Weight";
            else if(BMI >= 25 && BMI <=29)
                s="OverWeight";
            else if(BMI >= 30)
                s = "Obesity";
        }
        return s;
    }
    public double toPounds(double weight_kg){
        return weight_kg * 2.2;
    }
    public double toInches(double height_cm){
        return height_cm * 0.39;
    }
}