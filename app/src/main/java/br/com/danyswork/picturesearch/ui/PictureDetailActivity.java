package br.com.danyswork.picturesearch.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.danyswork.picturesearch.R;
import br.com.danyswork.picturesearch.model.Picture;
import br.com.danyswork.picturesearch.util.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PictureDetailActivity extends AppCompatActivity {

    @BindView(R.id.text_username)
    TextView textUsername;
    @BindView(R.id.text_picture_tags)
    TextView textTags;
    @BindView(R.id.text_likes)
    TextView textLikes;
    @BindView(R.id.text_comments)
    TextView textComments;
    @BindView(R.id.text_favorites)
    TextView textFavorites;
    @BindView(R.id.image_picture)
    ImageView imagePicture;

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        unbinder = ButterKnife.bind(this);

        Picture picture = (Picture) getIntent().getSerializableExtra(Constants.PICTURE);

        textUsername.setText(getString(R.string.user_name, picture.getUser()));
        textTags.setText(getString(R.string.tags, picture.getTags()));
        textLikes.setText(String.valueOf(picture.getLikes()));
        textComments.setText(String.valueOf(picture.getComments()));
        textFavorites.setText(String.valueOf(picture.getFavorites()));
        Picasso.with(this)
                .load(picture.getPreviewURL())
                .placeholder(R.drawable.vector_camera)
                .into(imagePicture);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
