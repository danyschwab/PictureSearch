package br.com.danyswork.picturesearch.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.danyswork.picturesearch.R;

public class ImageLoader {

    private static final int STUB_ID = R.drawable.default_picture;

    private Context mContext;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    private ExecutorService executorService;

    public ImageLoader(Context context) {
        executorService = Executors.newFixedThreadPool(5);
        mContext = context;
    }

    public void displayImage(String imageId, ImageView imageView) {
        imageViews.put(imageView, imageId);
        Bitmap bitmap = Utils.getResId(mContext, imageId);

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            queuePhoto(imageId, imageView);
            imageView.setImageResource(STUB_ID);
        }
    }

    private void queuePhoto(String fileName, ImageView imageView) {
        PictureToLoad p = new PictureToLoad(fileName, imageView);
        executorService.submit(new PictureLoader(p));
    }

    private Bitmap getBitmap(String fileName) {
        try {
            java.net.URL url = new java.net.URL(fileName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            File destFile = new File(mContext.getFilesDir(), fileName);
            OutputStream os = new FileOutputStream(destFile);
            Utils.CopyStream(input, os);
            return Utils.getResId(mContext, fileName);
        } catch (IOException e) {
            return null;
        }
    }


    //Task for the queue
    private class PictureToLoad {
        String fileName;
        ImageView imageView;

        PictureToLoad(String f, ImageView i) {
            fileName = f;
            imageView = i;
        }
    }

    private class PictureLoader implements Runnable {
        PictureToLoad pictureToLoad;

        PictureLoader(PictureToLoad pictureToLoad) {
            this.pictureToLoad = pictureToLoad;
        }

        @Override
        public void run() {
            BitmapDisplay bd = new BitmapDisplay(getBitmap(pictureToLoad.fileName), pictureToLoad);
            Activity a = (Activity) pictureToLoad.imageView.getContext();
            a.runOnUiThread(bd);
        }
    }

    private boolean imageViewReused(PictureToLoad pictureToLoad) {
        String tag = imageViews.get(pictureToLoad.imageView);
        return tag != null && tag.equals(pictureToLoad.fileName);
    }

    //Used to display bitmap in the UI thread
    private class BitmapDisplay implements Runnable {

        Bitmap bitmap;
        PictureToLoad pictureToLoad;

        BitmapDisplay(Bitmap b, PictureToLoad p) {
            bitmap = b;
            pictureToLoad = p;
        }

        public void run() {
            if (!imageViewReused(pictureToLoad)) {
                return;
            }

            if (bitmap != null) {
                pictureToLoad.imageView.setImageBitmap(bitmap);
            } else {
                pictureToLoad.imageView.setImageResource(STUB_ID);
            }
        }
    }
}