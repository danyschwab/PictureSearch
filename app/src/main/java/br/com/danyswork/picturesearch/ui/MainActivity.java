package br.com.danyswork.picturesearch.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import br.com.danyswork.picturesearch.R;
import br.com.danyswork.picturesearch.model.Picture;
import br.com.danyswork.picturesearch.presenter.Presenter;

public class MainActivity extends AppCompatActivity {


    private ProgressBar mProgressBar;

    private PicturesAdapter mAdapter;
    private Presenter mPresenter;
    private boolean mIsSearch = false;
    private String mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_pictures);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mPresenter = new Presenter(this);
        mAdapter = new PicturesAdapter(this);

        LinearLayoutManager layoutParams = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutParams);

        recyclerView.setAdapter(mAdapter);
    }

    private void clearContents() {
        mPresenter.cancelSearch();
        mProgressBar.setVisibility(View.VISIBLE);
        mAdapter.clearContent();
    }

    public void setContent(Picture picture) {
        mProgressBar.setVisibility(View.GONE);
        mAdapter.setContent(picture);
    }

}
