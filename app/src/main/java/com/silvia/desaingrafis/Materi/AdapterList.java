package com.silvia.desaingrafis.Materi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silvia.desaingrafis.R;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    List<ModelList> dataList;
    Context context;

    public AdapterList(List<ModelList> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.ViewHolder holder, int position) {
        holder.materi.setText(dataList.get(position).getNama_materi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,PertemuanActivity.class);
                i.putExtra("id_materi",dataList.get(position).getId_materi());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView materi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            materi = itemView.findViewById(R.id.Materi);
        }
    }
}
