package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benefitalumni1.model.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UpdateUserActivity extends Activity {


    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.et_college)
    EditText etCollege;
    @Bind(R.id.et_class)
    EditText etClass;
    @Bind(R.id.et_QQ)
    EditText etQQ;
    @Bind(R.id.et_tel)
    EditText etTel;
    @Bind(R.id.sureUpdate)
    Button sureDelete;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_update_user_info);
        ButterKnife.bind(this);

        // 在第二个参数添加在Bmob上创建项目的ID
        Bmob.initialize(this, "Application ID");

        //得到intent传递的数据
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        //回显用户数据
        etName.setText(user.getRealName());
        etEmail.setText(user.getEmail());
        Log.d("-----------email", "onCreate: 用户的邮箱为:" + user.getEmail());
        etCollege.setText(user.getCollege());
        etClass.setText(user.getUserClass());
        etQQ.setText(user.getQQ());
        etTel.setText(user.getTel());

    }



    @OnClick({R.id.back, R.id.sureUpdate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(UpdateUserActivity.this, UserActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
                break;
            case R.id.sureUpdate:
                userUpdate();
                break;
        }
    }

    // 更新用户信息
    private void userUpdate() {

        // 获取更新后的数据
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String college = etCollege.getText().toString();
        String Class = etClass.getText().toString();
        String QQ = etQQ.getText().toString();
        String tel = etTel.getText().toString();

        User updateUser = new User();
        updateUser.setRealName(name);
        updateUser.setEmail(email);
        updateUser.setCollege(college);
        updateUser.setUserClass(Class);
        updateUser.setQQ(QQ);
        updateUser.setTel(tel);

        updateUser.update(user.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
//                    toast("更新成功:"+p2.getUpdatedAt());
                    Toast.makeText(UpdateUserActivity.this,"用户信息更新成功，如更改邮箱请重新验证"+updateUser.getUpdatedAt(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateUserActivity.this, UserActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }else{
//                    toast("更新失败：" + e.getMessage());
                    Toast.makeText(UpdateUserActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}