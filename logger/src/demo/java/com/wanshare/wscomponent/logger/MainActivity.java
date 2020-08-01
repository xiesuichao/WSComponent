package com.wanshare.wscomponent.logger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Tag", "I'm a log which you don't see easily, hehe");
        LogUtil.e("show error message no tag");
        LogUtil.e("tag2", "show error message set tag");
        LogUtil.d("tag3", "show debug message");
        LogUtil.wrightToFile("wtf1");

        LogUtil.json("{ \"key\": 3, \"value\": something}");

        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key1", "value2");
        Map<String, String> map2 = new HashMap<>();
        map2.put("key", "value");
        map2.put("key1", "value2");

        List<Map> mList=new ArrayList<>();
        mList.add(map);
        mList.add(map2);

        LogUtil.list(mList);
        LogUtil.map(map);
        LogUtil.ex(new NullPointerException());

    }

}
