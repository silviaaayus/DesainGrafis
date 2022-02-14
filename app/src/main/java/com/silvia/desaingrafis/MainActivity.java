package com.silvia.desaingrafis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.silvia.desaingrafis.About.AboutActivity;
import com.silvia.desaingrafis.Evaluasi.DataSiswaActivity;
import com.silvia.desaingrafis.Evaluasi.PilihanEvaluasiActivity;
import com.silvia.desaingrafis.Materi.MateriActivity;
import com.silvia.desaingrafis.Profil.ProfilActivity;
import com.silvia.desaingrafis.Video.VideoActivity;
import com.silvia.desaingrafis.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
     MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         mp = MediaPlayer.create(this, R.raw.utama);
         mp.setLooping(true);


        binding.btnkikd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,KiKdActivity.class);
                startActivity(i);

            }
        });

        binding.btnmateri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MateriActivity.class);
                startActivity(i);

            }
        });

        binding.btnabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        binding.btnprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(i);
            }
        });

        binding.btnevaluasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DataSiswaActivity.class);
                startActivity(i);
            }
        });
        binding.btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
            mp.pause();

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mp.release();
//    }


    @Override
    protected void onResume() {
        super.onResume();
        mp.start();

    }

}