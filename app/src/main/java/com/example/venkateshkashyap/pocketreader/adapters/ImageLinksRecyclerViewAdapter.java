package com.example.venkateshkashyap.pocketreader.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.venkateshkashyap.pocketreader.BookInformation;
import com.example.venkateshkashyap.pocketreader.R;
import com.example.venkateshkashyap.pocketreader.constants.Constants;
import com.example.venkateshkashyap.pocketreader.models.BookInfo;
import com.example.venkateshkashyap.pocketreader.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Venkatesh Kashyap on 1/5/2018.
 */

public class ImageLinksRecyclerViewAdapter extends RecyclerView.Adapter<ImageLinksRecyclerViewAdapter.ViewHolder> {

    private List<Item> images = new ArrayList<>();
    private Context mContext;
    private String thumbnail_image;
    private BookInfo bookInfo = new BookInfo();
    public ImageLinksRecyclerViewAdapter(Context context,List<Item> images){
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        images = bookInfo.getItems();
        holder.item = images.get(position);
        thumbnail_image = images.get(position).getVolumeInfo().getImageLinks().getThumbnail();
        Glide.with(mContext).load(thumbnail_image)
                .thumbnail(0.5f)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail_image);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookInformation.class);
                intent.putExtra(Constants.VOLUME_INFO_KEY,holder.item.getVolumeInfo());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setList(BookInfo item) {
        bookInfo = item;
        images.addAll(bookInfo.getItems());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbnail_image;
        public final View mView;
        public Item item;
        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            thumbnail_image = (ImageView) itemView.findViewById(R.id.img_thumbnail);
        }
    }
}
