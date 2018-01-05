package com.example.venkateshkashyap.pocketreader.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.venkateshkashyap.pocketreader.R;
import com.example.venkateshkashyap.pocketreader.models.ImageLinks;

import java.util.List;

/**
 * Created by Venkatesh Kashyap on 1/5/2018.
 */

public class ImageLinksRecyclerViewAdapter extends RecyclerView.Adapter<ImageLinksRecyclerViewAdapter.ViewHolder> {

    private List<ImageLinks> images;
    private Context mContext;

    public ImageLinksRecyclerViewAdapter(Context context,List<ImageLinks> images){
        mContext = context;
        this.images = images;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_thumbnail,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLinks image = images.get(position);

        Glide.with(mContext).load(image.getThumbnail())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail_image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbnail_image;

        public ViewHolder(View itemView) {
            super(itemView);
            thumbnail_image = (ImageView) itemView.findViewById(R.id.img_thumbnail);
        }
    }
}
