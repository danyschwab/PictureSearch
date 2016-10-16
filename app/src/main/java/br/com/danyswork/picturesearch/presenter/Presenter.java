package br.com.danyswork.picturesearch.presenter;

import br.com.danyswork.picturesearch.request.Repository;
import br.com.danyswork.picturesearch.ui.MainActivity;

public class Presenter {

    private Repository mRepository;
    private MainActivity mActivity;
    private int pageSearch;

    public Presenter(MainActivity activity) {
        this.pageSearch = 1;
        this.mActivity = activity;
        this.mRepository = new Repository();
    }

    private void getImages() {

    }

    public void cancelSearch() {
        pageSearch = 1;
    }

    public void search(String s) {

    }
}
