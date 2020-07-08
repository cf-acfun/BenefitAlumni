package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.benefitalumni1.model.User;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {

    @Bind(R.id.edt_name)
    EditText edt_name;
    @Bind(R.id.edt_password)
    EditText edt_password;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // 在第二个参数添加在Bmob上创建项目的ID
        Bmob.initialize(this, "Application ID");

    }

    // 用户登录
    @OnClick(R.id.btn_login)
    public void login(){
        String name = edt_name.getText().toString();
        String password = edt_password.getText().toString();

        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    //intent.putExtra("user", user);    // 发送用户名
                    intent.putExtra("user",user);
                    startActivity(intent);
                    //startActivityForResult(intent,999);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"用户名或密码错误！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @OnClick(R.id.btn_register)
    public void Register() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
