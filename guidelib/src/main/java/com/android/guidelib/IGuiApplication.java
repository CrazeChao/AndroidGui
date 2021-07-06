package com.android.guidelib;

import android.content.Context;

import com.android.guidelib.m.GuiManager;
import com.android.guidelib.m.model.impl.GuiBuilder;
import com.tencent.mmkv.MMKV;

/**
 * Created by lizhichao on 1/22/21
 */
public interface IGuiApplication{
    default void setRoot(String root){
        GuiCache.setRoot(root);
        onGuiBuilder(new GuiBuilder()).push();
    }

    GuiBuilder onGuiBuilder(GuiBuilder guiUtils);

    default void initGuiApplication(){
        setRoot(MMKV.initialize((Context) this));
    }

    default void setGuiUnitAnimDuration(long unitAnimDuration){
        GuiAnim.animDuration = unitAnimDuration;
    }
    default void setGuiUnitAnimStartIntervalTime(long guiAnimStartIntervalTime){
        GuiAnim.animintervalTime = guiAnimStartIntervalTime;
    }
}
