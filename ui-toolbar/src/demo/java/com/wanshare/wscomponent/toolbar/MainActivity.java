package com.wanshare.wscomponent.toolbar;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyToolbar mMyToolbar2;
    private MyToolbar mMyToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyToolbar = findViewById(R.id.my_toolbar);
        mMyToolbar2 = findViewById(R.id.my_toolbar2);
        initToolbar();
        initToolbar2();
    }

    private void initToolbar() {
        //设置返回监听
        mMyToolbar.setOnBackButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click back", Toast.LENGTH_SHORT).show();
            }
        });
        mMyToolbar.setOnRightButtonTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click right text button", Toast.LENGTH_SHORT).show();
            }
        });
        mMyToolbar.setRightButtonTextSize(18);
    }

    /**
     * 通过代码设置标题栏
     */
    private void initToolbar2() {
        //设置标题
        mMyToolbar2.setTitle("我是代码设置的titlebar");
        mMyToolbar2.setTitleTextColor(R.color.colorPrimary);
        mMyToolbar2.setTitleTextSize(18);
        mMyToolbar2.setRightButtonText("注册");
        mMyToolbar2.setRightButtonTextSize(18);
        mMyToolbar2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        //设置返回监听
        mMyToolbar2.setOnBackButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click back", Toast.LENGTH_SHORT).show();
            }
        });
        mMyToolbar2.setRightButtonTextEnable(true);
        mMyToolbar2.setRightButtonImageVisible(false);
//        mMyToolbar2.setRightButtonImage(R.drawable.ic_lookup);
        mMyToolbar2.setOnRightButtonImageClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click right image button", Toast.LENGTH_SHORT).show();
            }
        });
        mMyToolbar2.setOnRightButtonTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click right text button", Toast.LENGTH_SHORT).show();
            }
        });
//        mMyToolbar2.setTitleTextStyle(Typeface.defaultFromStyle(Typeface.BOLD));
    }
}
