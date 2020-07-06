package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.benefitalumni1.model.User;


//主界面
public class HomeActivity extends Activity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_home);

//        得到intent传递的数据
        Intent intent = getIntent();
        //user = (User) intent.getSerializableExtra("user");
        user = (User) intent.getSerializableExtra("user");
        Log.d("user", "-----------onCreate: 用户名为:"+user);

    }

    //进入失物模块
    public void lost(View view) {
        Intent intent = new Intent(HomeActivity.this, NewLostActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);

//        setResult(999, intent);
//        finish();
    }

    //进入拾物模块
    public void found(View view) {
        Intent intent = new Intent(HomeActivity.this, FoundActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    //进入个人信息模块
    public void info(View view) {
        Intent intent = new Intent(HomeActivity.this, UserActivity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, 999);
//        finish();
    }

    //退出登陆
    public void exitLogin(View view) {
        Intent intent = new Intent();
        setResult(999, intent);
        finish();
    }

}
