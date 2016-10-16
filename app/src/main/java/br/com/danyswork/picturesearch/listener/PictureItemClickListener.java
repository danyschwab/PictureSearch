package br.com.danyswork.picturesearch.listener;


import android.view.View;

import br.com.danyswork.picturesearch.model.Picture;

public abstract class PictureItemClickListener {

    public abstract View.OnClickListener onClick(Picture picture);
}
