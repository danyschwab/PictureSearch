package br.com.danyswork.picturesearch.presenter;

import br.com.danyswork.picturesearch.request.Repository;
import br.com.danyswork.picturesearch.ui.MainActivity;

public class Presenter {

    private Repository mRepository;
    private MainActivity mActivity;

    public Presenter(MainActivity activity) {
        this.mActivity = activity;
        this.mRepository = new Repository();
    }

    private void getImages() {

    }

    public void cancelSearch() {

    }

    public void search(String query) {
        mRepository.search(query);
    }
}
