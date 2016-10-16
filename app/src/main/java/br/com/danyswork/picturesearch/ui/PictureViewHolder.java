package br.com.danyswork.picturesearch.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.danyswork.picturesearch.R;

class PictureViewHolder extends RecyclerView.ViewHolder {

    ImageView mImageThumbnail;
    TextView mUserName;
    TextView mTags;

    PictureViewHolder(View view) {
        super(view);
        mImageThumbnail = (ImageView) view.findViewById(R.id.image_thumbnail_picture);
        mUserName = (TextView) view.findViewById(R.id.text_user_name);
        mTags = (TextView) view.findViewById(R.id.text_picture_tags);
    }
}
