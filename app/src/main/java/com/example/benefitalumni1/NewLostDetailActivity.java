package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benefitalumni1.model.LostItem;
import com.example.benefitalumni1.model.User;
import com.loopj.android.image.SmartImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class NewLostDetailActivity extends Activity {

    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.tv_lostName)
    TextView tvLostName;
    @Bind(R.id.ll_lostName)
    LinearLayout llLostName;
    @Bind(R.id.tv_lostType)
    TextView tvLostType;
    @Bind(R.id.ll_lostType)
    LinearLayout llLostType;
    @Bind(R.id.tv_lostDetail)
    TextView tvLostDetail;
    @Bind(R.id.ll_lostDetail)
    LinearLayout llLostDetail;
    @Bind(R.id.tv_lostTime)
    TextView tvLostTime;
    @Bind(R.id.ll_lostTime)
    LinearLayout llLostTime;
    @Bind(R.id.siv_pic)
    SmartImageView sivPic;
    @Bind(R.id.tv_lostContact)
    TextView tvLostContact;
    @Bind(R.id.ll_lostContact)
    LinearLayout llLostContact;
    @Bind(R.id.btn_deleteLost)
    Button btnDeleteLost;
    private LostItem lostItem;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_lost_detail);
        ButterKnife.bind(this);

        // 在第二个参数添加在Bmob上创建项目的ID
        Bmob.initialize(this, "Application ID");

        //获取当前登录用户
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        lostItem = (LostItem) intent.getSerializableExtra("lostItem");

        Log.d("id_message", "onCreate: 丢失物品ID为：" + lostItem.getLostItemId());
        Log.d("id_message", "onCreate: 用户ID为：" + user.getObjectId());

        //如果此条失物信息的userId和当前用户的userId相等，说明该失物信息是当前用户创建，则该用户有删除的权限，则显示删除按钮
        if (lostItem.getLostItemId().equals(user.getObjectId()) ) {
            Log.d("id_message", "onCreate: 用户名和丢失物品的ID一致");
            btnDeleteLost.setVisibility(View.VISIBLE);
        }

        // 将本丢失物品的详细信息放到View中
        String name = lostItem.getLostItemName();
        String type = lostItem.getType();
        String time = lostItem.getLostTime();
        String detail = lostItem.getDetail();
        // 图片上传还有一点问题尚未解决
        //String img =
        String contact = lostItem.getContact();

        tvLostName.setText(name);
        tvLostType.setText(type);
        tvLostTime.setText(time);
        tvLostDetail.setText(detail);
        tvLostContact.setText(contact);

    }

    // 删除本人发布的丢失物品
    @OnClick(R.id.btn_deleteLost)
    public void onClick() {
        // 找到当前物品的ID
        String id = lostItem.getObjectId();
        Log.d("id_message", "onClick: 要删除物品的ID为：" + id);
        // 删除该条数据
        lostItem.setObjectId(id);
        lostItem.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
//                    toast("删除成功:"+p2.getUpdatedAt());
                    Toast.makeText(NewLostDetailActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
//                    toast("删除失败：" + e.getMessage());
                    Toast.makeText(NewLostDetailActivity.this,"删除失败！",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void back(View view){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}