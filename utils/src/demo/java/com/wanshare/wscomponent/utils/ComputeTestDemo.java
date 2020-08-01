package com.wanshare.wscomponent.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jason on 2018/7/23.
 * <p>
 * 加减乘除精确运算的demo
 */

public class ComputeTestDemo extends AppCompatActivity {
    @BindView(R.id.edit_add_number_1)
    EditText mEditAddNumber1;
    @BindView(R.id.edit_add_number_2)
    EditText mEditAddNumber2;
    @BindView(R.id.result_add)
    EditText mResultAdd;
    @BindView(R.id.edit_sub_number_1)
    EditText mEditSubNumber1;
    @BindView(R.id.edit_sub_number_2)
    EditText mEditSubNumber2;
    @BindView(R.id.result_sub)
    EditText mResultSub;
    @BindView(R.id.edit_mul_number_1)
    EditText mEditMulNumber1;
    @BindView(R.id.edit_mul_number_2)
    EditText mEditMulNumber2;
    @BindView(R.id.result_mul)
    EditText mResultMul;
    @BindView(R.id.edit_div_number_1)
    EditText mEditDivNumber1;
    @BindView(R.id.edit_div_number_2)
    EditText mEditDivNumber2;
    @BindView(R.id.result_div)
    EditText mResultDiv;
    @BindView(R.id.result_mul_8)
    EditText mResultMul8;
    @BindView(R.id.result_div_8)
    EditText mResultDiv8;
    @BindView(R.id.edit_number)
    EditText mEditNumber;
    @BindView(R.id.edit_scale)
    EditText mEditScale;
    @BindView(R.id.result_number)
    EditText mResultNumber;

    /**
     * 精确到第八位小数
     */
    private final int SCAN_8 = 8;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ComputeTestDemo.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_compute_test);
        ButterKnife.bind(this);

        initView();
    }


    private void initView() {
        mEditAddNumber1.addTextChangedListener(addWatcher);
        mEditAddNumber2.addTextChangedListener(addWatcher);
        mEditSubNumber1.addTextChangedListener(subWatcher);
        mEditSubNumber2.addTextChangedListener(subWatcher);
        mEditMulNumber1.addTextChangedListener(mulWatcher);
        mEditMulNumber2.addTextChangedListener(mulWatcher);
        mEditDivNumber1.addTextChangedListener(divWatcher);
        mEditDivNumber2.addTextChangedListener(divWatcher);
        mEditNumber.addTextChangedListener(roundWatcher);
        mEditScale.addTextChangedListener(roundWatcher);
    }

    /**
     * 加数输入框监听器
     */
    private TextWatcher addWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String editNumber1 = mEditAddNumber1.getText().toString().trim();
            String editNumber2 = mEditAddNumber2.getText().toString().trim();
            if (!TextUtils.isEmpty(editNumber1) && !TextUtils.isEmpty(editNumber2)) {
                //加法运算
                String addResult = ArithUtil.add(editNumber1, editNumber2);
                mResultAdd.setText(addResult);
            } else {
                mResultAdd.setText("");
            }
        }
    };

    /**
     * 减数输入框监听器
     */
    private TextWatcher subWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String editNumber1 = mEditSubNumber1.getText().toString().trim();
            String editNumber2 = mEditSubNumber2.getText().toString().trim();
            if (!TextUtils.isEmpty(editNumber1) && !TextUtils.isEmpty(editNumber2)) {
                //减法运算
                String subResult = ArithUtil.sub(editNumber1, editNumber2);
                mResultSub.setText(subResult);
            } else {
                mResultSub.setText("");
            }
        }
    };

    /**
     * 乘数输入框监听器
     */
    private TextWatcher mulWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String editNumber1 = mEditMulNumber1.getText().toString().trim();
            String editNumber2 = mEditMulNumber2.getText().toString().trim();
            if (!TextUtils.isEmpty(editNumber1) && !TextUtils.isEmpty(editNumber2)) {
                //乘法运算
                String mulResult = ArithUtil.mul(editNumber1, editNumber2);
                //精确到第八位小数的乘法运算
                String mulResult8 = ArithUtil.mul(editNumber1, editNumber2, SCAN_8);
                mResultMul.setText(mulResult);
                mResultMul8.setText(mulResult8);
            } else {
                mResultMul.setText("");
                mResultMul8.setText("");
            }
        }
    };

    /**
     * 除数输入框监听器
     */
    private TextWatcher divWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String editNumber1 = mEditDivNumber1.getText().toString().trim();
            String editNumber2 = mEditDivNumber2.getText().toString().trim();
            if (!TextUtils.isEmpty(editNumber1) && !TextUtils.isEmpty(editNumber2)) {
                //除法运算
                String divResult = ArithUtil.div(editNumber1, editNumber2);
                //精确到第八位小数的除法运算
                String divResult8 = ArithUtil.div(editNumber1, editNumber2, SCAN_8);
                mResultDiv.setText(divResult);
                mResultDiv8.setText(divResult8);
            } else {
                mResultDiv.setText("");
                mResultDiv8.setText("");
            }
        }
    };

    /**
     * 四舍五入数据输入框监听器
     */
    private TextWatcher roundWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String editNumber = mEditNumber.getText().toString().trim();
            String editScale = mEditScale.getText().toString().trim();
            if (!TextUtils.isEmpty(editNumber) && !TextUtils.isEmpty(editScale)) {
                int scale = Integer.parseInt(editScale);
                //根据自己的需求精确到小数位后的数值运算
                String resultNumber = ArithUtil.round(editNumber, scale);
                mResultNumber.setText(resultNumber);
            } else {
                mResultNumber.setText("");
            }
        }
    };
}
