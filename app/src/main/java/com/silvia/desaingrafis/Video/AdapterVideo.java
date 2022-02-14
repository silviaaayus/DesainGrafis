package com.silvia.desaingrafis.Video;

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

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.ViewHolder> {
    List<ModelVideo> dataVideo;
    Context context;

    public AdapterVideo(List<ModelVideo> dataVideo) {
        this.dataVideo = dataVideo;
    }

    @NonNull
    @Override
    public AdapterVideo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVideo.ViewHolder holder, int position) {
        holder.namaVideo.setText(dataVideo.get(position).getJudul_video());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,IsiVideoActivity.class);
                i.putExtra("link",dataVideo.get(position).getLink());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaVideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            namaVideo = itemView.findViewById(R.id.video);
        }
    }
}
