 package com.android.guidelib.strategy;

 import android.util.Log;
 import android.view.View;
 import android.widget.TextView;

 import com.android.guidelib.R;
 import com.android.utilslibrary.view.anim.CustomAnim;

 import static com.android.utilslibrary.view.anim.CustomAnim.getDefaultInterception;

 /**
 * Created by lizhichao on 7/7/21
  * 包含有进场动画的策略
  *decoration
  * 控件透明度 控件移动  + 整体透明度变化的动效
 */

 public class LavaGuiAnimStrategyDecoration implements IGuiAnimationStrategy {
     LavaGuiAnimStrategy decoration;
     public LavaGuiAnimStrategyDecoration(long animintervalTime, long animDuration) {
         decoration = new LavaGuiAnimStrategy(animintervalTime, animDuration);
     }

     @Override
     public boolean filter(View view) {
         if (view.getId() == R.id.gui_cover) return true;
         return decoration.filter(view);
     }

     @Override
     public void executionAndBuildAnimation(int position, float x, float y, View view,OnAnimEndListener endAction) {
         if (view.getId() != R.id.gui_cover) {
             decoration.executionAndBuildAnimation(position, x, y, view,endAction);
             return;
         }
         /**
          * 启动背景渐变动画
          * */
         View converView = view;
         float coverAlpha = view.getAlpha();
         converView.setAlpha(0);
         converView.animate().alpha(coverAlpha).setInterpolator(getDefaultInterception()).setDuration(1000).withEndAction(() -> {
             if (endAction == null)return;
             endAction.onEnd(converView);
         }).start();
     }

     @Override
     public int calculateWeight(View view) {
         if (view.getId() == R.id.gui_cover) return -1;
         return decoration.calculateWeight(view);
     }
 }