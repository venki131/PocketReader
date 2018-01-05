package com.example.venkateshkashyap.pocketreader.helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.venkateshkashyap.pocketreader.NetworkManager.NetworkManager;
import com.example.venkateshkashyap.pocketreader.models.BookInfo;
import com.example.venkateshkashyap.pocketreader.utils.DialogUtils;
import com.example.venkateshkashyap.pocketreader.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Venkatesh Kashyap on 1/5/2018.
 */

public class ItemHelper extends BaseHelper {

    private ItemHelper.OnItemResponseReceived mOnItemResponseReceived;
    private static final String TAG = ItemHelper.class.getSimpleName();

    public ItemHelper(Context context) {
        super(context);
    }

    public interface OnItemResponseReceived {
        void onItemResponseReceived(BookInfo item);

        void onFailure();
    }

    public void getItemInfo(final ItemHelper.OnItemResponseReceived onItemResponseReceived, final String book_info) {
        mOnItemResponseReceived = onItemResponseReceived;
        if (NetworkUtil.isConnectionAvailable(mContext)) {
            NetworkManager.getInstance().getBookDetails(mVolumeInfoCallback,book_info);
        } else {
            mOnItemResponseReceived.onFailure();
            Toast.makeText(mContext, "Please Check your Internet Connection and Try Again!", Toast.LENGTH_SHORT).show();
        }
    }

    private Callback<BookInfo> mVolumeInfoCallback = new Callback<BookInfo>() {
        @Override
        public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
            DialogUtils.hideProgressDialog();
            BookInfo item = new BookInfo();
            if (response != null && response.body() != null) {
                item.setItems(response.body().getItems());
                mOnItemResponseReceived.onItemResponseReceived(item);
                Log.d(TAG, "onResponse: " + response.code());
            } else {
            }
        }

        @Override
        public void onFailure(Call<BookInfo> call, Throwable t) {
            mOnItemResponseReceived.onFailure();
        }
    };
}