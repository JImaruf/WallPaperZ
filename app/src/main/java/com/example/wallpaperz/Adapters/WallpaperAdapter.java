package com.example.wallpaperz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperz.Listeners.onRecyclerClick;
import com.example.wallpaperz.Models.Photo;
import com.example.wallpaperz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.MyViewHolder> {

    Context context;
    List<Photo> list;
    onRecyclerClick recyclerClick;

    public WallpaperAdapter(Context context, List<Photo> list, onRecyclerClick recyclerClick) {
        this.context = context;
        this.list = list;
        this.recyclerClick = recyclerClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(list.get(position).getSrc().getMedium()).placeholder(R.drawable.ic_baseline_wallpaper_24).into(holder.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerClick.onClick(list.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.containerID);
        }
    }
}
