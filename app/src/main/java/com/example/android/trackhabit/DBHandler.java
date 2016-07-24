package com.example.android.trackhabit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "medsInfo";
    // Table name
    private static final String TABLE_MEDS = "meds";
    // Medications Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DOSAGE = "dosage";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MEDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE + " TEXT,"
                + KEY_DOSAGE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDS);
// Creating tables again
        onCreate(db);
    }

    // Adding new medication
    public void addMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, medication.getType()); // Medication Name
        values.put(KEY_DOSAGE, medication.getDosage()); // Dosage amount
// Inserting Row
        db.insert(TABLE_MEDS, null, values);
        db.close(); // Closing database connection
    }

    // Getting one medication
    public Medication getMedication(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEDS, new String[]{KEY_ID,
                        KEY_TYPE, KEY_DOSAGE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Medication contact = new Medication(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
// return medication
        return contact;
    }

    // Getting All Medications
    public List<Medication> getAllMedications() {
        List<Medication> medicationList = new ArrayList<Medication>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_MEDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Medication medication = new Medication();
                medication.setId(Integer.parseInt(cursor.getString(0)));
                medication.setType(cursor.getString(1));
                medication.setDosage(cursor.getString(2));
// Adding contact to list
                medicationList.add(medication);
            } while (cursor.moveToNext());
        }
// return contact list
        return medicationList;
    }

    // Getting medications Count
    public int getMedicationsCount() {
        String countQuery = "SELECT * FROM " + TABLE_MEDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
// return count
        return cursor.getCount();
    }

    // Updating a medication
    public int updateMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, medication.getType());
        values.put(KEY_DOSAGE, medication.getDosage());
// updating row
        return db.update(TABLE_MEDS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(medication.getId())});
    }

    // Deleting a medication
    public void deleteMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDS, KEY_ID + " = ?",
                new String[]{String.valueOf(medication.getId())});
        db.close();
    }
}
