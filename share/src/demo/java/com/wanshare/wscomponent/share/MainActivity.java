package com.wanshare.wscomponent.share;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TextView;

import java.io.File;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mBtnShareText;
    private View mBtnShareImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnShareText = findViewById(R.id.btn_share_text);
        mBtnShareImage = findViewById(R.id.btn_share_image);

        mBtnShareText.setOnClickListener(this);
        mBtnShareImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mBtnShareText) {
            ShareParam param = new ShareParam().setText("我是分享的文本信息2")
                    .setTitle("我是标题栏2")
                    .setUrl("http://www.google.com")
                    .setImageUrl("https://ps.ssl.qhimg.com/dmfd/420_207_/t010c3d51ee70bc13be.jpg");

            ShareManager.getInstance().shareTo(param);
        } else if (v == mBtnShareImage) {
            Bitmap bitmap = getShareBitmap();
            String fileName = "share.jpg";
            File file = CreatFileUtils.changeFile(CreatFileUtils.getImagePath(), fileName);
            //保存至sdcard
            BitmapUtil.saveBmpToSd(bitmap, file);
            String filePath = file.getAbsolutePath();
            ShareParam param = new ShareParam().setImagePath(filePath);

            ShareManager.getInstance().shareTo(param);
        }
    }

    private Bitmap getShareBitmap() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_share_image, null, false);
        String inviteCode = "123456";
        final String url = "http://www.wanshare.com";

        ((TextView)view.findViewById(R.id.tv_invite_my_code)).setText(getString(R.string.my_invite_code_value,inviteCode));
        return makeView2Bitmap(view);
    }

    public Bitmap makeView2Bitmap(View view) {
        //打开图片的缓存
        view.setDrawingCacheEnabled(true);
        //图片的大小 固定的语句
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //将位置传给view
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        //转化为bitmap文件
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

}
