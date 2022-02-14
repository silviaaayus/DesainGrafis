package com.silvia.desaingrafis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.silvia.desaingrafis.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    private int waktu_loading = 7000;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         mp = MediaPlayer.create(this, R.raw.loading);
        mp.start();


        binding.namaInstansi.setText("Aplikasi Media Pembelajaran");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }




        }, waktu_loading);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp.isPlaying()){
            mp.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
    }
}