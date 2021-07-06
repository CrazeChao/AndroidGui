package com.android.guidelib;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.guidelib.m.GuiManager;
import com.android.guidelib.m.GuiModel;
import com.android.guidelib.m.model.IGuiDelivery;
import com.tencent.mmkv.MMKV;

public class GuiActivity extends AppCompatActivity {
    private static final String simplNameKey = "SimpleName";
    private static final int recultCode_DISS = 0x100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        IGuiDelivery model = GuiManager.getG().getGui(getIntent().getStringExtra(simplNameKey));
        if (model == null) setContentView(R.layout.gui_error);
        int layout = model.poll().getLayout();
        setContentView(layout);
        ViewGroup viewGroup = getDelegate().findViewById(android.R.id.content);
        GuiAnim.executeAnim((ViewGroup) viewGroup.getChildAt(0));
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        IGuiDelivery model = GuiManager.getG().getGui(getIntent().getStringExtra(simplNameKey));
        if (model == null) setContentView(R.layout.gui_error);
        setContentView(model.poll().getLayout());
    }

    public void onStartNext(View view){
        into(this,getIntent().getStringExtra(simplNameKey));
    }
    public void emptyClick(View view){

    }
    public void onCommit(View view){
       GuiManager.getG().commit(getIntent().getStringExtra(simplNameKey));
       this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IGuiDelivery model = GuiManager.getG().getGui(getIntent().getStringExtra(simplNameKey));
        if (model != null)model.restory();
    }

    public static void into(Activity activity, String key){
        IGuiDelivery model = GuiManager.getG().getGui(key);
        if (model == null)return;
        Intent intent = new Intent(activity,GuiActivity.class);
        intent.putExtra(simplNameKey,key);
        activity.startActivity(intent);
        activity.overridePendingTransition(-1,-1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == recultCode_DISS){
            this.finish();
            setResult(recultCode_DISS);
        }
    }
}
