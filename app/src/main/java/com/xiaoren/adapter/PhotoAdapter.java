package com.xiaoren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xiaoren.Photo;
import com.xiaoren.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

    public Context context;
    public List<Photo> photos;

    public PhotoAdapter(Context context, List<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.photo = photos.get(position);
        holder.tvTitle.setText(holder.photo.title);
        Glide.with(context).load(holder.photo.thumb).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


}
