package com.silvia.desaingrafis.Evaluasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.silvia.desaingrafis.MainActivity;
import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityHasilQuisBinding;

public class HasilQuisActivity extends AppCompatActivity {
    private ActivityHasilQuisBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHasilQuisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        binding.namaSiswaHasil.setText(i.getStringExtra("nama_siswa"));
        binding.kelasHasil.setText(i.getStringExtra("kelas"));
        binding.benar.setText(i.getStringExtra("benar"));
        binding.salah.setText(i.getStringExtra("salah"));
        binding.hasilnilai.setText(i.getStringExtra("skorAkhir"));

        binding.ulangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HasilQuisActivity.this, DataSiswaActivity.class);
                startActivity(i);
            }
        });

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HasilQuisActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}