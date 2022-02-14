package com.silvia.desaingrafis.Evaluasi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.silvia.desaingrafis.API;
import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityEvaluasiBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EvaluasiActivity extends AppCompatActivity {

private ActivityEvaluasiBinding binding;
    API api;

    int skor=0;
    int arr;
    int x;
    int salah = 0;
    int benar = 0;

    String kunci;
    String semester,tipegenap, tipeganjil;

    String nama,kelas;
    String jumlahSkor,jumlahBenar,jumlahsalah;

    ArrayList<String> arraySoal = new ArrayList<>();
    ArrayList<String> arrayNomor = new ArrayList<>();
    ArrayList<ArrayList<String>> arrayJawaban = new ArrayList<>();
    ArrayList<String> arrayKunci = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEvaluasiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api = new API();
        AndroidNetworking.initialize(this);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent i = getIntent();
        nama = i.getStringExtra("nama_siswa");
        kelas = i.getStringExtra("kelas");
        semester = i.getStringExtra("semester");


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                cekJawaban();
            }
        });

        if (semester.equalsIgnoreCase(""+1)){
            ambilData();
        }else{
            ambilDataGenap();
        }
    }

    public void setKonten() {
        binding.rgPilihanJawaban.clearCheck();
        arr = arraySoal.size();
        if (x >= arr) { //jika nilai x melebihi nilai arr(panjang array) maka akan pindah activity (kuis selesai)
            //menjadikan skor menjadi string
            jumlahSkor = String.valueOf(skor);
            jumlahBenar = String.valueOf(benar);
            jumlahsalah = String.valueOf(salah);
            Log.e("nilai", "" + jumlahSkor);
            Intent i = new Intent(EvaluasiActivity.this, HasilQuisActivity.class);
            i.putExtra("activity", "PilihanGanda");
            i.putExtra("skorAkhir", jumlahSkor);
            i.putExtra("nama_siswa",nama);
            i.putExtra("kelas",kelas);
            i.putExtra("benar",jumlahBenar);
            i.putExtra("salah",jumlahsalah);


            startActivity(i);
        } else {
            //setting text dengan mengambil text dari method getter di kelas SoalPilihanGanda

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.tvNo.setText(Html.fromHtml(arrayNomor.get(x), Html.FROM_HTML_MODE_COMPACT));
                binding.tvSoal.setText(Html.fromHtml(arraySoal.get(x), Html.FROM_HTML_MODE_COMPACT));
                binding.rbPilihanJawaban1.setText(Html.fromHtml(arrayJawaban.get(x).get(0), Html.FROM_HTML_MODE_COMPACT));
                binding.rbPilihanJawaban2.setText(Html.fromHtml(arrayJawaban.get(x).get(1), Html.FROM_HTML_MODE_COMPACT));
                binding.rbPilihanJawaban3.setText(Html.fromHtml(arrayJawaban.get(x).get(2), Html.FROM_HTML_MODE_COMPACT));
                binding.rbPilihanJawaban4.setText(Html.fromHtml(arrayJawaban.get(x).get(3), Html.FROM_HTML_MODE_COMPACT));
                binding.rbPilihanJawaban5.setText(Html.fromHtml(arrayJawaban.get(x).get(4), Html.FROM_HTML_MODE_COMPACT));
                kunci = Html.fromHtml(arrayKunci.get(x), Html.FROM_HTML_MODE_COMPACT).toString();
            } else {
                binding.tvNo.setText(Html.fromHtml(arrayNomor.get(x)));
                binding.tvSoal.setText(Html.fromHtml(arraySoal.get(x)));
                binding.rbPilihanJawaban1.setText(Html.fromHtml(arrayJawaban.get(x).get(0)));
                binding.rbPilihanJawaban2.setText(Html.fromHtml(arrayJawaban.get(x).get(1)));
                binding.rbPilihanJawaban3.setText(Html.fromHtml(arrayJawaban.get(x).get(2)));
                binding.rbPilihanJawaban4.setText(Html.fromHtml(arrayJawaban.get(x).get(3)));
                binding.rbPilihanJawaban5.setText(Html.fromHtml(arrayJawaban.get(x).get(4)));
                kunci = String.valueOf(Html.fromHtml(arrayKunci.get(x)));
            }


        }
        x++;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void cekJawaban() {

        if (binding.rbPilihanJawaban1.isChecked()) { //jika radio button 1 diklik
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if (binding.rbPilihanJawaban1.getText().toString().equals(kunci)) {
                skor = skor + 5;
                benar = benar+1;
                Log.e("skor", "" + skor);
//                binding.tvSkor.setText("" + skor);    //mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            } else {
//                binding.tvSkor.setText("" + skor);
                salah = salah+1;
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        } else if (binding.rbPilihanJawaban2.isChecked()) {
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if (binding.rbPilihanJawaban2.getText().toString().equals(kunci)) {
                skor = skor + 5;
                benar = benar+1;
//                binding.tvSkor.setText("" + skor);    //mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            } else {
                salah = salah+1;
//                binding.tvSkor.setText("" + skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        } else if (binding.rbPilihanJawaban3.isChecked()) {
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if (binding.rbPilihanJawaban3.getText().toString().equals(kunci)) {
                skor = skor + 5;
                benar = benar+1;
//                binding.tvSkor.setText("" + skor);    //mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            } else {
                salah = salah+1;
//                binding.tvSkor.setText("" + skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        } else if (binding.rbPilihanJawaban4.isChecked()) {
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if (binding.rbPilihanJawaban4.getText().toString().equals(kunci)) {
                skor = skor + 5;
                benar = benar+1;
//                binding.tvSkor.setText("" + skor);    //mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            } else {
                salah = salah+1;
//                binding.tvSkor.setText("" + skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        } else if (binding.rbPilihanJawaban5.isChecked()) {
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if (binding.rbPilihanJawaban5.getText().toString().equals(kunci)) {
                skor = skor + 5;
                benar = benar+1;
//                binding.tvSkor.setText("" + skor);    //mtvSkor diset nilainya = var skor
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                setKonten(); //update next konten
            } else {
                salah = salah+1;
//                binding.tvSkor.setText("" + skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        } else {
            Toast.makeText(this, "Silahkan pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Apakah kamu yakin ingin keluar dari session ini?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }





    public void ambilData() {
        Log.e("apiSoal",api.EvaluasiGanjil);
        AndroidNetworking.get(api.EvaluasiGanjil)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getString("status").equalsIgnoreCase("sukses")) {
                                JSONArray data = response.getJSONArray("res");


                                for (int i = 0; i < data.length(); i++) {
                                    ArrayList<String> subJawaban = new ArrayList<>();
                                    JSONObject obj = data.getJSONObject(i);
                                    String id_kuis = obj.getString("id_evaluasi");
                                    String nomor = obj.getString("no_soal");

                                    String soal = obj.getString("soal");

                                    String pil_a = obj.getString("pil_a");
                                    subJawaban.add(pil_a);

                                    String pil_b = obj.getString("pil_b");
                                    subJawaban.add(pil_b);

                                    String pil_c = obj.getString("pil_c");
                                    subJawaban.add(pil_c);

                                    String pil_d = obj.getString("pil_d");
                                    subJawaban.add(pil_d);

                                    String pil_e = obj.getString("pil_e");
                                    subJawaban.add(pil_e);


                                    String kunci = obj.getString("kunci");
                                    arraySoal.add(soal);
                                    arrayJawaban.add(subJawaban);
                                    arrayKunci.add(kunci);
                                    arrayNomor.add(nomor);



                                }
                                setKonten();
                            } else {
                                Toast.makeText(EvaluasiActivity.this,"Gagal", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }


                });


    }

    public void ambilDataGenap() {
        Log.e("apiSoal",api.EvaluasiGenap);
        AndroidNetworking.get(api.EvaluasiGenap)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (response.getString("status").equalsIgnoreCase("sukses")) {
                                JSONArray data = response.getJSONArray("res");


                                for (int i = 0; i < data.length(); i++) {
                                    ArrayList<String> subJawaban = new ArrayList<>();
                                    JSONObject obj = data.getJSONObject(i);
                                    String id_kuis = obj.getString("id_evaluasi");
                                    String nomor = obj.getString("no_soal");

                                    String soal = obj.getString("soal");

                                    String pil_a = obj.getString("pil_a");
                                    subJawaban.add(pil_a);

                                    String pil_b = obj.getString("pil_b");
                                    subJawaban.add(pil_b);

                                    String pil_c = obj.getString("pil_c");
                                    subJawaban.add(pil_c);

                                    String pil_d = obj.getString("pil_d");
                                    subJawaban.add(pil_d);

                                    String pil_e = obj.getString("pil_e");
                                    subJawaban.add(pil_e);


                                    String kunci = obj.getString("kunci");
                                    arraySoal.add(soal);
                                    arrayJawaban.add(subJawaban);
                                    arrayKunci.add(kunci);
                                    arrayNomor.add(nomor);




                                }
                                setKonten();
                            } else {
                                Toast.makeText(EvaluasiActivity.this,"Gagal", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }


                });


    }
}