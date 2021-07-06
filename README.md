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
     1 设置View 之间动画启动间隔
       IGuiApplication.setGuiUnitAnimStartIntervalTime
     2 设置每个view 场景过度时长
       IGuiApplication.setGuiUnitAnimDuration

     3 定制View轨迹长度
       在xml中用  android:tag="100" 单位：时间戳
