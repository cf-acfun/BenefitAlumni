package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.benefitalumni1.entity.User;


public class UserInfoActivity extends Activity {
    private User user;
    private TextView tv_userName;
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_user_info);

        //得到intent传递的数据
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        tv_userName = (TextView) findViewById(R.id.tv_userName);
        tv_name = (TextView) findViewById(R.id.tv_name);

        tv_userName.setText(user.getUserName().toString());
        tv_name.setText(user.getName().toString());
    }

//    //修改用户信息
//    public void updateInfo(View view) {
//        user.setName(et_name.getText().toString().trim());
//        user.setPwd(et_pwd.getText().toString().trim());
//        //判断用户名或密码或确认密码是否为空
//        if (!user.getName().equals("") && !user.getPwd().equals("")) {
//            OkHttpClient client = new OkHttpClient();
//            String urlStr = Global.ROOT + "UpdateUserInfo";
//            FormBody paramBody = new FormBody.Builder()
//                    .add("userId", user.getUserId() + "")
//                    .add("pwd", user.getPwd().toString())
//                    .add("name", user.getName().toString())
//                    .build();
//            Request request = new Request.Builder().url(urlStr).post(paramBody).build();
//            Call call = client.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    //主UI界面的修改
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(UserInfoActivity.this, "更新用户信息成功！", Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(UserInfoActivity.this, HomeActivity.class);
//                            intent.putExtra("user", user);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                }
//            });
//        } else {
//            Toast.makeText(this, "请输入完整信息！", Toast.LENGTH_LONG).show();
//            return;
//        }
//    }

    //进入用户修改页面
    public void goUpdateUserInfo(View view) {
        Intent intent = new Intent(this, UpdateUserInfoActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    //返回HomeActivity主界面
    public void back(View view) {
//        Intent intent = new Intent();
//        setResult(999, intent);
        finish();
    }
}
