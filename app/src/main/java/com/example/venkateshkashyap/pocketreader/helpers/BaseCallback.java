package com.example.venkateshkashyap.pocketreader.helpers;

import android.content.Context;
import android.util.Log;

import com.example.venkateshkashyap.pocketreader.utils.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Venkatesh Kashyap on 1/4/2018.
 */

public class BaseCallback<T> implements Callback<T> {
    private static final String TAG = BaseCallback.class.getSimpleName();
    private final Context mContext;

    public BaseCallback(Context context) {
        mContext = context;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(TAG, "onFailure: ");
        DialogUtils.hideProgressDialog();
    }
}
