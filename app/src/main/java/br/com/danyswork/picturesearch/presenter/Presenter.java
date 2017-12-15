package br.com.danyswork.picturesearch.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.danyswork.picturesearch.model.Picture;
import br.com.danyswork.picturesearch.model.Pictures;
import br.com.danyswork.picturesearch.request.Repository;
import br.com.danyswork.picturesearch.ui.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    private Repository repository;
    private MainActivity activity;

    public Presenter(MainActivity activity) {
        this.activity = activity;
        this.repository = new Repository(activity);
    }

    public void cancelSearch() {
        repository.cancelSearch();
    }

    public void search(String query) {
        repository.search(query, new Callback<Pictures>() {
            @Override
            public void onResponse(@NonNull Call<Pictures> call, @NonNull Response<Pictures> response) {
                if (activity != null) {
                    List<Picture> pictures = new ArrayList<>();
                    Pictures body = response.body();
                    if (  body != null ) {
                        pictures = body.getPictures();
                    }
                    activity.setContent(pictures);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pictures> call, Throwable t) {
            }
        });
    }
}
