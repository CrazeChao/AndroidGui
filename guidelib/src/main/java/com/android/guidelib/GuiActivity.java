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
import com.android.guidelib.m.model.IGuiDelivery;
public class GuiActivity extends AppCompatActivity {
    private static final String simplNameKey = "SimpleName";
    private static final int recultCode_DISS = 0x100;
    ViewGroup mViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        IGuiDelivery model = GuiManager.getG().getGui(getIntent().getStringExtra(simplNameKey));
        if (model == null) setContentView(R.layout.gui_error);
        int layout = model.poll().getLayout();
        setContentView(layout);
        prepareEnterAnimation();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        IGuiDelivery model = GuiManager.getG().getGui(getIntent().getStringExtra(simplNameKey));
        if (model == null) {
            setContentView(R.layout.gui_error);
            return;
        }
        setContentView(model.poll().getLayout());
        initViewGroup();
        GuiAnim.prepareTransitionEnterAnim(mViewGroup,null);
    }
    void initViewGroup(){
        mViewGroup = getDelegate().findViewById(R.id.gui_root);
    }

    private void prepareEnterAnimation() {
        initViewGroup();
        GuiAnim.prepareEnterAnimation(mViewGroup,null);
    }

    public void onStartNext(View view) {
        initViewGroup();
        GuiAnim.prepareTransitionExitAnim(mViewGroup, resultView -> {
            if (resultView == mViewGroup){
                overridePendingTransition(-1, -1);
                into(GuiActivity.this, getIntent().getStringExtra(simplNameKey));
            }
        });
    }

    public void emptyClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onCommit(View view) {
        GuiManager.getG().commit(getIntent().getStringExtra(simplNameKey));
        initViewGroup();
        GuiAnim.prepareExitAnim(mViewGroup,resultView ->{
            if (resultView == mViewGroup){
                overridePendingTransition(-1, -1);
                GuiActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IGuiDelivery model = GuiManager.getG().getGui(getIntent().getStringExtra(simplNameKey));
        if (model != null) model.restory();
    }

    public static void into(Activity activity, String key) {
        IGuiDelivery model = GuiManager.getG().getGui(key);
        if (model == null) return;
        Intent intent = new Intent(activity, GuiActivity.class);
        intent.putExtra(simplNameKey, key);
        activity.startActivity(intent);
        activity.overridePendingTransition(-1, -1);
    }

    public static void laucher(Activity activity, String key,Runnable onGuiRun) {
        if (activity instanceof GuiActivity) return;
        IGuiDelivery model = GuiManager.getG().getGui(key);
        if (model == null) return;
        if (onGuiRun != null){
            onGuiRun.run();
        }
        Intent intent = new Intent(activity, GuiActivity.class);
        intent.putExtra(simplNameKey, key);
        activity.startActivity(intent);
        activity.overridePendingTransition(-1, -1);
        //启动
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == recultCode_DISS) {
            this.finish();
            setResult(recultCode_DISS);
        }
    }
}
