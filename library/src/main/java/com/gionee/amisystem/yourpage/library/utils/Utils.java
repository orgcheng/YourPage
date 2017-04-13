package com.gionee.amisystem.yourpage.library.utils;

import android.content.Context;

/**
 * Created by orgcheng on 17-4-13.
 */

public class Utils {
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5);
    }
}
