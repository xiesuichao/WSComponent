package com.wanshare.wscomponent.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog1();
        initDialog2();
        initDialog3();
        initDialog4();
        initProgressDialog();
        initProgressDialog2();
        initProgressDialog3();
        initBottomDialog();
    }


    private void initDialog1() {
        findViewById(R.id.btn_dialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommonDialog.Builder(MainActivity.this).setCancelable(false).setTitle("我有1个button").setMessage("我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容").setPositiveButton("确认", new CommonDialog.OnPositiveClickListener() {
                    @Override
                    public void onClick(CommonDialog dialog) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "点击了确认", Toast.LENGTH_SHORT).show();
                    }

                })
                        .setNegativeBtnShow(false)
                        .setNegativeButton("取消", new CommonDialog.OnNegativeClickListener() {
                            @Override
                            public void onClick(CommonDialog dialog) {
                                dialog.cancel();
                                Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
            }
        });
    }

    private void initDialog2() {
        findViewById(R.id.btn_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommonDialog.Builder(MainActivity.this).setCancelable(false).setTitle("我有2个button").setMessage("我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容").setPositiveButton("确认", new CommonDialog.OnPositiveClickListener() {
                    @Override
                    public void onClick(CommonDialog dialog) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "点击了确认", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton("取消", new CommonDialog.OnNegativeClickListener() {
                            @Override
                            public void onClick(CommonDialog dialog) {
                                dialog.cancel();
                                Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
            }
        });
    }

    private void initDialog3() {
        findViewById(R.id.btn_dialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommonDialog.Builder(MainActivity.this, R.style.my_dialog_style).setCancelable(false).setTitle("我是自定义的style").setMessage("我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容").setPositiveButton("确认", new CommonDialog.OnPositiveClickListener() {
                    @Override
                    public void onClick(CommonDialog dialog) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "点击了确认", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton("取消", new CommonDialog.OnNegativeClickListener() {
                            @Override
                            public void onClick(CommonDialog dialog) {
                                dialog.cancel();
                                Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
            }
        });
    }

    private void initDialog4() {
        findViewById(R.id.btn_dialog4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_custom_text, null);
                new CommonDialog.Builder(MainActivity.this).setCancelable(false).setTitle("我是自定义的content").setPositiveButton("确认", new CommonDialog.OnPositiveClickListener() {
                    @Override
                    public void onClick(CommonDialog dialog) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "点击了确认", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton("取消", new CommonDialog.OnNegativeClickListener() {
                            @Override
                            public void onClick(CommonDialog dialog) {
                                dialog.cancel();
                                Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setView(view)
                        .create().show();
            }
        });
    }

    private void initProgressDialog() {
        findViewById(R.id.btn_progress_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonProgressDialog dialog = new CommonProgressDialog(MainActivity.this);
                dialog.show();
            }
        });

    }

    private void initProgressDialog2() {
        findViewById(R.id.btn_progress_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonProgressDialog dialog = new CommonProgressDialog(MainActivity.this, R.style.my_progress_dialog_theme);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setMsg("加载中");
                dialog.show();
            }
        });

    }

    private void initProgressDialog3() {
        findViewById(R.id.btn_progress_dialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.progress_circle_rotate);
                animation.setDuration(1000);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(-1);
                final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_logo_progress, null);
                ImageView ivProgress = view.findViewById(R.id.ivProgressCircle);
                ivProgress.setAnimation(animation);
                animation.start();
                CommonProgressDialog dialog = new CommonProgressDialog(MainActivity.this, R.style.my_progress_dialog_theme);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setProgressView(view);
                dialog.setMsg("加载中");
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        animation.cancel();
                    }
                });
                dialog.show();
            }
        });

    }

    private void initBottomDialog() {
        findViewById(R.id.btn_bottom_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CommonBottomSheetDialog dialog = new CommonBottomSheetDialog(MainActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_bottom_select, null);
                view.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                view.findViewById(R.id.tv_album).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "相册选取", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                dialog.setView(view);
                dialog.show();
            }
        });
    }


}
