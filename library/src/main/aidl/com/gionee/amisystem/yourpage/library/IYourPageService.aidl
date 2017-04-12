// IYourPageService.aidl
package com.gionee.amisystem.yourpage.library;

import android.content.Intent;

// Declare any non-default types here with import statements
import com.gionee.amisystem.yourpage.library.IYourPageCallback;
import com.gionee.amisystem.yourpage.library.ICardViewCallback;

oneway interface IYourPageService {

    oneway void registerYourPageCallback(IYourPageCallback callback);
    oneway void unRegisterYourPageCallback(IYourPageCallback callback);

    oneway void registerCardViewCallback(ICardViewCallback callback);
    oneway void unRegisterCardViewCallback(ICardViewCallback callback);

    oneway void onYourPagePause();
    oneway void onYourPageRemuse();

    oneway void onEventForStatistics(String var1);
    oneway void onEventForStatisticsForParams(String params);
    oneway void onEventForStatisticsForValue(String var1, String var2);

    oneway void onCloseYourPage();


    oneway void switchToActivity(in Intent intent);
    oneway void startLocation();
}
