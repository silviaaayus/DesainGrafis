package com.silvia.desaingrafis.Evaluasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityDataSiswaBinding;

public class DataSiswaActivity extends AppCompatActivity {
    private ActivityDataSiswaBinding binding;
    String nama,kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataSiswaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataSiswaActivity.this,PilihanEvaluasiActivity.class);
                i.putExtra("nama_siswa",binding.edtnama.getText().toString());
                i.putExtra("kelas",binding.edtkelas.getText().toString());
                startActivity(i);
            }
        });

    }
}