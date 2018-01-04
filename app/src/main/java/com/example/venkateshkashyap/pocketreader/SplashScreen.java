package com.example.venkateshkashyap.pocketreader;

import android.app.Activity;
import android.os.Bundle;

import com.example.venkateshkashyap.pocketreader.constants.Constants;
import com.example.venkateshkashyap.pocketreader.helpers.VolumeInfoHelper;


/**
 * Created by Venkatesh Kashyap on 1/4/2018.
 */

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //new VolumeInfoHelper(this).getVolumeInfo(this, Constants.book_info);
    }
}
