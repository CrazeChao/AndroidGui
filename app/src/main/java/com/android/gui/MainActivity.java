package com.android.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.guidelib.GuiJumpHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void jump(View view){
        startActivity(new Intent(this,MainActivity2.class));
    }

    public void showGui(View view){
        GuiJumpHelper.start(this,MainActivity.class);

    }
}