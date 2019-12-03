package com.xiaoren;

import java.util.List;

interface DetailView {

    void loadData();

    void showLoading();

    void hideLoading();

    void notifyUpdateData(List<Photo> result);

    List<Photo> parseHtml();
}
