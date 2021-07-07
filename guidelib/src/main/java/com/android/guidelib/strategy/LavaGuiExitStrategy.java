 package com.android.guidelib.strategy;
 import android.view.View;
 import com.android.guidelib.R;
 import com.android.utilslibrary.view.anim.CustomAnim;
 /**
 * Created by lizhichao on 7/7/21
  *
 */
public   class LavaGuiExitStrategy   implements IGuiAnimationStrategy   {
     int duration;
     public LavaGuiExitStrategy(int duration) {
         this.duration = duration;
     }

     @Override
     public boolean filter(View view) {
         return view.getId() == R.id.gui_root;
     }

     @Override
     public int calculateWeight(View view) {
         return 0;
     }

     @Override
     public void executionAndBuildAnimation(int position, float x, float y, View view,OnAnimEndListener endAction) {
         view.animate().alpha(0).setDuration(500).setInterpolator(CustomAnim.getDefaultInterception()).withEndAction(() -> {
             if (endAction == null)return;
                 endAction.onEnd(view);
         });
     }
 }