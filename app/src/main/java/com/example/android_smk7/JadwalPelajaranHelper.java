package com.example.android_smk7;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import static android.content.ContentValues.TAG;



public class JadwalPelajaranHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "jadwalpelajaran";
    private static final String DATABASE_NAME = "final_project.db";
    private static final String KEY_ID = "ID";
    private static final String COLUMNS_ONE = "MAPEL";
    private static final String COLUMNS_TWO = "GURU";
    private static final String COLUMNS_THREE = "HARI";
    private static final String COLUMNS_FOUR = "JAMMULAI";
    private static final String COLUMNS_FIVE = "JAMSELESAI";
    private static final String COLUMNS_SIX = "RUANGAN";

    SQLiteDatabase mReadableDB;

    public JadwalPelajaranHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE jadwalpelajaran (ID INTEGER PRIMARY KEY AUTOINCREMENT, MAPEL TEXT NOT NULL, GURU TEXT NOT NULL, HARI TEXT NOT NULL, JAMMULAI TEXT NOT NULL, JAMSELESAI TEXT NOT NULL, RUANGAN TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<JadwalPelajaran> jadwalList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<JadwalPelajaran> jadwalLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        JadwalPelajaran entry;

        if (cursor.moveToFirst()) {
            do {
                entry = new JadwalPelajaran();

                entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                entry.setHari(cursor.getString(cursor.getColumnIndex(COLUMNS_TWO)));
                entry.setJamMulai(cursor.getString(cursor.getColumnIndex(COLUMNS_THREE)));
                entry.setJadwalPelajaran(cursor.getString(cursor.getColumnIndex(COLUMNS_ONE)));
                entry.setRuangan(cursor.getString(cursor.getColumnIndex(COLUMNS_FOUR)));

                jadwalLinkedList.add(entry);
            } while (cursor.moveToNext());
        }
        return jadwalLinkedList;
    }

    public JadwalPelajaran query(int position) {
        String query = "SELECT  * FROM " + TABLE_NAME +
                " ORDER BY " + KEY_ID + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        JadwalPelajaran entry = new JadwalPelajaran();

        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setHari(cursor.getString(cursor.getColumnIndex(COLUMNS_TWO)));
            entry.setJamMulai(cursor.getString(cursor.getColumnIndex(COLUMNS_THREE)));
            entry.setJadwalPelajaran(cursor.getString(cursor.getColumnIndex(COLUMNS_ONE)));
            entry.setRuangan(cursor.getString(cursor.getColumnIndex(COLUMNS_FOUR)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;
        }
    }

    public JadwalPelajaran getOne(int position) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + KEY_ID + " = " + position;

        Cursor cursor = null;
        JadwalPelajaran entry = new JadwalPelajaran();

        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setJadwalPelajaran(cursor.getString(cursor.getColumnIndex(COLUMNS_ONE)));
            entry.setHari(cursor.getString(cursor.getColumnIndex(COLUMNS_TWO)));
            entry.setJamMulai(cursor.getString(cursor.getColumnIndex(COLUMNS_THREE)));
            entry.setRuangan(cursor.getString(cursor.getColumnIndex(COLUMNS_FOUR)));
            entry.setNamaGuru(cursor.getString(cursor.getColumnIndex(COLUMNS_FIVE)));
            entry.setJamSelesai(cursor.getString(cursor.getColumnIndex(COLUMNS_SIX)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;
        }
    }

    public long count() {
        if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
        return DatabaseUtils.queryNumEntries(mReadableDB, TABLE_NAME);
    }



}
