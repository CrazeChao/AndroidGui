 package com.android.guidelib;
 import android.view.View;
 import android.view.ViewGroup;
 import android.view.ViewTreeObserver;
 import com.android.guidelib.strategy.IGuiAnimationStrategy;
 import com.android.guidelib.strategy.OnAnimEndListener;
 import com.android.guidelib.strategy.StrategyFactory;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Queue;
 /**
 * Created by lizhichao on 7/6/21
 */
 class GuiAnim {
     private final static  StrategyFactory mStrategyFactory = new StrategyFactory();

     public static StrategyFactory getStrategyFactory() {
         if (GuiJumpHelper.mStrategyUnitFactory != null){
             return  GuiJumpHelper.mStrategyUnitFactory;
         }
         return mStrategyFactory;
     }

     public static void prepareEnterAnimation(ViewGroup group, OnAnimEndListener endAction) {
         prepareAnim(group, getStrategyFactory().getStrategy(StrategyFactory.Strate.ENTER), getSafetyAnimEndListener(endAction) );
     }

     public static void prepareExitAnim(ViewGroup group, OnAnimEndListener endAction) {
         prepareAnim(group, getStrategyFactory().getStrategy(StrategyFactory.Strate.EXIT), getSafetyAnimEndListener(endAction));
     }

     public static void prepareTransitionExitAnim(ViewGroup group, OnAnimEndListener endAction) {
         prepareAnim(group, getStrategyFactory().getStrategy(StrategyFactory.Strate.TRANSITIONS_EXIT), getSafetyAnimEndListener(endAction) );
         group.requestLayout();
     }

     public static void prepareTransitionEnterAnim(ViewGroup group, OnAnimEndListener endAction) {
         prepareAnim(group, getStrategyFactory().getStrategy(StrategyFactory.Strate.TRANSITIONS_ENTER),getSafetyAnimEndListener(endAction) );
         group.requestLayout();
     }



     public static void animSalfeEnd(View view, OnAnimEndListener endListener) {
         if (endListener == null) return;
         endListener.onEnd(view);
     }
     public static OnAnimEndListener getSafetyAnimEndListener(OnAnimEndListener endAction){
         if (endAction == null){
             return view -> {

             };
         }
         return endAction;
     }

     public static void prepareAnim(ViewGroup group, IGuiAnimationStrategy iGuiAnimationStrategy, OnAnimEndListener endAction) {
         List<View> views = filterAnimView(group, iGuiAnimationStrategy);
         if (views.size() == 0) {
             animSalfeEnd(group, endAction);
             return;
         }

         AnimEndVisitor animEndVisitor = new AnimEndVisitor(group,endAction);
         group.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
             @Override
             public boolean onPreDraw() {
                 Collections.sort(views, (o1, o2) -> Integer.compare(iGuiAnimationStrategy.calculateWeight(o1), iGuiAnimationStrategy.calculateWeight(o2)));
                 for (int i = 0; i < views.size(); i++) {
                     View view = views.get(i);
                     AnimEndPart animEndPart = new AnimEndPart(endAction);
                     animEndPart.accept(animEndVisitor);
                     iGuiAnimationStrategy.executionAndBuildAnimation(i, view.getX(), view.getY(), view,animEndPart); //计数器
                 }
                 group.getViewTreeObserver().removeOnPreDrawListener(this);
                 return false;
             }
         });
     }
     private static List<View> filterAnimView(ViewGroup group, IGuiAnimationStrategy iGuiAnimationStrategy) {
         List<View> textViews = new LinkedList<>();
         for (int i = 0; i < group.getChildCount(); i++) {
             View childView = group.getChildAt(i);
             if (iGuiAnimationStrategy.filter(childView)) {
                 textViews.add(childView);
             }
         }
         View rootView = group.findViewById(R.id.gui_root);
         if (iGuiAnimationStrategy.filter(rootView)) {
             textViews.add(rootView);
         }
         return textViews;
     }

     public static class AnimEndPart implements OnAnimEndListener {
         OnAnimEndListener animEndListener;
         AnimEndVisitor animEndVisitor;

         public AnimEndPart(OnAnimEndListener animEndListener) {
             this.animEndListener = animEndListener;
         }

         @Override
         public void onEnd(View view) {
             animEndListener.onEnd(view);
             animEndVisitor.onEnd(view);
         }

         public void accept(AnimEndVisitor animEndVisitor) {
             animEndVisitor.onVisitor(this);
             this.animEndVisitor = animEndVisitor;
         }
     }

     public static class AnimEndVisitor implements OnAnimEndListener {
         ViewGroup viewGroup;
         OnAnimEndListener onAnimLastListener;//当所有动画执行完毕后的回掉
         Queue<AnimEndPart> animEndParts = new LinkedList<>();
         public AnimEndVisitor(ViewGroup viewGroup, OnAnimEndListener onAnimLastListener) {
             this.viewGroup = viewGroup;
             this.onAnimLastListener = onAnimLastListener;
         }

         void onVisitor(AnimEndPart animEndPart) {
             animEndParts.add(animEndPart);
         }

         @Override
         public void onEnd(View view) {
             animEndParts.poll();
             if (animEndParts.size() == 0) {
                 onAnimLastListener.onEnd(viewGroup);
             }
         }
     }

 }