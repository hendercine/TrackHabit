package com.example.android.trackhabit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context) {
        super(context, "medsinfo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + MedContract.MedEntry.TABLE_MEDS+ "("
                + MedContract.MedEntry.KEY_ID + " INTEGER PRIMARY KEY,"
                + MedContract.MedEntry.KEY_TYPE + " TEXT,"
                + MedContract.MedEntry.KEY_DOSAGE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MedContract.MedEntry.TABLE_MEDS);
    // Creating tables again
        onCreate(db);
    }

    // Adding new medication
    public void addMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MedContract.MedEntry.KEY_TYPE, medication.getType());
        values.put(MedContract.MedEntry.KEY_DOSAGE, medication.getDosage());
    // Inserting Row
        db.insert(MedContract.MedEntry.TABLE_MEDS, null, values);
        db.close(); // Closing database connection
    }

    // Getting one medication
    public Medication getMedication(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(MedContract.MedEntry.TABLE_MEDS,
                new String[]{
                        MedContract.MedEntry.KEY_ID,
                        MedContract.MedEntry.KEY_TYPE,
                        MedContract.MedEntry.KEY_DOSAGE
                },
                MedContract.MedEntry.KEY_ID + "=?",
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
        String selectQuery = "SELECT * FROM " + MedContract.MedEntry.TABLE_MEDS;
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
        String countQuery = "SELECT * FROM " + MedContract.MedEntry.TABLE_MEDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
    // return count
        return count;
    }

    // Updating a medication
    public int updateMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MedContract.MedEntry.KEY_TYPE, medication.getType());
        values.put(MedContract.MedEntry.KEY_DOSAGE, medication.getDosage());
    // updating row
        return db.update(MedContract.MedEntry.TABLE_MEDS,
                values,
                MedContract.MedEntry.KEY_ID + " = ?",
                new String[]{String.valueOf(medication.getId())});
    }

    // Deleting a medication
    public void deleteMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MedContract.MedEntry.TABLE_MEDS, MedContract.MedEntry.KEY_ID + " = ?",
                new String[]{String.valueOf(medication.getId())});
        db.close();
    }
}
