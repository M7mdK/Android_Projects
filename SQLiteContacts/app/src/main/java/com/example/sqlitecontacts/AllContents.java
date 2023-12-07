package com.example.sqlitecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllContents extends AppCompatActivity {

    DatabaseHandler db;
    ListView listViewContacts;
    ArrayList<Contact> arrayListContacts;
    ArrayAdapter<Contact> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contents);

        db = new DatabaseHandler(this);
        listViewContacts = findViewById(R.id.listViewContacts);
        arrayListContacts = db.getAllContacts();
        adapter = new ArrayAdapter<Contact>(this , android.R.layout.simple_list_item_1 , arrayListContacts);
        listViewContacts.setAdapter(adapter);

        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext() , EditContact.class);
                intent.putExtra("contId" , arrayListContacts.get(position).getId());
                intent.putExtra("contName" , arrayListContacts.get(position).getName());
                intent.putExtra("contPhone" , arrayListContacts.get(position).getPhone());
                intent.putExtra("contCountry" , arrayListContacts.get(position).getCountry());
                startActivity(intent);
            }
        });

        listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                int contId = arrayListContacts.get(position).getId();
                if(db.deleteContact(contId) == 1){
                    arrayListContacts.remove(position);
                    listViewContacts.setAdapter(adapter);
                    Toast.makeText(AllContents.this, "Contact deleted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(AllContents.this, "Deletion Error", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }
}
