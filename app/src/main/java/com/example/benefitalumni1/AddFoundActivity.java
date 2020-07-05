package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.benefitalumni1.entity.User;
import com.example.benefitalumni1.util.Global;
import com.loopj.android.image.SmartImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddFoundActivity extends Activity {
    private EditText et_foundName;
    private EditText et_foundType;
    private EditText et_foundTime;
    private EditText et_foundDetail;
    private EditText et_foundContact;
    private SmartImageView siv_pic;

    private int RESULT_LOAD_IMG = 1;
    private String imgPath;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_add_found);

        et_foundName = (EditText) findViewById(R.id.et_foundName);
        et_foundType = (EditText) findViewById(R.id.et_foundType);
        et_foundTime = (EditText) findViewById(R.id.et_foundTime);
        et_foundDetail = (EditText) findViewById(R.id.et_foundContact);
        et_foundContact = (EditText) findViewById(R.id.et_foundContact);
        siv_pic = (SmartImageView) findViewById(R.id.siv_pic);

        //获取当前登录用户
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
    }

    //确定添加拾物信息
    public void addFound(View view) {
        String encodeStr = "";
        if (imgPath != null && !imgPath.isEmpty()) {
            //将图片转化成字符串
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            Log.i("图片路径", imgPath);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            byte[] bytes = os.toByteArray();
            byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
            encodeStr = new String(encode);

        } else {
            Toast.makeText(getApplicationContext(), "至少选择一个图片哦",
                    Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpClient client = new OkHttpClient();
        String urlStr = Global.ROOT + "AddFoundItem";
        FormBody paramBody = new FormBody.Builder()
                .add("foundItemName", et_foundName.getText().toString())
                .add("type", et_foundType.getText().toString())
                .add("foundTime", et_foundTime.getText().toString())
                .add("detail", et_foundDetail.getText().toString())
                .add("userId", user.getUserId() + "")
                .add("pic", encodeStr)
                .add("contact", et_foundContact.getText().toString())
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
                        Intent intent = new Intent(AddFoundActivity.this, FoundActivity.class);
                        intent.putExtra("user", user);
                        Toast.makeText(AddFoundActivity.this, "添加成功！", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    //上传失物图片
    public void uploadFound(View view) {

        //从相册中选择图片
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

    }

    //当图片被选中的返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                // 获取游标
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgPath = cursor.getString(columnIndex);
                cursor.close();
                siv_pic.setImageBitmap(BitmapFactory.decodeFile(imgPath));
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    //返回
    public void back(View view) {
        finish();
    }
}
