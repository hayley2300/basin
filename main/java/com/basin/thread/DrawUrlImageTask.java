package com.basin.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;

public class DrawUrlImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView ivSample;

    public DrawUrlImageTask(ImageView ivSample) {
        this.ivSample = ivSample;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bitmap = null;
        InputStream in = null;

        try {
            in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ivSample.setImageBitmap(bitmap);

        // TODO ... 가져온 메타데이터로 뭘 할지 적어주면됨
        //Glide.with(context).load(metadata.imageUrl).into(holder.ivMetadataImage);
        //holder.tvMetadataTitle.setText(metadata.shorten(metadata.title));
       // holder.tvMetadataUrl.setText(metadata.shorten(metadata.url));
        //holder.rlMetadata.setVisibility(View.VISIBLE);
    }
}

