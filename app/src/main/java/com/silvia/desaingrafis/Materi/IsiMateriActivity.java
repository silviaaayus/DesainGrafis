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
import com.silvia.desaingrafis.databinding.ActivityIsiMateriBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IsiMateriActivity extends AppCompatActivity {
    private ActivityIsiMateriBinding binding;
    API api;
    private List<ModelList> dataList;
    String semester;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIsiMateriBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent i = getIntent();
        semester =  i.getStringExtra("semester");

        api = new API();
        AndroidNetworking.initialize(this);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.recyclerMateri.setHasFixedSize(true);
        binding.recyclerMateri.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        dataList = new ArrayList<>();
        if (semester.equalsIgnoreCase(""+1)){
            getMateriGanjil();
        }else{
            getMateriGenap();
        }

    }

    private void getMateriGanjil() {
        Log.e("api",api.MateriGanjil);
        AndroidNetworking.get(api.MateriGanjil)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equalsIgnoreCase("sukses")) {

                                JSONArray res = response.getJSONArray("res");
                                Gson gson = new Gson();
                                dataList.clear();
                                for (int i = 0; i < res.length(); i++) {
                                    JSONObject data = res.getJSONObject(i);
                                    ModelList Isi = gson.fromJson(data + "", ModelList.class);
                                    dataList.add(Isi);
                                }
                                AdapterList adapter = new AdapterList(dataList);
                                binding.recyclerMateri.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } else {

                                Toast.makeText(IsiMateriActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
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
    private void getMateriGenap() {
        Log.e("api",api.MateriGenap);
        AndroidNetworking.get(api.MateriGenap)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equalsIgnoreCase("sukses")) {

                                JSONArray res = response.getJSONArray("res");
                                Gson gson = new Gson();
                                dataList.clear();
                                for (int i = 0; i < res.length(); i++) {
                                    JSONObject data = res.getJSONObject(i);
                                    ModelList Isi = gson.fromJson(data + "", ModelList.class);
                                    dataList.add(Isi);
                                }
                                AdapterList adapter = new AdapterList(dataList);
                                binding.recyclerMateri.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } else {

                                Toast.makeText(IsiMateriActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
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