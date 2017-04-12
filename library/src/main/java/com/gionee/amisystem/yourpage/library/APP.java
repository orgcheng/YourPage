package com.gionee.amisystem.yourpage.library;

import android.content.Context;

/**
 * Created by orgcheng on 17-4-12.
 *
 * save the right ApplicationContext for different situation
 */

public class APP {
    private static Context sAppContext;

    public static Context getAppContext() {
        return sAppContext;
    }

    public static void setAppContext(Context applicationContext) {
        sAppContext = applicationContext;
    }

}
