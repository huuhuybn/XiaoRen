package com.xiaoren;

import java.util.List;

public class DetailPresenter {

    DetailView detailView;

    public DetailPresenter(DetailView detailView) {
        this.detailView = detailView;
    }

    public void loadData() {
        MyTaskLoader myTaskLoader = new MyTaskLoader(new MyTaskLoader.OnLoadFinished() {
            @Override
            public void onFinish(List<Photo> result) {
                detailView.notifyUpdateData(result);
            }

            @Override
            public List<Photo> onDoing() {
                return detailView.parseHtml();
            }
        });
        myTaskLoader.execute();
    }
}
