package com.wanshare.wscomponent.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Richard on 2018/8/6
 * 通用底部条目对话框
 */
public class CommonBottomSheetDialog extends BottomSheetDialog {

    private FrameLayout mContentFL;
    private View mContentView;

    public CommonBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public CommonBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_bottom_sheet_dialog);
        mContentFL = findViewById(R.id.fl_content);
        if (mContentView != null) {
            mContentFL.addView(mContentView);
        }
    }

    public void setView(View view) {
        this.mContentView = view;
    }
}
