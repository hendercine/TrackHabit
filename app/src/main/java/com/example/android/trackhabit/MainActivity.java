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
            String log =
                    "Id: " + medication.getId()
                            + " ,Type: " + medication.getType()
                            + " ,Dosage: " + medication.getDosage();

            // Writing medications to log
            Log.d("Medication: : ", log);
        }

        //Get one medication
        String single = db.getMedication(4).toString();
        String logSingle =
                "Id: " + single.indexOf("0")
                        + ", Type: " + single.indexOf("1")
                        + ", Dosage: " + single.indexOf("2");
        Log.d("Single Medication: ", logSingle);

        //Get medication count
        int count = db.getMedicationsCount();
        Log.d("Medication count: ", String.valueOf(count));

        //Update medication
        int update = db.updateMedication(new Medication(4, "Diphenhydramine", "25mg"));
        String updateStr = String.valueOf(db.getMedication(update));
        String updateLog =
                "Id: " + updateStr.indexOf("0")
                        + ", Type: " + updateStr.indexOf("1")
                        + ", Dosage: " + updateStr.indexOf("2");
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
    }
}
