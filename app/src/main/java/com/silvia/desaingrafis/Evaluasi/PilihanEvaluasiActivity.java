package com.silvia.desaingrafis.Evaluasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityPilihanEvaluasiBinding;

public class PilihanEvaluasiActivity extends AppCompatActivity {
    private ActivityPilihanEvaluasiBinding binding;
    String nama,kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPilihanEvaluasiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        nama = i.getStringExtra("nama_siswa");
        kelas = i.getStringExtra("kelas");
        Log.e("nama_siswa",nama);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.evaluasiSemGanjil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PilihanEvaluasiActivity.this,EvaluasiActivity.class);
                i.putExtra("nama_siswa",nama);
                i.putExtra("kelas",kelas);
                i.putExtra("semester","1");
                startActivity(i);

            }
        });

        binding.evaluasiSemGenap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PilihanEvaluasiActivity.this,EvaluasiActivity.class);
                i.putExtra("nama_siswa",nama);
                i.putExtra("kelas",kelas);
                i.putExtra("semester","2");
                startActivity(i);

            }
        });
    }
}