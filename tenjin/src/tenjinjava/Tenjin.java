package com.anvil.tenjin;

import android.app.Activity;

import android.util.Log;

import com.tenjin.android.TenjinSDK;

class Tenjin {
    public static final String TAG = "extension_tenjin";

    private static TenjinSDK tenjinInstance;

    public static final void Init(Activity appActivity, String apiKey, boolean consent) {
        Log.d(TAG, "Init 1");

        try {
            Log.d(TAG, "Init 2");
            Log.d(TAG, "Before getInstance");
            tenjinInstance = TenjinSDK.getInstance(appActivity, apiKey);
            Log.d(TAG, "After getInstance");
            Log.d(TAG, "Before setAppStore");
            tenjinInstance.setAppStore(TenjinSDK.AppStoreType.googleplay);
            Log.d(TAG, "After setAppStore");

            if (consent) {
                tenjinInstance.optIn();
            }
            else {
                tenjinInstance.optOut();
            }

            tenjinInstance.connect();
        } catch (Exception e) {
            Log.d(TAG, "Init 3");
            Log.e(TAG, "Tenjin error", e);
        }

        Log.d(TAG, "Init 4");
    }

    public static final void CustomEvent(String eventName) {
        Log.d(TAG, "CustomEvent");
        tenjinInstance.eventWithName(eventName);
    }

    public static final void CustomEventWithValue(String eventName, String eventValue) {
        tenjinInstance.eventWithNameAndValue(eventName, eventValue);
    }

    public static final void PurchaseEvent(String productId, String currencyCode, int quantity, double unitPrice) {
        tenjinInstance.transaction(productId, currencyCode, quantity, unitPrice);
    }

}
