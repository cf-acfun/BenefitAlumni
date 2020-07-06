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

import com.example.benefitalumni1.model.FoundItem;
import com.example.benefitalumni1.model.User;
import com.loopj.android.image.SmartImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddNewFoundActivity extends Activity {

    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.et_foundName)
    EditText etFoundName;
    @Bind(R.id.et_foundType)
    EditText etFoundType;
    @Bind(R.id.et_foundDetail)
    EditText etFoundDetail;
    @Bind(R.id.et_foundTime)
    EditText etFoundTime;
    @Bind(R.id.siv_pic)
    SmartImageView sivPic;
    @Bind(R.id.et_foundContact)
    EditText etFoundContact;
    @Bind(R.id.addFound)
    Button addFound;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_add_found);
        ButterKnife.bind(this);

        // 获取当前登录用户
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        Bmob.initialize(this, "04e905bba1912c9e7d3972bdebe82ff6");
    }

    @OnClick({R.id.back, R.id.addFound})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.addFound:
                addFoundMessage();
                break;
        }
    }

    private void addFoundMessage() {
        FoundItem foundItem = new FoundItem();
        String foundName = etFoundName.getText().toString();
        String foundType = etFoundType.getText().toString();
        String foundTime = etFoundTime.getText().toString();
        String foundDetail = etFoundDetail.getText().toString();
        String foundId = user.getObjectId();    // 将丢失物品的id与user绑定。
        Log.d("message", "+++++-----------addLost: 用户id为：" + foundId);
        // 图片上传问题尚待解决
        //String lostImg = imgPath;
        //String lostImg = "";
        String lostContact = etFoundContact.getText().toString();

        foundItem.setFoundItemName(foundName);
        foundItem.setType(foundType);
        foundItem.setFoundTime(foundTime);
        foundItem.setDetail(foundDetail);
        foundItem.setContact(lostContact);
        foundItem.setUserName(user.getUsername());
        foundItem.setFoundItemId(foundId);

        foundItem.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    //toast("添加数据成功，返回objectId为："+objectId);
                    Toast.makeText(AddNewFoundActivity.this,"添加数据成功",Toast.LENGTH_SHORT).show();
                    // 添加数据成功之后直接返回上一个界面
                    finish();
                }else{
                    //toast("创建数据失败：" + e.getMessage());
                    Toast.makeText(AddNewFoundActivity.this,"添加数据失败",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}