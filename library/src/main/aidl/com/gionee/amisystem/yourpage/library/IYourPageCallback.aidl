// IYourPageCallback.aidl
package com.gionee.amisystem.yourpage.library;

// Declare any non-default types here with import statements

oneway interface IYourPageCallback {
    oneway void onCardDataReceived(String data);
}
