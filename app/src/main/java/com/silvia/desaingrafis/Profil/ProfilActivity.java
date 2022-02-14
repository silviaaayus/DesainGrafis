package com.silvia.desaingrafis.Profil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityProfilBinding;

public class ProfilActivity extends AppCompatActivity {

    private ActivityProfilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}