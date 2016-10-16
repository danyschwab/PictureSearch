package br.com.danyswork.picturesearch.presenter;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.danyswork.picturesearch.R;
import br.com.danyswork.picturesearch.model.Picture;
import br.com.danyswork.picturesearch.model.Pictures;
import br.com.danyswork.picturesearch.request.Repository;
import br.com.danyswork.picturesearch.ui.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    private Repository mRepository;
    private MainActivity mActivity;

    public Presenter(MainActivity activity) {
        this.mActivity = activity;
        this.mRepository = new Repository(activity);
    }

    public void cancelSearch() {
        mRepository.cancelSearch();
    }

    public void search(String query) {
        mRepository.search(query, new Callback<Pictures>() {
            @Override
            public void onResponse(Call<Pictures> call, Response<Pictures> response) {
                if (mActivity != null) {
                    List<Picture> pictures = new ArrayList<>();
                    if ( response.body() != null ) {
                        pictures = response.body().getPictures();
                    }
                    mActivity.setContent(pictures);
                }
            }

            @Override
            public void onFailure(Call<Pictures> call, Throwable t) {
            }
        });
    }
}
