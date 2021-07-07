 package com.android.guidelib.strategy;

 import android.view.View;

 /**
 * Created by lizhichao on 7/7/21
  * 空策略
 */
public   class EmptyStrategy implements IGuiAnimationStrategy   {
     @Override
     public boolean filter(View view) {
         return false;
     }

     @Override
     public int calculateWeight(View view) {
         return 0;
     }

     @Override
     public void executionAndBuildAnimation(int position, float x, float y, View view, OnAnimEndListener endAction) {

     }
 }