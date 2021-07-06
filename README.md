# AndroidGui
第一步 
    
    allprojects {
    repositories {
       ...
        maven { url "https://jitpack.io" }
    }
    }
第二步
BaseApplication extends Application implements IGuiApplication{
    
       @Override
      public void onCreate() {
          super.onCreate();
          initGuiApplication();
      }
          @Override
      public GuiBuilder onGuiBuilder(GuiBuilder guiUtils) {
          return guiUtils.createGuiUnit(?.class,"1.0.1")
                  .addGui(R.layout.gui_tempo_1) //写View
                  .addGui(R.layout.gui_tempo_2)
                  .addGui(R.layout.gui_tempo_3)
                  .commit();
      }
}

第三步
        GuiJumpHelper.start(activity ,？.class);
