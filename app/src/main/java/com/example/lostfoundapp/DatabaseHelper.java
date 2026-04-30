package com.example.lostfoundapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostFound.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "items";

    private static final String COL_ID = "id";
    private static final String COL_TYPE = "type";
    private static final String COL_NAME = "name";
    private static final String COL_PHONE = "phone";
    private static final String COL_DESC = "description";
    private static final String COL_DATE = "date";
    private static final String COL_LOCATION = "location";
    private static final String COL_CATEGORY = "category";
    private static final String COL_IMAGE = "image";
    private static final String COL_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TYPE + " TEXT, "
                + COL_NAME + " TEXT, "
                + COL_PHONE + " TEXT, "
                + COL_DESC + " TEXT, "
                + COL_DATE + " TEXT, "
                + COL_LOCATION + " TEXT, "
                + COL_CATEGORY + " TEXT, "
                + COL_IMAGE + " TEXT, "
                + COL_TIMESTAMP + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertItem(String type, String name, String phone,
                              String desc, String date, String location,
                              String category, String image, String timestamp) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_TYPE, type);
        values.put(COL_NAME, name);
        values.put(COL_PHONE, phone);
        values.put(COL_DESC, desc);
        values.put(COL_DATE, date);
        values.put(COL_LOCATION, location);
        values.put(COL_CATEGORY, category);
        values.put(COL_IMAGE, image);
        values.put(COL_TIMESTAMP, timestamp);

        long result = db.insert(TABLE_NAME, null, values);

        return result != -1;
    }


    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY timestamp DESC", null);
    }


    public boolean deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}