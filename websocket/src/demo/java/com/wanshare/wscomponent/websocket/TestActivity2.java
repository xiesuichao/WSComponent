package com.wanshare.wscomponent.websocket;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestActivity2 extends AbsWebSocketActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);


        TextView textView = findViewById(R.id.tv_test_info);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText("42[\"coin-main:order:join\"]");
            }
        });

    }

    @Override
    public void onMessageResponse(Response message) {

    }

    @Override
    public void onSendMessageError(ErrorResponse error) {

    }
}
