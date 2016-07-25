package com.example.android.trackhabit;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHandler db = new DBHandler(this);

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inserting Medication/Rows
        Log.d("Insert: ", "Inserting ..");
        db.addMedication(new Medication(1, "Vitamin C", "500mg"));
        db.addMedication(new Medication(2, "Vitamin D", "200mg"));
        db.addMedication(new Medication(3, "Ibuprofen", "200mg"));
        db.addMedication(new Medication(4, "Loratadine", "5mg"));

        // Reading all medications
        Log.d("Reading: ", "Reading all medications..");
        List<Medication> medications = db.getAllMedications();

        for (Medication medication : medications) {
            String log =
                    "Id: " + medication.getId()
                            + " ,Type: " + medication.getType()
                            + " ,Dosage: " + medication.getDosage();

            // Writing medications to log
            Log.d("Medication: : ", log);
        }

        //Get one medication
        Cursor single = db.getMedication(4);
        single.moveToFirst();
        String logSingle =
                "Id: " + single.getInt(0)
                        + ", Type: " + single.getString(1)
                        + ", Dosage: " + single.getString(2);
        Log.d("Single Medication: ", logSingle);

        //Get medication count
        int count = db.getMedicationsCount();
        Log.d("Medication count: ", String.valueOf(count));

        //Update medication
        db.updateMedication(new Medication(4, "Diphenhydramine", "25mg"));
        Cursor updateStr = db.getMedication(4);
        String updateLog =
                "Id: " + updateStr.getInt(0)
                        + ", Type: " + updateStr.getString(1)
                        + ", Dosage: " + updateStr.getString(2);
        Log.d("Updated medication", updateLog);

        //Delete one medication
        Medication removeMed = new Medication(2, "Vitamin D", "200mg");
        db.deleteMedication(removeMed);
        medications = db.getAllMedications();

        for (Medication medication : medications) {
            String log =
                    "Id: " + medication.getId()
                            + " ,Type: " + medication.getType()
                            + " ,Dosage: " + medication.getDosage();
            Log.d("New medication list: ", log);
        }

        //Delete all medications
        Log.d("Deleting:", "Deleting all medications...");
        db.deleteAll();

        // Reinserting Medication/Rows
        Log.d("Insert new meds: ", "Inserting new items...");
        db.addMedication(new Medication(1, "Vitamin A", "500mg"));
        db.addMedication(new Medication(2, "Vitamin E", "200mg"));
        db.addMedication(new Medication(3, "Aspirin", "200mg"));
        db.addMedication(new Medication(4, "Valium", "5mg"));

        // Reading all medications
        Log.d("Reading: ", "Reading all medications..");
        medications = db.getAllMedications();

        for (Medication medication : medications) {
            String log =
                    "Id: " + medication.getId()
                            + " ,Type: " + medication.getType()
                            + " ,Dosage: " + medication.getDosage();

            // Writing medications to log
            Log.d("Medication: : ", log);
        }

    }

    @Override
    protected void onDestroy() {
        //Delete entire Database
        Log.d("Deleting: ", "Deleting entire Database");
        db.close();
        db.deleteDatabase();
        super.onDestroy();
    }
}
