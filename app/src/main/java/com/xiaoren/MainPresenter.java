package com.xiaoren;

import java.util.List;

public class MainPresenter {


    MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void loadData(int page) {
        mainView.showLoading();
        MyTaskLoader myTaskLoader = new MyTaskLoader(new MyTaskLoader.OnLoadFinished() {
            @Override
            public void onFinish(List<Photo> result) {
                mainView.notifyUpdateDate(result);
                mainView.hideLoading();
                mainView.updateSize();
            }

            @Override
            public List<Photo> onDoing() {
                return mainView.parseHtml();
            }
        });
        myTaskLoader.execute();
    }
}
