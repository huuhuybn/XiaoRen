package com.xiaoren;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.xiaoren.adapter.PhotoAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MainView {

    public String url = "http://www.xiuren.org/";
    private RecyclerView lvList;
    private GridLayoutManager gridLayoutManager;
    private MainPresenter mainPresenter;
    private int page = 1;
    private List<Photo> photos;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter = new MainPresenter(this);
        mainPresenter.loadData(page);
        lvList.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                MainActivity.this.page++;
                mainPresenter.loadData(MainActivity.this.page);
            }
        });
    }

    public void initView() {
        lvList = findViewById(R.id.lvList);
        gridLayoutManager = new GridLayoutManager(this, 3);
        photos = new ArrayList<>();
        photoAdapter = new PhotoAdapter(this, photos);
        lvList.setLayoutManager(gridLayoutManager);
        lvList.setAdapter(photoAdapter);
    }

    @Override
    public void notifyUpdateDate(List<Photo> result) {
        photos.addAll(result);
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public List<Photo> parseHtml() {
        List<Photo> photos_ = new ArrayList<>();
        try {
            StringBuilder myLink = new StringBuilder();
            // create link to request
            myLink.append(url).append(getString(R.string.param, page));
            // Init Jsoup variable to parse html
            Document doc = Jsoup.connect(myLink.toString()).get();
            // search all tag div with class = loop
            Elements links = doc.select("div.loop");

            for (Element element : links) {
                Photo photo = new Photo();
                // select tag a and get attr is title
                String title = element.select("a").attr("title");
                // select tag img and get attr is img with end is jpg, get attr = src
                String thumb = element.select("img[src$=.jpg]").attr("src");

                String link = element.select("a[href]").attr("href");
                photo.link = link;
                photo.thumb = thumb;
                photo.title = title;
                photos_.add(photo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return photos_;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, getString(R.string.loading), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, getString(R.string.notity_scroll_more), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSize() {
        setTitle(getString(R.string.count_photo, photos.size()));
    }

}
