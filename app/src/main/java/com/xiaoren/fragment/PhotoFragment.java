package com.xiaoren.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.xiaoren.R;

public class PhotoFragment extends Fragment {

    public static PhotoFragment instance(String link) {
        PhotoFragment photoFragment = new PhotoFragment();
        Bundle bundle
                = new Bundle();
        bundle.putString("link", link);
        photoFragment.setArguments(bundle);
        return photoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }


    private ImageView imgPhoto;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgPhoto = view.findViewById(R.id.imgPhoto);
        String link = getArguments().getString("link");
        Glide.with(this).load(link).into(imgPhoto);
    }

}
