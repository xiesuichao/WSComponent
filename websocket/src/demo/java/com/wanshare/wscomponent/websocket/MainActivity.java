package com.wanshare.wscomponent.websocket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsWebSocketActivity {

    Context mContext;
    Button mBtn1,mBtn2;

    private FragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mBtn1 = findViewById(R.id.btn_test_1);
        mBtn2 = findViewById(R.id.btn_test_2);

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,TestActivity1.class));
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,TestActivity2.class));
            }
        });

    }


    @Override
    public void onServiceBindSuccess() {
        super.onServiceBindSuccess();
//        sendText("42[\"home:join\"]");
    }

    @Override
    public void onMessageResponse(Response message) {

    }

    @Override
    public void onSendMessageError(ErrorResponse error) {

    }
}
