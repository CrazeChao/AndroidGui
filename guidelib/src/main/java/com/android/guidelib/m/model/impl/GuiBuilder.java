 package com.android.guidelib.m.model.impl;
 import androidx.annotation.LayoutRes;

 import com.android.guidelib.m.GuiManager;
 import com.android.guidelib.m.model.BaseGuiModel;
 import com.android.guidelib.m.model.Gui;
 import com.android.guidelib.m.model.IGuiUnit;

 import java.util.LinkedHashSet;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;

 /**
 * Created by lizhichao on 5/26/21
 */
public   class GuiBuilder {
    private List<GuiUnitBuilder> guiUnitBuilderHashMap = new LinkedList<>();
     public  GuiUnitBuilder createGuiUnit(Class<?> key,String version){
        return new GuiUnitBuilder(this).setKey(key.getName()).setVersion(version);
     }
      public void push(){
         Map map = GuiManager.getG().getMap();
         for (int i = 0; i < guiUnitBuilderHashMap.size(); i++) {
            GuiUnitBuilder guiUnitBuilder =  guiUnitBuilderHashMap.get(i);
            map.put(guiUnitBuilder.key,new BaseGuiModel(guiUnitBuilder));
         }
     }


    public class GuiUnitBuilder implements IGuiUnit {
        GuiBuilder guiBuilder;
        LinkedHashSet<Gui> guiList = new LinkedHashSet<>();
        String version;
        String key;

        public String getKey() {
            return key;
        }

        @Override
        public String getVersion() {
            return version;
        }

        @Override
        public void loading(LinkedList<Gui> guiLinkedList) {
             guiLinkedList.clear();
             guiLinkedList.addAll(guiList);
        }

        public GuiUnitBuilder(GuiBuilder guiBuilder) {
            this.guiBuilder = guiBuilder;
        }

        public GuiBuilder commit(){
            guiUnitBuilderHashMap.add(this);
            return guiBuilder;
        }
        public GuiUnitBuilder addGui(@LayoutRes int layoutRes){
            guiList.add(new Gui(layoutRes));
            return this;
        }
        private GuiUnitBuilder setVersion(String version){
            this.version = version;
            return this;
        }
        private GuiUnitBuilder setKey(String key){
            this.key = key;
            return this;
        }

    }



}