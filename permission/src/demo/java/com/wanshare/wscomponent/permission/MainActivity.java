package com.wanshare.wscomponent.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wanshare.wscomponent.PermissionCallback;
import com.wanshare.wscomponent.Permissions;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * @deprecated 请使用下方 onContactPermission 或者 onReadSmsPermission 中的权限申请流程
     */
    @Deprecated
    public void onStorage(View view) {
        //AndPermission本身使用方法
        AndPermission.with(this)
                     .runtime()
                     .permission(Permission.Group.STORAGE)
                     .onGranted(new Action<List<String>>() {
                         @Override
                         public void onAction(List<String> data) {
                             Toast.makeText(MainActivity.this, "存储权限已通过", Toast.LENGTH_SHORT).show();
                         }
                     })
                     .onDenied(new Action<List<String>>() {
                         @Override
                         public void onAction(List<String> data) {
                             Toast.makeText(MainActivity.this, "存储权限已拒绝", Toast.LENGTH_SHORT).show();
                         }
                     })
                     .start();
    }

    /**
     * @deprecated 请使用下方 onContactPermission 或者 onReadSmsPermission 中的权限申请流程
     */
    @Deprecated
    public void onPhotograph(View view) {
        //AndPermission本身使用方法
        AndPermission.with(this)
                     .runtime()
                     .permission(Permission.Group.CAMERA)
                     .onGranted(new Action<List<String>>() {
                         @Override
                         public void onAction(List<String> data) {
                             Toast.makeText(MainActivity.this, "拍照权限已通过", Toast.LENGTH_SHORT).show();
                         }
                     })
                     .onDenied(new Action<List<String>>() {
                         @Override
                         public void onAction(List<String> data) {
                             Toast.makeText(MainActivity.this, "拍照权限已拒绝", Toast.LENGTH_SHORT).show();
                         }
                     })
                     .start();
    }

    public void onContactPermission(View view) {
        //用法： Permissions.from(Activity或者fragment, 权限（可跟多个，请使用com.yanzhenjie.permission.Permission中的
        // 权限常量即可。如果需要换权限库，再换权限常量类.)
        // .start() 跟上权限回调逻辑即可
        Permissions.from(this, Permission.READ_CONTACTS)
                   .start(new PermissionCallback() {
                       @Override
                       public void yes(List<String> data) {
                           Toast.makeText(MainActivity.this, "读取联系人权限已通过", Toast.LENGTH_SHORT).show();
                       }

                       @Override
                       public void no(List<String> data) {
                           Toast.makeText(MainActivity.this, "读取联系人权限已拒绝", Toast.LENGTH_SHORT).show();
                       }
                   });
    }

    public void onReadSmsPermission(View view) {
        Permissions.from(this, Permission.READ_SMS)
                   .start(new PermissionCallback() {
                       @Override
                       public void yes(List<String> data) {
                           Toast.makeText(MainActivity.this, "读取短信权限已通过", Toast.LENGTH_SHORT).show();
                       }

                       @Override
                       public void no(List<String> data) {
                           Toast.makeText(MainActivity.this, "读取短信权限已拒绝", Toast.LENGTH_SHORT).show();
                       }
                   });
    }
}

