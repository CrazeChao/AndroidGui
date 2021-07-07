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


设置：
        动画设置面向策略工厂
        分四个策略
           1 启动gui流程的 动画车略
           2 关闭gui流程的动画策略
           3 gui切换旧的页面动画策略
           4 gui切换新页面动画策略

