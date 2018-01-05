package com.example.venkateshkashyap.pocketreader.helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.venkateshkashyap.pocketreader.NetworkManager.NetworkManager;
import com.example.venkateshkashyap.pocketreader.models.VolumeInfo;
import com.example.venkateshkashyap.pocketreader.utils.DialogUtils;
import com.example.venkateshkashyap.pocketreader.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Venkatesh Kashyap on 1/4/2018.
 */

public class VolumeInfoHelper extends BaseHelper {

    private VolumeInfoHelper.OnVolumeInfoResponseReceived mOnVolumeInfoResponseReceived;

    public VolumeInfoHelper(Context context) {
        super(context);
    }

    public interface OnVolumeInfoResponseReceived {
        void onVolumeInfoResponseReceived(VolumeInfo volumeInfo);
        void onFailure();
    }

    private static final String TAG = VolumeInfoHelper.class.getSimpleName();

    public void getVolumeInfo(final VolumeInfoHelper.OnVolumeInfoResponseReceived onVolumeInfoResponseReceived,final String book_info) {
        mOnVolumeInfoResponseReceived = onVolumeInfoResponseReceived;

        if(NetworkUtil.isConnectionAvailable(mContext)) {
            NetworkManager.getInstance().getBookDetails(mVolumeInfoCallback,book_info);
        }else{
            mOnVolumeInfoResponseReceived.onFailure();
            Toast.makeText(mContext, "Please Check your Internet Connection and Try Again!", Toast.LENGTH_SHORT).show();
        }

    }
    private Callback<VolumeInfo> mVolumeInfoCallback = new Callback<VolumeInfo>() {
        @Override
        public void onResponse(Call<VolumeInfo> call, Response<VolumeInfo> response) {
            DialogUtils.hideProgressDialog();
            VolumeInfo volumeInfo = new VolumeInfo();
            if (response != null && response.body() != null) {
                volumeInfo.setTitle(response.body().getTitle());
                volumeInfo.setAuthors(response.body().getAuthors());
                volumeInfo.setDescription(response.body().getDescription());
                volumeInfo.setPublisher(response.body().getPublisher());
                //volumeInfo.setImageLinks(response.body().getImageLinks());
                mOnVolumeInfoResponseReceived.onVolumeInfoResponseReceived(volumeInfo);
                Log.d(TAG, "onResponse: " + response.code());
            }else {
            }
        }

        @Override
        public void onFailure(Call<VolumeInfo> call, Throwable t) {
            mOnVolumeInfoResponseReceived.onFailure();
        }
    };


}
