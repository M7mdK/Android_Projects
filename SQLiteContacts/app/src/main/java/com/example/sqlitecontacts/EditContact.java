package com.example.sqlitecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {

    EditText nameET , phoneET, countryET;
    Button saveChanges;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        nameET = findViewById(R.id.changeName);
        phoneET = findViewById(R.id.changePhone);
        countryET = findViewById(R.id.changeCountry);
        saveChanges = findViewById(R.id.saveChangesButton);

        db = new DatabaseHandler(this);
        Intent intent = getIntent();
        final int contId = intent.getIntExtra("contId" , -1);
        String contName = intent.getStringExtra("contName");
        String contPhone = intent.getStringExtra("contPhone");
        String contCountry = intent.getStringExtra("contCountry");
        nameET.setText(contName);
        phoneET.setText(contPhone);
        countryET.setText(contCountry);

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String phone = phoneET.getText().toString();
                String country = countryET.getText().toString();
                Contact contact = new Contact(contId , name , phone , country);
                if(db.updateContact(contact) == 1)
                    Toast.makeText(EditContact.this, "Contact Info Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EditContact.this, "Updating error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
