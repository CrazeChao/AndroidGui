 package com.android.gui;
 import android.app.Application;
 import com.android.guidelib.IGuiApplication;
 import com.android.guidelib.m.model.impl.GuiBuilder;
 /**
 * Created by lizhichao on 7/5/21
 */
  public class App  extends Application implements IGuiApplication {
     @Override
     public void onCreate() {
         super.onCreate();
         initGuiApplication();
         setRoot(BuildConfig.APPLICATION_ID);
     }

     @Override
     public GuiBuilder onGuiBuilder(GuiBuilder guiBuilder) {
       return guiBuilder
                .createGuiUnit(MainActivity.class,"1.d.1")
               .addGui(R.layout.gui_tempo_1)
                 .addGui(R.layout.gui_tempo_2)
                 .addGui(R.layout.gui_tempo_3)
                 .commit()
          .createGuiUnit(MainActivity2.class,"1.0d.1")
                 .addGui(R.layout.gui_tempo_1)
                 .addGui(R.layout.gui_tempo_2)
                 .addGui(R.layout.gui_tempo_3)
                 .commit();
     }

 }