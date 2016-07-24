package com.example.android.trackhabit;

/**
 * Created by Hendercine on 7/23/16.
 */
public class Medication {
    private int id;
    private String type;
    private String dosage;

    public Medication() {
    }

    public Medication(int id, String type, String dosage) {
        this.id = id;
        this.type = type;
        this.dosage = dosage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getId() {
        return id;
    }

    public String getDosage() {
        return dosage;
    }

    public String getType() {
        return type;
    }
}

