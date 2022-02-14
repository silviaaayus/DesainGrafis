package com.silvia.desaingrafis.Materi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.media.MediaPlayer;
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
import com.silvia.desaingrafis.R;

import com.silvia.desaingrafis.databinding.ActivityPertemuanBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PertemuanActivity extends AppCompatActivity {

    private ActivityPertemuanBinding binding;
    API api;
    private List<ModelPertemuan> dataPertemuan;
    String id_materi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPertemuanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        api = new API();
        AndroidNetworking.initialize(this);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent i = getIntent();
        id_materi = i.getStringExtra("id_materi");



        binding.recyclerPertemuan.setHasFixedSize(true);
        binding.recyclerPertemuan.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dataPertemuan = new ArrayList<>();
        getPertemuan();
    }

    private void getPertemuan() {
        Log.e("api",api.Pertemuan);
        AndroidNetworking.get(api.Pertemuan+id_materi)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equalsIgnoreCase("sukses")) {

                                JSONArray res = response.getJSONArray("res");
                                Gson gson = new Gson();
                                dataPertemuan.clear();
                                for (int i = 0; i < res.length(); i++) {
                                    JSONObject data = res.getJSONObject(i);
                                    ModelPertemuan Isi = gson.fromJson(data + "", ModelPertemuan.class);
                                    dataPertemuan.add(Isi);
                                }
                                AdapterPertemuan adapter = new AdapterPertemuan(dataPertemuan);
                                binding.recyclerPertemuan.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } else {

                                Toast.makeText(PertemuanActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
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