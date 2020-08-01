package com.wanshare.wscomponent.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.progressWebView);
        initWebView();
    }

    private void initWebView() {
        mWebView.loadUrl("http://www.baidu.com");
        mWebView.setOnTitleChangedListener(new ProgressWebView.OnTitleChangedListener() {
            @Override
            public void onTitleChanged(ProgressWebView progressWebView, String title) {
                getSupportActionBar().setTitle(title);
            }
        });
//        mWebView.setShowProgressBar(false);
    }

    @Override
    public void onBackPressed() {
        if(mWebView.getWebView().canGoBack()) {
            mWebView.getWebView().goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}

