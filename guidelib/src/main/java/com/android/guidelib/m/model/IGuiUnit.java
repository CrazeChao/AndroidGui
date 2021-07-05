 package com.android.guidelib.m.model;
 import java.util.LinkedList;

 /**
 * Created by lizhichao on 5/26/21
 */
 public  interface IGuiUnit{
    String getVersion();
    void loading(LinkedList<Gui> guiLinkedList);
}