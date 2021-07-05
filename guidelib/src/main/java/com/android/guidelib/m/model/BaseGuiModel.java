package com.android.guidelib.m.model;
import com.android.guidelib.m.GuiModel;
import java.util.LinkedList;
/**
 * Created by lizhichao on 1/22/21
 */
public class BaseGuiModel extends GuiModel{
    IGuiUnit mIGuiUnit;

    public BaseGuiModel(IGuiUnit mIGuiUnit) {
        this.mIGuiUnit = mIGuiUnit;
    }

    @Override
    public String getVersion() {
        return mIGuiUnit.getVersion();
    }

    @Override
    public void loading(LinkedList<Gui> queue) {
        mIGuiUnit.loading(queue);
    }
}
