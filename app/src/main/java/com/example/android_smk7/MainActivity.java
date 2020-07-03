package com.example.android_smk7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Html;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.android_smk7.controllers.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textViewNis, textViewNisn, textViewNama_siswa, textViewJenisKelamin, textViewTempatLahir, textViewTanggalLahir,
            textViewAgama, textViewKecamatan, textViewHp, textViewFoto, textViewAngkatan , textViewKodeKelas, textViewPassword,
            textViewNamaAyah, textViewPekerjaanAyah, textViewNamaIbu, textViewPekerjaanIbu, textViewNamaWali, textViewPekerjaanWali;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout_profil);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //Session
        sessionManager = new SessionManager(getApplicationContext());

        textViewNis = (TextView) findViewById(R.id.textViewNis);
        textViewNisn = (TextView) findViewById(R.id.textViewNisn);
        textViewNama_siswa = (TextView) findViewById(R.id.textViewNamaSiswa);
        textViewJenisKelamin = (TextView) findViewById(R.id.textViewJenisKelamin);
        textViewTempatLahir = (TextView) findViewById(R.id.textViewTempatLahir);
        textViewTanggalLahir = (TextView) findViewById(R.id.textViewTanggalLahir);
        textViewAgama= (TextView) findViewById(R.id.textViewAgama);
        textViewKecamatan = (TextView) findViewById(R.id.textViewKecamatan);
        textViewAgama = (TextView) findViewById(R.id.textViewAgama);
        textViewHp = (TextView) findViewById(R.id.textViewHp);
        textViewFoto = (TextView) findViewById(R.id.textViewFoto);
        textViewAngkatan = (TextView) findViewById(R.id.textViewAngkatan);
        textViewKodeKelas = (TextView) findViewById(R.id.textViewKodeKelas);
        textViewPassword= (TextView) findViewById(R.id.textViewPassword);
        textViewNamaAyah = (TextView) findViewById(R.id.textViewAyah);
        textViewPekerjaanAyah = (TextView) findViewById(R.id.textViewPekerjaanAyah);
        textViewNamaIbu= (TextView) findViewById(R.id.textViewIbu);
        textViewPekerjaanIbu = (TextView) findViewById(R.id.textViewPekerjaanIbu);
        textViewNamaWali = (TextView) findViewById(R.id.textViewWali);
        textViewPekerjaanWali = (TextView) findViewById(R.id.textViewPekerjaanWali);

        //menampilkan user data dari session
        HashMap<String, String> user = sessionManager.getUserDetails();
        String nis = user.get(sessionManager.KEY_NIS);
        String nisn = user.get(sessionManager.KEY_NISN);
        String nama_siswa = user.get(sessionManager.KEY_NAMA_SISWA);
        String jenis_kelamin = user.get(sessionManager.KEY_JENIS_KELAMIN);
        String tempat_lahir = user.get(sessionManager.KEY_TEMPAT_LAHIR);
        String tanggal_lahir = user.get(sessionManager.KEY_TANGGAL_LAHIR);
        String agama = user.get(sessionManager.KEY_AGAMA);
        String kecamatan = user.get(sessionManager.KEY_KECAMATAN);
        String hp = user.get(sessionManager.KEY_HP);
        String foto = user.get(sessionManager.KEY_FOTO);
        String angkatan = user.get(sessionManager.KEY_ANGKATAN);
        String kode_kelas = user.get(sessionManager.KEY_KODE_KELAS);
        String password = user.get(sessionManager.KEY_PASSWORD);
        String nama_ayah = user.get(sessionManager.KEY_NAMA_AYAH);
        String pekerjaan_ayah = user.get(sessionManager.KEY_PEKERJAAN_AYAH);
        String nama_ibu = user.get(sessionManager.KEY_NAMA_IBU);
        String pekerjaan_ibu = user.get(sessionManager.KEY_PEKERJAAN_IBU);
        String nama_wali = user.get(sessionManager.KEY_NAMA_WALI);
        String pekerjaan_wali = user.get(sessionManager.KEY_PEKERJAAN_WALI);

        textViewNis.setText(Html.fromHtml(nis));
        textViewNisn.setText(Html.fromHtml(nisn));
        textViewNama_siswa.setText(Html.fromHtml(nama_siswa));
        textViewJenisKelamin.setText(Html.fromHtml(jenis_kelamin));
        textViewTempatLahir.setText(Html.fromHtml(tempat_lahir));
        textViewTanggalLahir.setText(Html.fromHtml(tanggal_lahir));
        textViewAgama.setText(Html.fromHtml(agama));
        textViewKecamatan.setText(Html.fromHtml(kecamatan));
        textViewHp.setText(Html.fromHtml(hp));
        textViewFoto.setText(Html.fromHtml(foto));
        textViewAngkatan.setText(Html.fromHtml(angkatan));
        textViewKodeKelas.setText(Html.fromHtml(kode_kelas));
        textViewPassword.setText(Html.fromHtml(password));
        textViewNamaAyah.setText(Html.fromHtml(nama_ayah));
        textViewPekerjaanAyah.setText(Html.fromHtml(pekerjaan_ayah));
        textViewNamaIbu.setText(Html.fromHtml(nama_ibu));
        textViewPekerjaanIbu.setText(Html.fromHtml(pekerjaan_ibu));
        textViewNamaWali.setText(Html.fromHtml(nama_wali));
        textViewPekerjaanWali.setText(Html.fromHtml(pekerjaan_wali));

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_profil);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logout:
                sessionManager.logoutUser();
                break;
            case R.id.antrian_anda:
                startActivity(new Intent(this, ListMataPelajaran.class));
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_profil);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
