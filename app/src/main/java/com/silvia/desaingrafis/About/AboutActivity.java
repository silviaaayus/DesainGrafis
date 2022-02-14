package com.silvia.desaingrafis.About;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityAboutBinding;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class AboutActivity extends AppCompatActivity implements DownloadFile.Listener {
    private ActivityAboutBinding binding;

    PDFPagerAdapter adapter;
    RemotePDFViewPager remotePDFViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        remotePDFViewPager = new RemotePDFViewPager(this, "https://sisfoagam.citragroup-hrd.com/api/desain/pdf/ABOUT.pdf", this);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);

        LinearLayout about = (LinearLayout) findViewById(R.id.pdfPlace);
        about.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onFailure(Exception e) {
        Toast.makeText(this, "PDF Tidak Bisa Ditampilkan",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
}