package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benefitalumni1.entity.LostItem;
import com.example.benefitalumni1.entity.User;
import com.example.benefitalumni1.util.Global;
import com.loopj.android.image.SmartImageView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//失物详情页面
public class LostDetailActivity extends Activity {

    private TextView tv_lostName;
    private TextView tv_lostType;
    private TextView tv_lostTime;
    private TextView tv_lostDetail;
    private TextView tv_lostContact;
    private SmartImageView siv_pic;
    private Button btn_deleteLost;
    private LostItem lostItem;
    private User user;

    private int RESULT_LOAD_IMG = 1;
    private String imgPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_lost_detail);

        tv_lostName = (TextView) findViewById(R.id.tv_lostName);
        tv_lostType = (TextView) findViewById(R.id.tv_lostType);
        tv_lostTime = (TextView) findViewById(R.id.tv_lostTime);
        tv_lostDetail = (TextView) findViewById(R.id.tv_lostDetail);
        tv_lostContact = (TextView) findViewById(R.id.tv_lostContact);
        siv_pic = (SmartImageView) findViewById(R.id.siv_pic);

        //获取当前登录用户
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        lostItem = (LostItem) intent.getSerializableExtra("lostItem");


        tv_lostName.setText(lostItem.getLostItemName().toString());
        tv_lostType.setText(lostItem.getType().toString());
        tv_lostTime.setText(lostItem.getLostTime().toString());
        tv_lostDetail.setText(lostItem.getDetail().toString());
        siv_pic.setImageUrl(Global.PicPath + lostItem.getPic().toString());
        tv_lostContact.setText(lostItem.getContact());

        //如果此条失物信息的userId和当前用户的userId相等，说明该失物信息是当前用户创建，则该用户有删除的权限，则显示删除按钮
        //同时管理员admin也有权限删除
        if (lostItem.getUserId() == user.getUserId() || user.getUserName().equals("admin")) {
            btn_deleteLost = (Button) findViewById(R.id.btn_deleteLost);
            btn_deleteLost.setVisibility(View.VISIBLE);
        }

    }

    //删除失物信息
    public void deleteLost(View view) {
        OkHttpClient client = new OkHttpClient();
        String urlStr = Global.ROOT + "DeleteLostItem";
        FormBody paramBody = new FormBody.Builder()
                .add("lostItemId", lostItem.getLostItemId() + "")
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
                        //添加成功跳转到拾物列表
                        Intent intent = new Intent(LostDetailActivity.this, LostActivity.class);
                        intent.putExtra("user", user);
                        Toast.makeText(LostDetailActivity.this, "删除成功！", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    //返回
    public void back(View view) {
        finish();
    }
}
