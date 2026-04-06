package com.anvil.tenjin;

import android.app.Activity;

import android.util.Log;

import com.tenjin.android.TenjinSDK;

class Tenjin {
    public static final String TAG = "extension_tenjin";

    private static TenjinSDK tenjinInstance;

    public static final void Init(Activity appActivity, String apiKey, boolean consent) {
        Log.d(TAG, "Init 1");

        tenjinInstance = TenjinSDK.getInstance(appActivity, apiKey);
        tenjinInstance.setAppStore(TenjinSDK.AppStoreType.googleplay);

        if (consent) {
            tenjinInstance.optIn();
        }
        else {
            tenjinInstance.optOut();
        }

        tenjinInstance.connect();

        Log.d(TAG, "Init 2");
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
