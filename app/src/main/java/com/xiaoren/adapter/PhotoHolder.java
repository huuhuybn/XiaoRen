package com.xiaoren.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaoren.DetailActivity;
import com.xiaoren.Photo;
import com.xiaoren.R;

class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imgPhoto;
    public TextView tvTitle;
    public Photo photo;

    public PhotoHolder(@NonNull View root) {
        super(root);
        imgPhoto = root.findViewById(R.id.imgPhoto);
        tvTitle = root.findViewById(R.id.tvTitle);
        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("link", photo.link);
        view.getContext().startActivity(intent);
    }
}
