package com.xiaoren;

import java.util.List;

public interface MainView {

    void notifyUpdateDate(List<Photo> photos);

    List<Photo> parseHtml();

    void showLoading();

    void hideLoading();

    void updateSize();
}
