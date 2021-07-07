package com.android.guidelib.strategy;

import android.view.View;


/**
 * Created by lizhichao on 7/7/21
 */
public interface IGuiAnimationStrategy {
    boolean filter(View view);
    int calculateWeight(View view);
    void executionAndBuildAnimation(int position, float x, float y, View view, OnAnimEndListener endAction);
}
