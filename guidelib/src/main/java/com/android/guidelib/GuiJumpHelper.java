 package com.android.guidelib;
 import android.app.Activity;
 /**
 * Created by lizhichao on 6/23/21
  * 开启效果
 */
public class GuiJumpHelper {
    public static void start(Activity activity, Class<?> key){
        GuiActivity.into(activity,key.getName());
    }
}