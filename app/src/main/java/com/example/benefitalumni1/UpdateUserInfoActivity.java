package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.benefitalumni1.entity.User;
import com.example.benefitalumni1.util.Global;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateUserInfoActivity extends Activity {
    private User user;
    private EditText et_name;
    private EditText et_pwd;
    private EditText et_rePwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_update_user_info);

        //得到intent传递的数据
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        //回显用户数据
//        et_name = (EditText) findViewById(R.id.et_name);
//        et_pwd = (EditText) findViewById(R.id.et_pwd);
//        et_rePwd = (EditText) findViewById(R.id.et_rePwd);

        //回显用户数据
        et_name.setText(user.getName().toString());
    }

    //修改用户信息
    public void updateInfo(View view) {
        user.setName(et_name.getText().toString().trim());
        user.setPwd(et_pwd.getText().toString().trim());
        //判断用户名或密码或确认密码是否为空
        if (!user.getName().equals("") && !user.getPwd().equals("")) {
            OkHttpClient client = new OkHttpClient();
            String urlStr = Global.ROOT + "UpdateUserInfo";
            FormBody paramBody = new FormBody.Builder()
                    .add("userId", user.getUserId() + "")
                    .add("pwd", user.getPwd().toString())
                    .add("name", user.getName().toString())
                    .build();
            Request request = new Request.Builder().url(urlStr).post(paramBody).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //主UI界面的修改
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UpdateUserInfoActivity.this, "更新用户信息成功！", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(UpdateUserInfoActivity.this, UserActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "请输入完整信息！", Toast.LENGTH_LONG).show();
            return;
        }
    }

    //返回
    public void back(View view){
        Intent intent = new Intent(UpdateUserInfoActivity.this, UserActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

}
