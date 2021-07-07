 package com.android.guidelib.strategy;
 import android.view.View;
 import android.widget.TextView;
 import com.android.utilslibrary.view.anim.CustomAnim;

 /**
 * Created by lizhichao on 7/7/21
  * 拿火第一版策略
  *decoration
  * 只有控件透明度和 控件移动的动画
 */
public   class LavaGuiAnimStrategy implements IGuiAnimationStrategy   {
     public  long animintervalTime;
     public  long animDuration;

     public LavaGuiAnimStrategy(long animintervalTime, long animDuration) {
         this.animintervalTime = animintervalTime;
         this.animDuration = animDuration;
     }

     @Override
     public boolean filter(View view) {
         return view instanceof TextView && view.getAlpha()>0;
     }

     @Override
     public void executionAndBuildAnimation(int position, float x, float y, View view,OnAnimEndListener endAction) {
         long currentStartDelayTime = animintervalTime * position;
         String data = (String) view.getTag();
         int inval = 100;
         try {
             inval = Integer.parseInt(data);
         } catch (NumberFormatException numberFormatException) {
         }
         float startTranslation_Y = view.getTranslationY() + inval;
         float endTranslation_Y = view.getTranslationY();
         float alpha = view.getAlpha();
         view.setTranslationY(startTranslation_Y);
         view.setAlpha(0f);
         view.animate().translationY(endTranslation_Y).alpha(alpha).setInterpolator(CustomAnim.getDefaultInterception()).setStartDelay(currentStartDelayTime)
                 .setDuration(animDuration).withEndAction(() -> {
                     if (endAction == null)return;
                         endAction.onEnd(view);

                 }).start();
     }

     @Override
     public int calculateWeight(View view) {
         return (int)view.getY();
     }
 }