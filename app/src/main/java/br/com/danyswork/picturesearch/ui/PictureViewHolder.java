package br.com.danyswork.picturesearch.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.danyswork.picturesearch.R;
import butterknife.BindView;
import butterknife.ButterKnife;

class PictureViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_thumbnail_picture)
    ImageView imageThumbnail;
    @BindView(R.id.text_username)
    TextView username;
    @BindView(R.id.text_picture_tags)
    TextView tags;

    PictureViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
