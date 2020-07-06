package com.example.benefitalumni1;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.benefitalumni1.model.User;

import cn.bmob.v3.Bmob;

public class UserActivity extends Activity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_user_info);

        Bmob.initialize(this, "04e905bba1912c9e7d3972bdebe82ff6");



    }
}