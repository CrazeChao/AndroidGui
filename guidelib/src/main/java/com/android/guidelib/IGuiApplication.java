package com.android.guidelib;

import android.content.Context;

import com.android.guidelib.m.GuiManager;
import com.android.guidelib.m.model.impl.GuiBuilder;
import com.tencent.mmkv.MMKV;

/**
 * Created by lizhichao on 1/22/21
 */
public interface IGuiApplication{
    default void create(){
         setRoot(MMKV.initialize((Context) this));
    }
    default void setRoot(String root){
        GuiCache.setRoot(root);
        new GuiBuilder()
                .createGuiUnit("gui1","1.0.0")
                .addGui(R.layout.gui_tempo_1)
                .addGui(R.layout.gui_tempo_2)
                .commit()
                .createGuiUnit("gui2","1.0.0")
                .addGui(R.layout.gui_tempo_3)
                .commit()
                .apply(GuiManager.getG().getMap());
    }
}
