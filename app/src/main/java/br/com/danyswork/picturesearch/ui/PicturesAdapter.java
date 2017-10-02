package br.com.danyswork.picturesearch.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.danyswork.picturesearch.R;
import br.com.danyswork.picturesearch.listener.PictureItemClickListener;
import br.com.danyswork.picturesearch.model.Picture;

class PicturesAdapter extends RecyclerView.Adapter<PictureViewHolder> {

    private List<Picture> data;
    private Context context;
    private PictureItemClickListener clickListener;

    PicturesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item_layout, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {

        if (data.isEmpty())
            return;

        final Picture picture = data.get(position);

        if (picture != null) {
            holder.username.setText(context.getString(R.string.user_name, picture.getUser()));
            holder.tags.setText(context.getString(R.string.tags, picture.getTags()));
            Picasso.with(context)
                    .load(picture.getPreviewURL())
                    .resize(50, 50)
                    .centerCrop()
                    .placeholder(R.drawable.vector_camera)
                    .into(holder.imageThumbnail);
            holder.itemView.setOnClickListener(clickListener.onClick(picture));
        }
    }

    @Override
    public int getItemCount() {
        return (data != null ? data.size() : 0);
    }

    void setContent(List<Picture> pictures) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data.clear();
        }
        if (pictures != null) {
            this.data.addAll(pictures);
        }
        notifyDataSetChanged();
    }

    void clearContent() {
        data.clear();
    }

    void setClickListener(PictureItemClickListener listener) {
        this.clickListener = listener;
    }
}
