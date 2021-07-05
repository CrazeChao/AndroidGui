package com.android.guidelib;

import com.tencent.mmkv.MMKV;

/**
 * Created by lizhichao on 1/22/21
 */
public    class GuiCache {
   static String ROOT;
   public static void setRoot(String root){
       ROOT = root;
   }
   public  static MMKV gkv(){
      return MMKV.defaultMMKV();
   }
}
