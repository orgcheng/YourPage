package com.gionee.amisystem.yourpage.view;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gionee.amisystem.yourpage.R;
import com.gionee.amisystem.yourpage.library.APP;
import com.gionee.amisystem.yourpage.library.utils.LogUtils;
import com.gionee.amisystem.yourpage.library.utils.Utils;

/**
 * Created by ke on 16-7-28.
 */
public class YourPageView extends LinearLayout{
    private static final String TAG = "YourPageView";

    private SearchCardView mSearchCardView;
    private int mGap;
    private int mPaddingLeftRight;
    private int mMarginTop;

    public YourPageView(Context context, Context launcherContext) {
        super(context);

        init(context, launcherContext);
        initChildViews(context, launcherContext);
    }



    private void init(Context context, Context launcherContext) {
        // inject Application to library, remember to release
        APP.setAppContext(launcherContext);

        setOrientation(VERTICAL);
        setBackground(null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(lp);

//        EncodeImeiUtils.getInstance().initImei(context);

        mGap = (int) context.getResources().getDimension(com.gionee.amisystem.yourpage.library.R.dimen.yourpage_10dp);
        mMarginTop = (int) context.getResources().getDimension(com.gionee.amisystem.yourpage.library.R.dimen.yourpage_31dp);
        mPaddingLeftRight = (int) context.getResources().getDimension(com.gionee.amisystem.yourpage.library.R.dimen.yourpage_14dp);
    }

    private void initChildViews(Context context, Context launcherContext) {
        mSearchCardView = new SearchCardView(context);
        LayoutParams searchCardLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        searchCardLp.gravity = Gravity.CENTER;
        searchCardLp.height = context.getResources().getDimensionPixelSize(R.dimen.yourpage_search_card_height);
        searchCardLp.width = Utils.dp2px(context, 332);
        searchCardLp.setMargins(mPaddingLeftRight, mMarginTop, mPaddingLeftRight, 0);
        mSearchCardView.setLayoutParams(searchCardLp);
        addView(mSearchCardView, 0);

//        mContentContainer = new ContentContainer(context, launcherContext);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(context.getResources().getDisplayMetrics().widthPixels,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        lp.topMargin = mGap;
//        mContentContainer.setLayoutParams(lp);
//        addView(mContentContainer, 1);
    }


////////分割线
    public void switchToCardManager(){
        // 跳转到卡片管理界面
        LogUtils.d("switchToCardManager");
    }

    public void onYourPageSelected(boolean selected) {
        // 卡片调用 onYourPageSelected
        LogUtils.d("onYourPageSelected selected = " + selected);
    }

    public void onYourPageResume(){
        // 卡片调用 onYourPageResume
        LogUtils.d("onYourPageResume");
    }

    public void onYourPagePause(){
        // 卡片调用 onYourPagePause
        LogUtils.d("onYourPagePause");
    }


    public void onYourPageRemove(){
        // 卡片调用 onYourPageRemove
        LogUtils.d("onYourPageRemove");

        // relealse Application from library
        APP.setAppContext(null);
    }
}
