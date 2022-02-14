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

public class AdapterPertemuan extends RecyclerView.Adapter<AdapterPertemuan.ViewHolder> {
    List<ModelPertemuan> dataPertemuan;
    Context context;

    public AdapterPertemuan(List<ModelPertemuan> dataPertemuan) {
        this.dataPertemuan = dataPertemuan;
    }

    @NonNull
    @Override
    public AdapterPertemuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pertemuan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPertemuan.ViewHolder holder, int position) {
        holder.pertemuan.setText(dataPertemuan.get(position).getPertemuan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,IsiPertemuanActivity.class);
                i.putExtra("file",dataPertemuan.get(position).getFile());
                i.putExtra("nama_pertemuan",dataPertemuan.get(position).getPertemuan());
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataPertemuan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pertemuan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            pertemuan = itemView.findViewById(R.id.pertemuan);
        }
    }
}
