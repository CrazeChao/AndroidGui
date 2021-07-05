package com.android.guidelib.m;
import android.text.TextUtils;

import com.android.guidelib.GuiCache;
import com.android.guidelib.m.model.BaseGuiModel;
import com.android.guidelib.m.model.IGuiDelivery;
import com.google.gson.Gson;
import java.util.HashMap;
/**
 * Created by lizhichao on 1/22/21
 */
public  class GuiManager {
   private final static String non = "null";
   Gson mGson;
   private static GuiManager g;
    public static GuiManager getG() {
       return  (g == null)? g = new GuiManager():g;
    }
    public GuiManager() {
       mGson = new Gson();
    }
    public void put(String key,BaseGuiModel baseGuiModel){
       map.put(key,baseGuiModel);
    }
    public HashMap<String, GuiModel> getMap() {
        return map;
    }
    HashMap<String,GuiModel> map = new HashMap<>();

    String activityName;
    public IGuiDelivery getGui(String  SimplName){
        activityName = SimplName;
        GuiModel guiModel = map.get(activityName);
        if (guiModel == null) return null;
        guiModel.init();
        String str = GuiCache.gkv().getString(activityName,non);
        if (non.equals(str)) return guiModel;
        GuiRecord guiRecord = mGson.fromJson(str, GuiRecord.class);
        if (!TextUtils.equals(guiRecord.commitVersion,guiModel.getVersion())) return guiModel;
        return null;
    }

   public void commit(String simplName){
       GuiModel guiModel = map.get(simplName);
       if (guiModel == null)return;
       GuiCache.gkv().putString(simplName,mGson.toJson(new GuiRecord(guiModel.getVersion())));
   }

}
