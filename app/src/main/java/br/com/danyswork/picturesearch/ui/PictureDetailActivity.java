package br.com.danyswork.picturesearch.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.danyswork.picturesearch.R;
import br.com.danyswork.picturesearch.model.Picture;
import br.com.danyswork.picturesearch.util.Constants;

public class PictureDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        Picture picture = (Picture) getIntent().getSerializableExtra(Constants.PICTURE);

        TextView textUsername = (TextView) findViewById(R.id.text_username);
        TextView textTags = (TextView) findViewById(R.id.text_picture_tags);
        TextView textLikes = (TextView) findViewById(R.id.text_likes);
        TextView textComments = (TextView) findViewById(R.id.text_comments);
        TextView textFavorites = (TextView) findViewById(R.id.text_favorites);

        ImageView imagePicture = (ImageView) findViewById(R.id.image_picture);

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
}
