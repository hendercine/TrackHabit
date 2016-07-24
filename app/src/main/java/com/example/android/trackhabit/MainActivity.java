package com.example.android.trackhabit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);

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
            String log = "Id: " + medication.getId() + " ,Type: " + medication.getType() + " ,Dosage: " + medication.getDosage();

            // Writing medications to log
            Log.d("Medication: : ", log);
        }
        //Get one medication
        Medication single = db.getMedication(4);
        Log.d("Single Medication: ", String.valueOf(single));

        //Get medication count
        int count = db.getMedicationsCount();
        Log.d("Medication count: ", String.valueOf(count));

        //Update medication
        int update = db.updateMedication(new Medication(4, "Diphenhydramine", "25mg"));
        Log.d("Updated medication", String.valueOf(update));

        //Delete medication
        Medication removeMed = new Medication(2, "Vitamin D", "200mg");
        db.deleteMedication(removeMed);
        medications = db.getAllMedications();

        for (Medication medication : medications) {
            String log = "Id: " + medication.getId() + " ,Type: " + medication.getType() + " ,Dosage: " + medication.getDosage();

            Log.d("New medication list: ", log);
        }
    }
}
