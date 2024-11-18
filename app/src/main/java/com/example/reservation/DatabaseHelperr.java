package com.example.reservation;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperr extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DentistApp.db";
    private static final int DATABASE_VERSION = 2; // Incremented version number

    // Appointment Table
    private static final String TABLE_APPOINTMENTS = "appointments";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_USER_ID = "userId";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";

    public DatabaseHelperr(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Appointments table
        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_APPOINTMENTS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_TIME + " TEXT, "
                + COLUMN_USER_ID + " TEXT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PHONE + " TEXT)";
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);
        onCreate(db);
    }

    // Method to add an appointment
    public boolean addAppointment(String date, String time, String userId, String name, String phone) {
        if (isTimeReserved(date, time)) {
            return false; // Time slot already reserved
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        long result = db.insert(TABLE_APPOINTMENTS, null, values);
        db.close();
        return result != -1;
    }

    // Check if a time slot is reserved
    public boolean isTimeReserved(String date, String time) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_APPOINTMENTS + " WHERE " + COLUMN_DATE + "=? AND " + COLUMN_TIME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{date, time});
        boolean isReserved = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isReserved;
    }
    public Cursor getAllReservations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_APPOINTMENTS, null, null, null, null, null, null);
    }
}
