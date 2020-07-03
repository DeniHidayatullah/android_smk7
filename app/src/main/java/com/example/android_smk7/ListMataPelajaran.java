package com.example.android_smk7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListMataPelajaran extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    private String filter = "";
    private JadwalPelajaranHelper myDB;
    private RecyclerView mRecyclerView;
    private AdapterJadwalPelajaran mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jadwalpelajaran);

        mRecyclerView = findViewById(R.id.list_matakuliah);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        populaterecyclerView(filter);

    }

    private void populaterecyclerView(String filter){
        myDB = new JadwalPelajaranHelper(this);
        mAdapter = new AdapterJadwalPelajaran(myDB.jadwalList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

    }
}
