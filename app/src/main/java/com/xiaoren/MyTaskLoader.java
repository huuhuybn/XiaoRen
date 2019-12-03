package com.xiaoren;

import android.os.AsyncTask;

import java.util.List;

public class MyTaskLoader extends AsyncTask<String, Long, List<Photo>> {

    public interface OnLoadFinished {
        void onFinish(List<Photo> result);
        List<Photo> onDoing();
    }

    public OnLoadFinished onLoadFinished;

    public MyTaskLoader(OnLoadFinished onLoadFinished) {
        this.onLoadFinished = onLoadFinished;
    }

    @Override
    protected List<Photo> doInBackground(String... strings) {
        return onLoadFinished.onDoing();
    }

    @Override
    protected void onPostExecute(List<Photo> photos) {
        super.onPostExecute(photos);
        onLoadFinished.onFinish(photos);
    }
}
