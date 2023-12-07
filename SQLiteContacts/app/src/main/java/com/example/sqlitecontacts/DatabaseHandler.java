package com.example.sqlitecontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ContactsDB";
    private static final int VERSION = 1;
    private static final String CONTACTS_TABLE = "Contacts";
    SQLiteDatabase db;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQry = "CREATE TABLE " + CONTACTS_TABLE + "( id INTEGER PRIMARY KEY , name TEXT , phone TEXT , country TEXT )";
        db.execSQL(createQry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE);
        onCreate(db);
    }

    public long addContact(String name, String phone, String country) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("country", country);

        long result = db.insert(
                CONTACTS_TABLE,
                null,
                values
        );
        db.close();
        return result;
    }


    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        db = this.getWritableDatabase();
        String qry = "SELECT * FROM " + CONTACTS_TABLE;
        Cursor cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            do {
                Contact c = new Contact(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                contacts.add(c);
            } while (cursor.moveToNext());
        }
        return contacts;
    }

    public int deleteContact(int id) {

        db = this.getWritableDatabase();
        int result = db.delete(
                CONTACTS_TABLE,
                "id = ?",
                new String[]{""+id}
        );
        db.close();
        return result;
    }

    public int updateContact(Contact contact){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id" , contact.getId());
        values.put("name" , contact.getName());
        values.put("phone",contact.getPhone());
        values.put("country" , contact.getCountry());
        int result = db.update(
                CONTACTS_TABLE,
                values,
                "id=?",
                new String[]{""+contact.getId()}
        );
        db.close();
        return  result;
    }

}