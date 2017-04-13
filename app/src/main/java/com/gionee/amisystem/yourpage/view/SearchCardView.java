package com.gionee.amisystem.yourpage.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.gionee.amisystem.yourpage.R;
import com.gionee.amisystem.yourpage.library.IYourPageService;

public class SearchCardView extends LinearLayout implements CardView, View.OnClickListener {

    private IYourPageService mService;
    private int mId;

    private static final int ENTERTYPE = 15;
    private static final int SCANENTERTYPE = 106;

    public SearchCardView(Context context) {
        super(context);
        inits(context);
    }

    private void inits(Context context) {
        setOrientation(HORIZONTAL);
        setBackgroundResource(R.drawable.yourpage_bg_search_shape);
        LayoutInflater.from(context).inflate(R.layout.yourpage_search_layout, this);
        findViewById(R.id.yourpage_search_text).setOnClickListener(this);
        findViewById(R.id.yourpage_search_btn).setOnClickListener(this);
        findViewById(R.id.yourpage_search_scanner_btn).setOnClickListener(this);
    }

    @Override
    public void setCardId(int id) {
        mId = id;
    }

    @Override
    public void setYourPageService(IYourPageService service) {
        mService = service;
    }

    @Override
    public void onYourPageResume() {

    }

    @Override
    public void onYourPagePause() {

    }

    @Override
    public void onYourPageRemove() {
        mService = null;
    }

    @Override
    public void onYourPageRefresh(boolean force) {

    }

    @Override
    public String getCardViewName() {
        return null;
    }

    @Override
    public long getLastRefreshTime() {
        return 0;
    }


    @Override
    public void onClick(View v) {
        try {
            if (R.id.yourpage_search_scanner_btn == v.getId()) {
                Intent intent = new Intent("com.gionee.yourpage.scanner");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(intent);
//                StatisticsUtil.Statistics_Event_Common(getContext(), mService,
//                        StatisticConstant.EVENT_YOURSPAGE_CARD_SEARCH_CLICK_SCANNER_TIMES);
            } else {
                Intent intent = new Intent("com.gionee.yourpage.search");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(intent);
//                StatisticsUtil.Statistics_Event_Common(getContext(), mService,
//                        StatisticConstant.EVENT_YOURSPAGE_CARD_SEARCH_CLICK_TIMES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}