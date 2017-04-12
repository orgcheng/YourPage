package com.gionee.amisystem.yourpage.view;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by ke on 16-7-28.
 */
public class YourPageView extends LinearLayout{
    private static final String TAG = "YourPageView";

    public YourPageView(Context context) {
        super(context);
    }

//    private SearchCard mSearchCard;
//    private int mGap;
//    private int mPaddingLeftRight;
//    private int mMarginTop;
//
//    public YourPageView(Context context, Context launcherContext) {
//        super(context);
//
//        // inject Application to library, remember to release
//        APP.setAppContext(launcherContext);
//
//        init(context);
//        initChildViews(context, launcherContext);
//    }
//
//    private void init(Context context) {
//        setOrientation(VERTICAL);
//        setBackground(null);
//        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//        setLayoutParams(lp);
//        EncodeImeiUtils.getInstance().initImei(context);
//        mGap = Utils.dp2px(context, 10);
//        mPaddingLeftRight = Utils.dp2px(context, 14);
//        mMarginTop = Utils.dp2px(context, 31);
//    }
//
//    private void initChildViews(Context context, Context launcherContext) {
//        mSearchCard = new SearchCard(context);
//        LinearLayout.LayoutParams searchCardLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        searchCardLp.gravity = Gravity.CENTER;
//        searchCardLp.height = context.getResources().getDimensionPixelSize(R.dimen.search_card_height);
//        searchCardLp.width = Utils.dp2px(context, 332);
//        searchCardLp.setMargins(mPaddingLeftRight, mMarginTop, mPaddingLeftRight, 0);
//        mSearchCard.setLayoutParams(searchCardLp);
//        addView(mSearchCard, 0);
//
//        mContentContainer = new ContentContainer(context, launcherContext);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(context.getResources().getDisplayMetrics().widthPixels,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        lp.topMargin = mGap;
//        mContentContainer.setLayoutParams(lp);
//        addView(mContentContainer, 1);
//    }


////////分割线
    public void switchToCardManager(){
        // 跳转到卡片管理界面
    }

    public void onYourPageSelected(boolean selected) {
        // 卡片调用 onYourPageSelected
    }

    public void onYourPageResume(){
        // 卡片调用 onYourPageResume
    }

    public void onYourPagePause(){
        // 卡片调用 onYourPagePause
    }


    public void onYourPageRemove(){
        // 卡片调用 onYourPageRemove
    }
}
