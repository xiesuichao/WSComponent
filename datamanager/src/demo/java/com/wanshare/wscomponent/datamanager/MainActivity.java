package com.wanshare.wscomponent.datamanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private boolean mState = true;
    private String mText = "text";
    private int mIntNum = 10;
    private float mFloatNum = 11.11f;
    private long mLongNum = 1212L;
    private short mShortNum = 333;
    private double mDoubleNum = 44.44;
    private User mUser = new User("Tom", "20", 10, true, 22.2222, 33.33f, mShortNum, 55555);

    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnDecryptUtil.init(getApplicationContext());

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }

        testDb();
        testSp();
        testMemory();
        testFile();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
        }
    }

    private void testFile() {
        String filename = "fileText.txt";
        String content = "fileString";

        //内部存储files路径 及其下的图片、文本、音频、视频文件夹
        String internalFilePath = FileCache.getInternalFilePath(getApplicationContext());
        String internalFileImgPath = FileCache.getInternalFileImgPath(getApplicationContext());
        String internalFileTextPath = FileCache.getInternalFileTextPath(getApplicationContext());
        String internalFileVoicePath = FileCache.getInternalFileVoicePath(getApplicationContext());
        String internalFileVideoPath = FileCache.getInternalFileVideoPath(getApplicationContext());

        //内部存储cache路径
        String internalCachePath = FileCache.getInternalCachePath(getApplicationContext());

        //外部存储files路径
        String externalFilePath = FileCache.getExternalFilePath(getApplicationContext());

        //外部存储cache路径
        String externalCachePath = FileCache.getExternalCachePath(getApplicationContext());

        //外部存储根路径
        String externalDirectoryPath = FileCache.getExternalDirectoryPath();

        FileCache.saveFile(filename, internalFilePath, content);

    }

    private void testSp() {
        DataCache dataCache = new SpCache(getApplicationContext());
        //write
        dataCache.setData(SpConfig.SP_KEY_BOOLEAN, mState);
        dataCache.setData(SpConfig.SP_KEY_DOUBLE, mDoubleNum, -1);
        dataCache.setObject(SpConfig.SP_KEY_USER, mUser);

        //read
        boolean state = dataCache.getBoolean(SpConfig.SP_KEY_BOOLEAN);
        double doubleNum = dataCache.getDouble(SpConfig.SP_KEY_DOUBLE);
        User user = (User) dataCache.getObject(SpConfig.SP_KEY_USER);

        PrintUtil.log("sp boolean", state);
        PrintUtil.log("sp double", doubleNum);
        PrintUtil.log("sp user", user);

    }

    private void testDb() {
        DataCache dataCache = new DBCache(getApplicationContext());
        //write
        dataCache.setData(SpConfig.SP_KEY_BOOLEAN, mState);
        dataCache.setData(SpConfig.SP_KEY_DOUBLE, mDoubleNum, -1);
        dataCache.setObject(SpConfig.SP_KEY_USER, mUser);

        dataCache.remove(SpConfig.SP_KEY_DOUBLE);

        //read
        boolean state = dataCache.getBoolean(SpConfig.SP_KEY_BOOLEAN);
        double doubleNum = dataCache.getDouble(SpConfig.SP_KEY_DOUBLE);
        User user = (User) dataCache.getObject(SpConfig.SP_KEY_USER);

        PrintUtil.log("db boolean", state);
        PrintUtil.log("db double", doubleNum);
        PrintUtil.log("db getObject user", user);

    }

    private void testMemory() {
        //write
        DataCache dataCache = new MemoryCache();
        dataCache.setData(SpConfig.SP_KEY_BOOLEAN, mState);
        dataCache.setData(SpConfig.SP_KEY_DOUBLE, mDoubleNum, -1);
        dataCache.setObject(SpConfig.SP_KEY_USER, mUser, -1);

        //read
        boolean state = dataCache.getBoolean(SpConfig.SP_KEY_BOOLEAN);
        double doubleNum = dataCache.getDouble(SpConfig.SP_KEY_DOUBLE);
        User user = (User) dataCache.getObject(SpConfig.SP_KEY_USER);

        PrintUtil.log("memory Boolean", state);
        PrintUtil.log("memory Double", doubleNum);
        PrintUtil.log("memory User", user);

    }

}
