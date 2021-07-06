 package com.android.guidelib;
 import android.graphics.Path;
 import android.view.View;
 import android.view.ViewGroup;
 import android.view.ViewTreeObserver;
 import android.view.animation.Interpolator;
 import android.view.animation.PathInterpolator;
 import android.widget.TextView;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 /**
 * Created by lizhichao on 7/6/21
 */
 class GuiAnim  {
     public static void executeAnim(ViewGroup group) {
       List<View> listViews =  filterAnimView(group);
       if (listViews.size() == 0)return;
       group.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
           @Override
           public boolean onPreDraw() {
               for (int i = 0; i < listViews.size(); i++) {
                   anim(listViews.get(i),i);
               }
               group.getViewTreeObserver().removeOnPreDrawListener(this);
               return false;
           }
       });
     }

     public static final long delayTime = 500;
     public static final float hideAlpha = 0f;

     public static void  anim(View view,int index){
         long currentStartDelayTime = delayTime*index;
         String data = (String)view.getTag();
         int inval = 100;
         try {
             inval = Integer.parseInt(data);
         }catch (NumberFormatException numberFormatException){
         }
         float startTranslationY = view.getTranslationY()+inval;
         float endTranlationy = view.getTranslationY();
         view.setTranslationY(startTranslationY);
         view.setAlpha(hideAlpha);
         view.animate().translationY(endTranlationy).alpha(1).setInterpolator(getDefaultInterception()).setStartDelay(currentStartDelayTime)
                 .setDuration(600).start();
     }

     private static List<View> filterAnimView(ViewGroup group) {
         List<View> textViews = new ArrayList<>();
         for (int i = 0; i < group.getChildCount(); i++) {
             if (group.getChildAt(i) instanceof TextView){
                 textViews.add(group.getChildAt(i));
             }
         }
         Collections.sort(textViews, (o1, o2) -> Integer.compare((int)o1.getTranslationY(),(int)o2.getTranslationY()));
         return textViews;
     }


     public static Path getDefaultPath(){
         Path path = new Path();
         path.moveTo(0, 0);
         path.cubicTo(0.25f, 0.1f, 0.25f, 1f, 1f, 1f);
         return path;
     }
     public static Interpolator getDefaultInterception(){
         return new PathInterpolator(getDefaultPath());
     }


 }