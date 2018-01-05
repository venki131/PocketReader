package com.example.venkateshkashyap.pocketreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.venkateshkashyap.pocketreader.constants.Constants;
import com.example.venkateshkashyap.pocketreader.models.VolumeInfo;

public class BookInformation extends AppCompatActivity {

    private VolumeInfo mVolumeInfo;
    private ImageView thumbnail;
    private TextView title;
    private TextView author;
    private TextView publisher;
    private TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        thumbnail = (ImageView) findViewById(R.id.img_small_thumbnail);
        title = (TextView) findViewById(R.id.txt_title);
        author = (TextView) findViewById(R.id.txt_authors);
        publisher = (TextView) findViewById(R.id.txt_publishers);
        description = (TextView) findViewById(R.id.txt_description);
        mVolumeInfo = (VolumeInfo) this.getIntent().getParcelableExtra(Constants.VOLUME_INFO_KEY);
        if(mVolumeInfo.getImageLinks().getSmallThumbnail()!=null){
            Glide.with(this).load(mVolumeInfo.getImageLinks().getSmallThumbnail())
                    .thumbnail(0.5f)
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(thumbnail);
        }

        if (!mVolumeInfo.getTitle().isEmpty()){
            title.setText(mVolumeInfo.getTitle());
        }

        if (!mVolumeInfo.getAuthors().isEmpty()){
            author.setText(mVolumeInfo.getAuthors().toString());
        }

        if (!mVolumeInfo.getPublisher().isEmpty()){
            publisher.setText(mVolumeInfo.getPublisher());
        }

        if (!mVolumeInfo.getDescription().isEmpty()){
            description.setText(mVolumeInfo.getDescription());
        }
    }
}
