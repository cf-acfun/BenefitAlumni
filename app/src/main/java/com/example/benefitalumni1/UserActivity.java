package com.example.benefitalumni1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.benefitalumni1.model.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;

public class UserActivity extends Activity {

    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.update)
    TextView update;
    @Bind(R.id.tv_userId)
    TextView tvUserId;
    @Bind(R.id.tv_password)
    TextView tvPassword;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_email)
    TextView tvEmail;
    @Bind(R.id.tv_college)
    TextView tvCollege;
    @Bind(R.id.tv_class)
    TextView tvClass;
    @Bind(R.id.tv_QQ)
    TextView tvQQ;
    @Bind(R.id.tv_tel)
    TextView tvTel;
    @Bind(R.id.iv_user)
    ImageView ivUser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);

        Bmob.initialize(this, "04e905bba1912c9e7d3972bdebe82ff6");

        //得到intent传递的数据
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");


        // 从数据库中获取当前用户的信息
        String userId = user.getUsername();
        String password = "********";    // 密码无法获取，使用**代替
        String realName = user.getRealName();
        String email = user.getEmail();
        String college = user.getCollege();
        String Class = user.getUserClass();
        String QQ = user.getQQ();
        String tel = user.getTel();

        // 将获取的用户信息放到TextView中
        tvUserId.setText(userId);
        tvPassword.setText(password);
        tvName.setText(realName);
        tvEmail.setText(email);
        tvCollege.setText(college);
        tvClass.setText(Class);
        tvQQ.setText(QQ);
        tvTel.setText(tel);

    }

    @OnClick({R.id.back, R.id.update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.update:
                Intent intent = new Intent(this, UpdateUserInfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
                break;
        }
    }
}