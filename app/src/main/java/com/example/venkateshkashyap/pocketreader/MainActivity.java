package com.example.venkateshkashyap.pocketreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.venkateshkashyap.pocketreader.adapters.ImageLinksRecyclerViewAdapter;
import com.example.venkateshkashyap.pocketreader.constants.Constants;
import com.example.venkateshkashyap.pocketreader.helpers.VolumeInfoHelper;
import com.example.venkateshkashyap.pocketreader.models.ImageLinks;
import com.example.venkateshkashyap.pocketreader.models.VolumeInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements VolumeInfoHelper.OnVolumeInfoResponseReceived {

    private ImageLinksRecyclerViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private ArrayList<ImageLinks> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        images = new ArrayList<>();
        mAdapter = new ImageLinksRecyclerViewAdapter(getApplicationContext(),images);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, Constants.NUM_OF_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        new VolumeInfoHelper(this).getVolumeInfo(this,Constants.book_info);
    }

    @Override
    public void onVolumeInfoResponseReceived(VolumeInfo volumeInfo) {
        if (volumeInfo != null)
            volumeInfo.setImageLinks(volumeInfo.getImageLinks());
    }

    @Override
    public void onFailure() {

    }
}
