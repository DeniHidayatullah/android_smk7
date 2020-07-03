package com.example.android_smk7.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_smk7.MainActivity;
import com.example.android_smk7.R;
import com.example.android_smk7.config.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    // deklarasi objek
    TextInputLayout validasiNISN, validasiPassword;
    EditText txtNISN, txtPassword;
    Button btnLogin;
    // deklarasi variabel
    String NISN, Password;

    // deklarasi variabel alamat host
    // setting terlebih dahulu supaya antara laptop dan android jadi satu jaringan
    public static String URL = Server.URL + "api/app_login/index_login";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        // inisialisasi variabel objek
        validasiNISN = findViewById(R.id.validasiNISN);
        validasiPassword = findViewById(R.id.validasiPassword);
        txtNISN = findViewById(R.id.txtNISN);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // jika tombol login diklik
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NISN = txtNISN.getText().toString().trim();
                Password = txtPassword.getText().toString().trim();

                if ( Password.isEmpty() ) {
                    validasiNISN.setError("Nisn harus diisi!");
                } else if ( Password.isEmpty() ) {
                    validasiPassword.setError("Password harus diisi!");
                } else {
                    auth_siswa(NISN, Password);
                }
            }
        });
    }

    // method login
    private void auth_siswa(final String NISN, final String Password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String nis = jsonObject1.getString("nis").trim();
                            String nisn = jsonObject1.getString("nisn").trim();
                            String nama_siswa = jsonObject1.getString("nama_siswa").trim();
                            String jenis_kelamin = jsonObject1.getString("jenis_kelamin");
                            String tempat_lahir = jsonObject1.getString("tanggal_lahir").trim();
                            String tanggal_lahir = jsonObject1.getString("tanggal_lahir").trim();
                            String agama = jsonObject1.getString("agama").trim();
                            String kecamatan = jsonObject1.getString("kecamatan").trim();
                            String hp = jsonObject1.getString("hp").trim();
                            String foto = jsonObject1.getString("foto").trim();
                            String angkatan = jsonObject1.getString("angkatan").trim();
                            String kode_kelas = jsonObject1.getString("kode_kelas").trim();
                            String password = jsonObject1.getString("password").trim();
                            String nama_ayah = jsonObject1.getString("nama_ayah").trim();
                            String pekerjaan_ayah = jsonObject1.getString("pekerjaan_ayah").trim();
                            String nama_ibu = jsonObject1.getString("nama_ibu").trim();
                            String pekerjaan_ibu = jsonObject1.getString("pekerjaan_ibu").trim();
                            String nama_wali = jsonObject1.getString("nama_wali").trim();
                            String pekerjaan_wali = jsonObject1.getString("pekerjaan_wali").trim();

                            sessionManager.createSession(NISN, nis,  nisn, nama_siswa, jenis_kelamin, tempat_lahir,tanggal_lahir,  agama, kecamatan,
                                    hp, foto, angkatan, kode_kelas,  password,  nama_ayah, pekerjaan_ayah,
                                    nama_ibu, pekerjaan_ibu,  nama_wali, pekerjaan_wali);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Nisn dan Password tidak ditemukan! ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error login : " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error login : " + error.toString(), Toast.LENGTH_SHORT) .show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", NISN);
                // sesuaikan dengan $_POST pada PHP
                params.put("password", Password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
