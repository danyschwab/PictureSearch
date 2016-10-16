package br.com.danyswork.picturesearch.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.danyswork.picturesearch.R;
import br.com.danyswork.picturesearch.listener.PictureItemClickListener;
import br.com.danyswork.picturesearch.model.Picture;
import br.com.danyswork.picturesearch.util.ImageLoader;

class PicturesAdapter extends RecyclerView.Adapter<PictureViewHolder> {

    private List<Picture> mList;
    private ImageLoader mImageLoader;
    private PictureItemClickListener mClickListener;

    PicturesAdapter(Context context) {
        mImageLoader = new ImageLoader(context);
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item_layout, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {

        if (mList.isEmpty())
            return;

        final Picture picture = mList.get(position);

        if (picture != null) {
            holder.mUserName.setText(picture.getUser());
            holder.mTags.setText(picture.getTags());
            mImageLoader.displayImage(String.valueOf(picture.getPreviewURL()), holder.mImageThumbnail);
            holder.itemView.setOnClickListener(mClickListener.onClick(picture));
        }
    }

    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }

    void setContent(Picture picture) {
        if (this.mList == null) {
            this.mList = new ArrayList<>();
        }
        if (picture != null) {
            if (!mList.contains(picture)) {
                this.mList.add(picture);
            }
        }
        notifyDataSetChanged();
    }

    void clearContent() {
        mList.clear();
    }

    void setClickListener(PictureItemClickListener listener){
        this.mClickListener = listener;
    }
}
