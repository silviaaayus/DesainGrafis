package com.silvia.desaingrafis.Materi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.silvia.desaingrafis.API;
import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityIsiPertemuanBinding;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class IsiPertemuanActivity extends AppCompatActivity implements DownloadFile.Listener {
    private ActivityIsiPertemuanBinding binding;
    API api;
    String file,pertemuan;
    MediaPlayer mp;


    PDFPagerAdapter adapter;
    RemotePDFViewPager remotePDFViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIsiPertemuanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mp = MediaPlayer.create(this, R.raw.materi);
        mp.start();
        mp.setLooping(true);



        api = new API();
        Intent i = getIntent();
        file = i.getStringExtra("file");
        file = api.IsiPertemuan + file;
        pertemuan = i.getStringExtra("nama_pertemuan");

        remotePDFViewPager = new RemotePDFViewPager(this, file, this);

        binding.namaMateri.setText(pertemuan);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);

        LinearLayout pdfmateri = (LinearLayout) findViewById(R.id.pdfpertemuan);
        pdfmateri.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void onFailure(Exception e) {
        Toast.makeText(this, "PDF Tidak Dapat Ditampilkan", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

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
        if (mp.isPlaying()){
            mp.release();
        }
    }
}