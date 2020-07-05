package com.example.benefitalumni1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.benefitalumni1.model.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class RegisterActivity extends Activity {

    @Bind(R.id.edt_registerName)
    EditText edtRegisterName;
    @Bind(R.id.edt_registerPassword)
    EditText edtRegisterPassword;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.edt_registerEmail)
    EditText edtEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Bmob.initialize(this,"04e905bba1912c9e7d3972bdebe82ff6");



    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        saveData();
        readData();

    }


    private void saveData() {
        // 获取注册信息
        final String name = edtRegisterName.getText().toString();
        final String password = edtRegisterPassword.getText().toString();
        final String Email = edtEmail.getText().toString();

        //Person p2 = new Person();
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(Email);

        /*user.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    //toast("添加数据成功，返回objectId为："+objectId);
                    Toast.makeText(RegisterActivity.this,"添加数据成功，返回objectId为：" + objectId,Toast.LENGTH_LONG).show();
                    Log.d("message", "done: 添加数据成功，返回objectId为：" + objectId);
                }else{
                    //toast("创建数据失败：" + e.getMessage());
                    Log.d("message", "done: 创建数据失败：" + e.getMessage());
                }
            }
        }); */

        // 注册
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    Toast.makeText(RegisterActivity.this,"恭喜注册成功！请进行邮箱验证完成注册",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this,"注册失败！" + e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void readData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}