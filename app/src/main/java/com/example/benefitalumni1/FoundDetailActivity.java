package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.benefitalumni1.entity.FoundItem;
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

//拾物详情页面
public class FoundDetailActivity extends Activity {
    private TextView tv_foundName;
    private TextView tv_foundType;
    private TextView tv_foundTime;
    private TextView tv_foundDetail;
    private TextView tv_foundContact;
    private SmartImageView siv_pic;
    private Button btn_deleteFound;
    private FoundItem foundItem;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_found_detail);

        tv_foundName = (TextView) findViewById(R.id.tv_foundName);
        tv_foundType = (TextView) findViewById(R.id.tv_foundType);
        tv_foundTime = (TextView) findViewById(R.id.tv_foundTime);
        tv_foundDetail = (TextView) findViewById(R.id.tv_foundDetail);
        tv_foundContact = (TextView) findViewById(R.id.tv_foundContact);
        siv_pic = (SmartImageView) findViewById(R.id.siv_pic);


        //获取当前登录用户
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        foundItem = (FoundItem) intent.getSerializableExtra("foundItem");


        tv_foundName.setText(foundItem.getFoundItemName().toString());
        tv_foundType.setText(foundItem.getType().toString());
        tv_foundTime.setText(foundItem.getFoundTime().toString());
        tv_foundDetail.setText(foundItem.getDetail().toString());
        siv_pic.setImageUrl(Global.PicPath + foundItem.getPic().toString());
        tv_foundContact.setText(foundItem.getContact());

        //如果此条拾物信息的userId和当前用户的userId相等，说明该拾物信息是当前用户创建，则该用户有删除的权限，则显示删除按钮
        //同时管理员admin也有权限删除
        if (foundItem.getUserId() == user.getUserId() || user.getUserName().equals("admin")) {
            btn_deleteFound = (Button) findViewById(R.id.btn_deleteFound);
            btn_deleteFound.setVisibility(View.VISIBLE);
        }


    }



    //删除拾物信息
    public void deleteFound(View view) {
        OkHttpClient client = new OkHttpClient();
        String urlStr = Global.ROOT + "DeleteFoundItem";
        FormBody paramBody = new FormBody.Builder()
                .add("foundItemId", foundItem.getFoundItemId() + "")
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
                        Intent intent = new Intent(FoundDetailActivity.this, FoundActivity.class);
                        intent.putExtra("user", user);
                        Toast.makeText(FoundDetailActivity.this, "删除成功！", Toast.LENGTH_LONG).show();
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
