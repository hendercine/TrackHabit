package com.example.android.trackhabit;

import android.provider.BaseColumns;

public class MedContract {
    //Database Path
    public static final String DATABASE_PATH = "/data/data/com.example.android.trackhabit/databases/";
    // Database Name
    public static final String DATABASE_NAME = "medsInfo";
    // Database Version
    public static final int DATABASE_VERSION = 1;

    public class MedEntry implements BaseColumns {
        // Table name
        public static final String TABLE_MEDS = "meds";
        // Medications Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_TYPE = "type";
        public static final String KEY_DOSAGE = "dosage";
    }
}
