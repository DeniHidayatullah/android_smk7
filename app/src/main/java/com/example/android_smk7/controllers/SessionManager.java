package com.example.android_smk7.controllers;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
    // Shared Preferences
    private static SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "SMKN 7 Jember";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Make variable public to access from outside class
    public static final String KEY_KODE_SISWA = "kode_siswa";
    public static final String KEY_NIS = "nis";
    public static final String KEY_NISN = "nisn";
    public static final String KEY_NAMA_SISWA = "nama_siswa";
    public static final String KEY_JENIS_KELAMIN = "jenis_kelamin";
    public static final String KEY_TEMPAT_LAHIR = "tempat_lahir";
    public static final String KEY_TANGGAL_LAHIR = "tanggal_lahir";
    public static final String KEY_AGAMA = "agama";
    public static final String KEY_KECAMATAN = "kecamatan";
    public static final String KEY_HP= "hp";
    public static final String KEY_FOTO = "foto";
    public static final String KEY_ANGKATAN = "angkatan";
    public static final String KEY_KODE_KELAS = "kode_kelas";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAMA_AYAH = "nama_ayah";
    public static final String KEY_PEKERJAAN_AYAH = "pekerjaan_ayah";
    public static final String KEY_NAMA_IBU = "nama_ibu";
    public static final String KEY_PEKERJAAN_IBU = "pekerjaan_ibu";
    public static final String KEY_NAMA_WALI = "nama_wali";
    public static final String KEY_PEKERJAAN_WALI = "pekerjaan_wali";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    /**
     * Create login session
     * */
    public void createSession(String kode_siswa, String nis, String nisn, String nama_siswa, String jenis_kelamin, String tempat_lahir,
                              String tanggal_lahir, String agama, String kecamatan,
                              String hp, String foto, String angkatan, String kode_kelas, String password, String nama_ayah, String pekerjaan_ayah,
                              String nama_ibu, String pekerjaan_ibu, String nama_wali, String pekerjaan_wali){

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing index_pasien in pref
        editor.putString(KEY_KODE_SISWA, kode_siswa);

        // Storing data profil siswa in pref
        editor.putString(KEY_NIS , nis);
        editor.putString(KEY_NISN , nisn);
        editor.putString(KEY_NAMA_SISWA , nama_siswa);
        editor.putString(KEY_JENIS_KELAMIN , jenis_kelamin);
        editor.putString(KEY_TEMPAT_LAHIR , tempat_lahir);
        editor.putString(KEY_TANGGAL_LAHIR , tanggal_lahir);
        editor.putString(KEY_AGAMA , agama);
        editor.putString(KEY_KECAMATAN , kecamatan);
        editor.putString(KEY_HP, hp);
        editor.putString(KEY_FOTO , foto);
        editor.putString(KEY_ANGKATAN , angkatan);
        editor.putString(KEY_KODE_KELAS , kode_kelas);
        editor.putString(KEY_PASSWORD , password);
        editor.putString(KEY_NAMA_AYAH , nama_ayah);
        editor.putString(KEY_PEKERJAAN_AYAH , pekerjaan_ayah);
        editor.putString(KEY_NAMA_IBU , nama_ibu);
        editor.putString(KEY_PEKERJAAN_IBU , pekerjaan_ibu);
        editor.putString(KEY_NAMA_WALI , nama_wali);
        editor.putString(KEY_PEKERJAAN_WALI , pekerjaan_wali);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // index pasien
        user.put(KEY_KODE_SISWA, pref.getString(KEY_KODE_SISWA, null));

        // data profil pasien
        user.put(KEY_NIS , pref.getString(KEY_NIS , null));
        user.put(KEY_NISN , pref.getString(KEY_NISN , null));
        user.put(KEY_NAMA_SISWA , pref.getString(KEY_NAMA_SISWA , null));
        user.put(KEY_JENIS_KELAMIN , pref.getString(KEY_JENIS_KELAMIN, null));
        user.put(KEY_TEMPAT_LAHIR , pref.getString(KEY_TEMPAT_LAHIR, null));
        user.put(KEY_TANGGAL_LAHIR , pref.getString(KEY_TANGGAL_LAHIR, null));
        user.put(KEY_AGAMA , pref.getString(KEY_AGAMA, null));
        user.put(KEY_KECAMATAN , pref.getString(KEY_KECAMATAN, null));
        user.put(KEY_HP , pref.getString(KEY_HP, null));
        user.put(KEY_FOTO , pref.getString(KEY_FOTO, null));
        user.put(KEY_ANGKATAN , pref.getString(KEY_ANGKATAN, null));
        user.put(KEY_KODE_KELAS , pref.getString(KEY_KODE_KELAS, null));
        user.put(KEY_PASSWORD , pref.getString(KEY_PASSWORD, null));
        user.put(KEY_NAMA_AYAH , pref.getString(KEY_NAMA_AYAH, null));
        user.put(KEY_PEKERJAAN_AYAH , pref.getString(KEY_PEKERJAAN_AYAH, null));
        user.put(KEY_NAMA_IBU , pref.getString(KEY_NAMA_IBU, null));
        user.put(KEY_PEKERJAAN_IBU , pref.getString(KEY_PEKERJAAN_IBU, null));
        user.put(KEY_NAMA_WALI , pref.getString(KEY_NAMA_WALI, null));
        user.put(KEY_PEKERJAAN_WALI  , pref.getString(KEY_PEKERJAAN_WALI, null));


        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }



}