 package com.android.guidelib.strategy;
 import java.util.HashMap;
 /**
 * Created by lizhichao on 7/7/21
 * //简单的策略工厂
 */
 public class StrategyFactory{
    public enum Strate{
       ENTER,EXIT,TRANSITIONS_ENTER,TRANSITIONS_EXIT;
    }
    HashMap<Strate,IGuiAnimationStrategy> factoryStore;
    public void putStrategy(Strate strate,IGuiAnimationStrategy iGuiAnimationStrategy){
       if (factoryStore == null){
          factoryStore = new HashMap<>();
       }
       factoryStore.put(strate,iGuiAnimationStrategy);
    }

    public IGuiAnimationStrategy getStrategy(Strate strate){
       return factoryStore.get(strate);
    }

}