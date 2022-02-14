package com.silvia.desaingrafis.Materi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityMainBinding;
import com.silvia.desaingrafis.databinding.ActivityMateriBinding;

public class MateriActivity extends AppCompatActivity {
    private ActivityMateriBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMateriBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    ;

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.materiSemGanjil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MateriActivity.this,IsiMateriActivity.class);
                i.putExtra("semester","1");
                startActivity(i);
            }
        });

        binding.materiSemGenap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MateriActivity.this, IsiMateriActivity.class);
                i.putExtra("semester","2");
                startActivity(i);
            }
        });
    }

}