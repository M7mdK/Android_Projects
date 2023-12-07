package com.example.productslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void productsClicked(View view) {
        Intent intent = new Intent(this , ProductsList.class);
        startActivity(intent);
    }

    public void historyClicked(View view) {
        Intent intent = new Intent(this , History.class);
        startActivity(intent);
    }
}
