package com.silvia.desaingrafis.Video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.silvia.desaingrafis.API;
import com.silvia.desaingrafis.Materi.AdapterPertemuan;
import com.silvia.desaingrafis.Materi.ModelPertemuan;
import com.silvia.desaingrafis.Materi.PertemuanActivity;
import com.silvia.desaingrafis.R;
import com.silvia.desaingrafis.databinding.ActivityVideoBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {
    private ActivityVideoBinding binding;
    API api;
    private List<ModelVideo> dataVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        api = new API();
        AndroidNetworking.initialize(this);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.recyclerVideo.setHasFixedSize(true);
        binding.recyclerVideo.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dataVideo = new ArrayList<>();
        getVideo();
    }

    private void getVideo() {
        Log.e("api",api.Video);
        AndroidNetworking.get(api.Video)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equalsIgnoreCase("sukses")) {

                                JSONArray res = response.getJSONArray("res");
                                Gson gson = new Gson();
                                dataVideo.clear();
                                for (int i = 0; i < res.length(); i++) {
                                    JSONObject data = res.getJSONObject(i);
                                    ModelVideo Isi = gson.fromJson(data + "", ModelVideo.class);
                                    dataVideo.add(Isi);
                                }
                                AdapterVideo adapter = new AdapterVideo(dataVideo);
                                binding.recyclerVideo.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } else {

                                Toast.makeText(VideoActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tampil menu", "response:" + anError);
                    }
                });
    }
}