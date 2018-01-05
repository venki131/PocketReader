package com.example.venkateshkashyap.pocketreader.utils;

import android.app.ProgressDialog;

/**
 * Created by Venkatesh Kashyap on 1/4/2018.
 */

public class DialogUtils {

    private static ProgressDialog sProgressDialog;
    /**
     * Dismisses progress dialog.
     */
    public static void hideProgressDialog() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            try {
                sProgressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            sProgressDialog = null;
        }
    }
}
