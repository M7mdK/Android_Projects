package com.example.productslist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductsList extends AppCompatActivity{

    private static final String FILE_NAME = "producats.txt";

    Toolbar  toolbarP;
    static ListView productsListView;
    static ArrayList<Product> productsArrayList;
    static ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        toolbarP = (Toolbar) findViewById(R.id.toolbarP) ;
        toolbarP.setTitle("Products List");
        setSupportActionBar(toolbarP);

        productsListView = (ListView) findViewById(R.id.productsList);
        productsArrayList = new ArrayList<Product>();
        adapter = new ArrayAdapter<Product>(this , android.R.layout.simple_list_item_1 , productsArrayList);
        loadProducts();
        productsListView.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_list_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item){
        if(R.id.addProduct == item.getItemId()){
            Intent intent = new Intent(this , AddProduct.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadProducts(){
        Product p;
        String name , qty , price;
        try {
            Scanner scan = new Scanner(openFileInput(FILE_NAME));
            while(scan.hasNextLine()){
                name = scan.nextLine();
                qty = scan.nextLine();
                price = scan.nextLine();
                p = new Product(name , qty, price);
                productsArrayList.add(p);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void saveProducts(){
        try {
            PrintStream ps = new PrintStream(openFileOutput(FILE_NAME , MODE_APPEND));
            for(int i=0 ; i<productsArrayList.size() ; i++)
                ps.println(productsArrayList.get(i).toString());
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void deleteProduct(){
        //Use it in longPress and in menu
        //Must save in SH.pref then load deleted items in History
    }
}
