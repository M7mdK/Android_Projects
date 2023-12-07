package com.example.sqlitecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button showButton , addButton , clearButton;
    EditText nameET, phoneET , countryET;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showButton = findViewById(R.id.showButton);
        addButton = findViewById(R.id.addButton);
        clearButton = findViewById(R.id.clearButton);
        nameET = findViewById(R.id.nameEditText);
        phoneET = findViewById(R.id.phoneEditText);
        countryET = findViewById(R.id.countryEditText);
        db = new DatabaseHandler(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String phone = phoneET.getText().toString();
                String country = countryET.getText().toString();
                long inserted = db.addContact(name , phone , country);
                if(inserted != -1)
                    Toast.makeText(MainActivity.this, "Content inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "insertion error", Toast.LENGTH_SHORT).show();
                nameET.setText("");
                phoneET.setText("");
                countryET.setText("");
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , AllContents.class);
                startActivity(intent);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.onUpgrade(db.getWritableDatabase() , 1 , 2);
                Toast.makeText(MainActivity.this, "Cleared All contacts", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
