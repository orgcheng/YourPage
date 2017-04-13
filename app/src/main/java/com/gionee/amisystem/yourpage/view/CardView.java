package com.gionee.amisystem.yourpage.view;

import com.gionee.amisystem.yourpage.library.IYourPageService;

public interface CardView{
    void setCardId(int id);
    void setYourPageService(IYourPageService service);

    void onYourPageResume();
    void onYourPagePause();
    void onYourPageRemove();

    void onYourPageRefresh(boolean force);

    String getCardViewName();
    long getLastRefreshTime();
}