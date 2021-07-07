package com.android.guidelib;

import android.content.Context;

import com.android.guidelib.m.model.impl.GuiBuilder;
import com.android.guidelib.strategy.EmptyStrategy;
import com.android.guidelib.strategy.IGuiAnimationStrategy;
import com.android.guidelib.strategy.LavaGuiAnimStrategy;
import com.android.guidelib.strategy.LavaGuiAnimStrategyDecoration;
import com.android.guidelib.strategy.LavaGuiExitStrategy;
import com.android.guidelib.strategy.StrategyFactory;
import com.tencent.mmkv.MMKV;

/**
 * Created by lizhichao on 1/22/21
 */
public interface IGuiApplication{
    GuiBuilder onGuiBuilder(GuiBuilder guiUtils);

    /**
     * 启动gui流程
     * //包含遮罩透明度变化
     * */
    default IGuiAnimationStrategy onGuiAnimEnterStrategyPrepare(){
        return new LavaGuiAnimStrategyDecoration(500,800);
    }

    /**
     * 退出gui流程
     * */
    default IGuiAnimationStrategy onGuiAnimExitStrategyPrepare(){
        return new LavaGuiExitStrategy(500);
    }

    /**
     *gui转场 新的要展示的gui页
     * */
    default IGuiAnimationStrategy onGuiTransitionsAnimEnterStrategyPrepare(){
        return  new LavaGuiAnimStrategy(500,800);
    }
    /**
     * gui转场 旧的 即将退出的gui页面
     * */
    default IGuiAnimationStrategy onGuiTransitionsAnimExitStrategyPrepare(){
        return new EmptyStrategy(); //new EmptyStrategy(); //new LavaGuiAnimStrategy(500,800,true);
    }


    default void initGuiApplication(){
        GuiCache.setRoot(MMKV.initialize((Context) this));
        onGuiBuilder(new GuiBuilder()).push();
        StrategyFactory strategyFactory = GuiAnim.getStrategyFactory();
        strategyFactory.putStrategy(StrategyFactory.Strate.ENTER,onGuiAnimEnterStrategyPrepare());
        strategyFactory.putStrategy(StrategyFactory.Strate.EXIT,onGuiAnimExitStrategyPrepare());
        strategyFactory.putStrategy(StrategyFactory.Strate.TRANSITIONS_ENTER,onGuiTransitionsAnimEnterStrategyPrepare());
        strategyFactory.putStrategy(StrategyFactory.Strate.TRANSITIONS_EXIT,onGuiTransitionsAnimExitStrategyPrepare());
    }

}
