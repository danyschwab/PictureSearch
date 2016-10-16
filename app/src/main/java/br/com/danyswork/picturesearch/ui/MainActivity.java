package br.com.danyswork.picturesearch.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.List;

import br.com.danyswork.picturesearch.R;
import br.com.danyswork.picturesearch.listener.PictureItemClickListener;
import br.com.danyswork.picturesearch.model.Picture;
import br.com.danyswork.picturesearch.presenter.Presenter;

public class MainActivity extends AppCompatActivity {

    private EditText mEditSearch;
    private ProgressBar mProgressBar;

    private PicturesAdapter mAdapter;
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new Presenter(this);
        mAdapter = new PicturesAdapter(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_pictures);
        mEditSearch = (EditText) findViewById(R.id.edit_picture_search);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ImageView imageClose = (ImageView) findViewById(R.id.image_clear_text);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearContents();
            }
        });

        mEditSearch.addTextChangedListener(new TextWatcher() {

            boolean isUpdating;

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }
                isUpdating = true;
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.cancelSearch();
                mPresenter.search(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEditSearch.setText(R.string.fruits);

        mAdapter.setClickListener(new PictureItemClickListener() {
            @Override
            public View.OnClickListener onClick(final Picture picture) {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage(R.string.dialog_message);
                        builder.setPositiveButton(R.string.label_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //TODO open new activity passing picture
                            }
                        });
                        builder.setNegativeButton(R.string.label_no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                };
            }
        });

        LinearLayoutManager layoutParams = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutParams);

        recyclerView.setAdapter(mAdapter);
    }

    private void clearContents() {
        mEditSearch.setText("");
        mProgressBar.setVisibility(View.GONE);
        mPresenter.cancelSearch();
        mAdapter.clearContent();
    }

    public void setContent(List<Picture> pictures) {
        mProgressBar.setVisibility(View.GONE);
        mAdapter.setContent(pictures);
    }

}
