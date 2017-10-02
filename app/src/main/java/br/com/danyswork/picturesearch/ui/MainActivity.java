package br.com.danyswork.picturesearch.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import br.com.danyswork.picturesearch.R;
import br.com.danyswork.picturesearch.listener.PictureItemClickListener;
import br.com.danyswork.picturesearch.model.Picture;
import br.com.danyswork.picturesearch.presenter.Presenter;
import br.com.danyswork.picturesearch.util.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_pictures)
    RecyclerView recyclerView;
    @BindView(R.id.edit_picture_search)
    EditText editSearch;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private PicturesAdapter adapter;
    private Presenter presenter;
    private boolean isUpdating;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        presenter = new Presenter(this);
        adapter = new PicturesAdapter(this);

        editSearch.setText(R.string.fruits);

        adapter.setClickListener(new PictureItemClickListener() {
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
                                Intent intent = new Intent(MainActivity.this, PictureDetailActivity.class);
                                intent.putExtra(Constants.PICTURE, picture);
                                startActivity(intent);
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

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @OnClick(R.id.image_clear_text)
    public void onClickClose() {
        clearContents();
    }

    @OnTextChanged(R.id.edit_picture_search)
    public void onTextChanged(CharSequence s) {
        if (isUpdating) {
            isUpdating = false;
            return;
        }
        isUpdating = true;
        progressBar.setVisibility(View.VISIBLE);
        presenter.cancelSearch();
        presenter.search(s.toString());

    }

    private void clearContents() {
        editSearch.setText("");
        progressBar.setVisibility(View.GONE);
        presenter.cancelSearch();
        adapter.clearContent();
    }

    public void setContent(List<Picture> pictures) {
        progressBar.setVisibility(View.GONE);
        adapter.setContent(pictures);
    }

}
