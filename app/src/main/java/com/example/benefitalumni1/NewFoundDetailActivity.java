package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import cn.bmob.v3.listener.UpdateListener;

public class NewFoundDetailActivity extends Activity {

    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.tv_foundName)
    TextView tvFoundName;
    @Bind(R.id.tv_foundType)
    TextView tvFoundType;
    @Bind(R.id.tv_foundDetail)
    TextView tvFoundDetail;
    @Bind(R.id.tv_foundTime)
    TextView tvFoundTime;
    @Bind(R.id.siv_pic)
    SmartImageView sivPic;
    @Bind(R.id.tv_foundContact)
    TextView tvFoundContact;
    @Bind(R.id.btn_deleteFound)
    Button btnDeleteFound;
    private User user;
    private FoundItem foundItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_found_detail);
        ButterKnife.bind(this);
        Bmob.initialize(this, "04e905bba1912c9e7d3972bdebe82ff6");

        //获取当前登录用户
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        foundItem = (FoundItem) intent.getSerializableExtra("foundItem");

        //如果此条拾物信息的userId和当前用户的userId相等，说明该拾物信息是当前用户创建，则该用户有删除的权限，则显示删除按钮
        if (foundItem.getFoundItemId().equals(user.getObjectId()) ) {
            Log.d("id_message", "onCreate: 用户名和丢失物品的ID一致");
            btnDeleteFound.setVisibility(View.VISIBLE);
        }

        // 将本拾到物品的详细信息放到View中
        String name = foundItem.getFoundItemName();
        String type = foundItem.getType();
        String time = foundItem.getFoundTime();
        String detail = foundItem.getDetail();
        // 图片上传还有一点问题尚未解决
        //String img =
        String contact = foundItem.getContact();

        tvFoundName.setText(name);
        tvFoundType.setText(type);
        tvFoundTime.setText(time);
        tvFoundDetail.setText(detail);
        tvFoundContact.setText(contact);


    }

    @OnClick({R.id.back, R.id.btn_deleteFound})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_deleteFound:
                deleteFound();
                break;
        }
    }

    private void deleteFound() {
        // 找到当前物品的ID
        String id = foundItem.getObjectId();
        Log.d("id_message", "onClick: 要删除物品的ID为：" + id);
        // 删除该条数据
        foundItem.setObjectId(id);
        foundItem.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
//                    toast("删除成功:"+p2.getUpdatedAt());
                    Toast.makeText(NewFoundDetailActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
//                    toast("删除失败：" + e.getMessage());
                    Toast.makeText(NewFoundDetailActivity.this,"删除失败！",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}