package com.example.android_smk7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailJadwal extends AppCompatActivity {
    int id;
    TextView mapel, hari, jam, ruang, guru, jamselesai;
    Button edit;
    JadwalPelajaranHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);
        myDb  = new JadwalPelajaranHelper(this);

        mapel = findViewById(R.id.matkul);
        hari = findViewById(R.id.hari);
        jam = findViewById(R.id.jam);
        ruang = findViewById(R.id.ruang);
        guru = findViewById(R.id.dosen);
        jamselesai = findViewById(R.id.hp);

        try {
            //get intent to get person id
            id = getIntent().getIntExtra("ID", 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        final JadwalPelajaran detail = myDb.getOne(id);
        //Toast.makeText(DetailJadwal.this, detail.getHari(), Toast.LENGTH_LONG).show();

        mapel.setText(": " + detail.getJadwalPelajaran());
        hari.setText(": " + detail.getHari());
        jam.setText(": " + detail.getJamMulai());
        ruang.setText(": " + detail.getRuangan());
        guru.setText(": " + detail.getNamaGuru());
        jamselesai.setText(": " + detail.getJamSelesai());


    }

}
