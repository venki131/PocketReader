package com.example.venkateshkashyap.pocketreader.NetworkManager;

import android.util.Log;

import com.example.venkateshkashyap.pocketreader.constants.Constants;
import com.example.venkateshkashyap.pocketreader.models.BookInfo;
import com.example.venkateshkashyap.pocketreader.models.Item;
import com.example.venkateshkashyap.pocketreader.models.VolumeInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Venkatesh Kashyap on 1/4/2018.
 */

public class NetworkManager {
    private static final String TAG = NetworkManager.class.getSimpleName();
    private static NetworkManager ourInstance;
    private ApiService apiService;

    public static NetworkManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new NetworkManager();
        }
        return ourInstance;
    }

    private Retrofit getRetroFit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getBookDetails(Callback<BookInfo> itemCallback, String book_info){
        Log.d(TAG, "getBookDetails");
        ApiService apiService = getApiService();
        Call<BookInfo> itemCall = apiService.getBookDetails(book_info);
        itemCall.enqueue(itemCallback);
    }

    private ApiService getApiService() {

        if (apiService == null)
            apiService = getRetroFit().create(ApiService.class);

        return apiService;
    }
}
