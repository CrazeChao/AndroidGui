package com.android.guidelib.m;
import com.android.guidelib.m.model.Gui;
import com.android.guidelib.m.model.IGuiDelivery;
import com.android.guidelib.m.model.IGuiUnit;

import java.util.LinkedList;

/**
 * Created by lizhichao on 1/22/21
 * 一个模型对应一个页面
 *
 */
public abstract class GuiModel implements IGuiUnit, IGuiDelivery {
    protected LinkedList<Gui> mGuiQueue = new LinkedList<>();
    public Gui poll(){
        return mGuiQueue.pop();
    }

    public void init(){
        if (init)return;
        init = true;
        mGuiQueue.clear();
        loading(mGuiQueue);
    }

    @Override
    public void restory() {
        init = false;
        init();
    }

    boolean init = false;
}
