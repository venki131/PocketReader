package com.example.venkateshkashyap.pocketreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.venkateshkashyap.pocketreader.adapters.ImageLinksRecyclerViewAdapter;
import com.example.venkateshkashyap.pocketreader.constants.Constants;
import com.example.venkateshkashyap.pocketreader.helpers.ItemHelper;
import com.example.venkateshkashyap.pocketreader.models.BookInfo;
import com.example.venkateshkashyap.pocketreader.models.Item;
import com.example.venkateshkashyap.pocketreader.utils.DialogUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemHelper.OnItemResponseReceived {

    private ImageLinksRecyclerViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Item> images;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        images = new ArrayList<>();
        mAdapter = new ImageLinksRecyclerViewAdapter(getApplicationContext(),images);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, Constants.NUM_OF_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        new ItemHelper(this).getItemInfo(this,Constants.BOOK_INFO);
    }

    @Override
    public void onItemResponseReceived(BookInfo item) {
        mProgressBar.setVisibility(View.INVISIBLE);
        DialogUtils.hideProgressDialog();
        mAdapter.setList(item);
    }


    @Override
    public void onFailure() {
        Toast.makeText(this,"Try again!",Toast.LENGTH_LONG).show();
    }
}
