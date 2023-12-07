package com.example.productslist;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;

import static com.example.productslist.ProductsList.adapter;
import static com.example.productslist.ProductsList.productsArrayList;
import static com.example.productslist.ProductsList.productsListView;

public class AddProduct extends AppCompatActivity{

    private static final String FILE_NAME = "producats.txt";

    Button addButton;
    EditText Name;
    EditText Quantity;
    EditText Price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        addButton = (Button) findViewById(R.id.addButton);
        Name = (EditText) findViewById(R.id.editTextName);
        Quantity = (EditText) findViewById(R.id.editTextQuantity);
        Price = (EditText) findViewById(R.id.editTextPrice);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String quantity = Quantity.getText().toString();
                String price = Price.getText().toString();
                if(name.equals(""))
                    Toast.makeText(AddProduct.this, "Enter Product Name", Toast.LENGTH_SHORT).show();
                else if(quantity.equals(""))
                    Toast.makeText(AddProduct.this, "Enter Product Quantity", Toast.LENGTH_SHORT).show();
                else if(price.equals(""))
                    Toast.makeText(AddProduct.this, "Enter Product Price", Toast.LENGTH_SHORT).show();
                else{
                    Product p = new Product(name , quantity , price);
                    addProduct(p);
                    Intent intent = new Intent(AddProduct.this , ProductsList.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void addProduct(Product p){
        productsArrayList.add(p);
        productsListView.setAdapter(adapter);
    }

}
