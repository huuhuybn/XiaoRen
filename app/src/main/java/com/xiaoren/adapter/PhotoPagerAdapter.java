package com.xiaoren.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.xiaoren.Photo;
import com.xiaoren.fragment.PhotoFragment;

import java.util.List;

public class PhotoPagerAdapter extends FragmentStatePagerAdapter {


    private List<Photo> photos;

    public PhotoPagerAdapter(@NonNull FragmentManager fm, List<Photo> photos) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.photos = photos;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return PhotoFragment.instance(photos.get(position).thumb);
    }

    @Override
    public int getCount() {
        return photos.size();
    }
}
