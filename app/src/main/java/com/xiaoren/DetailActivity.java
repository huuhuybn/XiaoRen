package com.xiaoren;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.xiaoren.adapter.PhotoPagerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private String link;
    private ViewPager pager;
    private List<Photo> photos;
    private PhotoPagerAdapter photoPagerAdapter;
    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        detailPresenter = new DetailPresenter(this);
        detailPresenter.loadData();
    }

    public void initView() {
        link = getIntent().getStringExtra("link");
        pager = findViewById(R.id.pager);
        photos = new ArrayList<>();
        photoPagerAdapter = new PhotoPagerAdapter(getSupportFragmentManager(), photos);
        pager.setAdapter(photoPagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(getString(R.string.album, position, photos.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void loadData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void notifyUpdateData(List<Photo> result) {
        photos.addAll(result);
        photoPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public List<Photo> parseHtml() {
        List<Photo> photos_ = new ArrayList<>();
        try {
            // Init Jsoup variable to parse html
            Document doc = Jsoup.connect(link).get();
            // search all tag div with class = loop
            Elements links = doc.select("span.photoThum");

            for (Element element : links) {
                Photo photo = new Photo();
                // select tag a and get attr is title
                String link = element.select("a").attr("href");
                // select tag img and get attr is img with end is jpg, get attr = src
                photo.thumb = link;
                Log.e("l", link);
                photos_.add(photo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return photos_;
    }
}
