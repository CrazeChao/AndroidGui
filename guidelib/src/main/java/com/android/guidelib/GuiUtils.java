 package com.android.guidelib;

 import android.app.Activity;

 /**
 * Created by lizhichao on 6/23/21
 */
public   class GuiUtils {
    public static void start(Activity activity, String key){
        GuiActivity.into(activity,key);
    }
}