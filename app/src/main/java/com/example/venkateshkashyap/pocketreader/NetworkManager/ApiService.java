package com.example.venkateshkashyap.pocketreader.NetworkManager;

import com.example.venkateshkashyap.pocketreader.models.BookInfo;
import com.example.venkateshkashyap.pocketreader.models.Item;
import com.example.venkateshkashyap.pocketreader.models.VolumeInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


/**
 * Created by Venkatesh Kashyap on 1/4/2018.
 */

public interface ApiService {

    //URL for getting book information
    String getBookInformationUrl = "books/v1/volumes";

    @GET(getBookInformationUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<BookInfo> getBookDetails(@Query("q") String book_info);

}
