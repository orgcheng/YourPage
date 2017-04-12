// ICardViewCallback.aidl
package com.gionee.amisystem.yourpage.library;

// Declare any non-default types here with import statements

oneway interface ICardViewCallback {
    oneway void onLocationReceived(String data);
}
