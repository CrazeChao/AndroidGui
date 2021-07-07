 package com.android.guidelib;
 import android.app.Activity;
 import com.android.guidelib.strategy.StrategyFactory;
 /**
 * Created by lizhichao on 6/23/21
  * 开启效果
 */
public class GuiJumpHelper {
    static StrategyFactory mStrategyUnitFactory;

    public static void start(Activity activity, Class<?> key, StrategyFactory mStrategyUnitFactory){
        GuiJumpHelper.mStrategyUnitFactory = mStrategyUnitFactory;
        GuiActivity.laucher(activity,key.getName());
    }
     public static void start(Activity activity, Class<?> key){
        start(activity,key,null);
     }
}