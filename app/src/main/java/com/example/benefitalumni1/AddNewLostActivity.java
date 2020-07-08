package com.example.benefitalumni1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.benefitalumni1.model.LostItem;
import com.example.benefitalumni1.model.User;
import com.loopj.android.image.SmartImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class AddNewLostActivity extends Activity {

    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.et_lostName)
    EditText etLostName;
    @Bind(R.id.ll_lostName)
    LinearLayout llLostName;
    @Bind(R.id.et_lostType)
    EditText etLostType;
    @Bind(R.id.ll_lostType)
    LinearLayout llLostType;
    @Bind(R.id.et_lostDetail)
    EditText etLostDetail;
    @Bind(R.id.ll_lostDetail)
    LinearLayout llLostDetail;
    @Bind(R.id.et_lostTime)
    EditText etLostTime;
    @Bind(R.id.ll_lostTime)
    LinearLayout llLostTime;
    @Bind(R.id.siv_pic)
    SmartImageView sivPic;
    @Bind(R.id.et_lostContact)
    EditText etLostContact;
    @Bind(R.id.ll_lostContact)
    LinearLayout llLostContact;

    private User user;

    private int RESULT_LOAD_IMG = 1;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_add_lost);
        ButterKnife.bind(this);

        //获取当前登录用户
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        // 在第二个参数添加在Bmob上创建项目的ID
        Bmob.initialize(this, "Application ID");

    }

    // 添加失物信息
    public void addLost(View view){
        LostItem lostitem = new LostItem();
        String lostName = etLostName.getText().toString();
        String lostType = etLostType.getText().toString();
        String lostTime = etLostTime.getText().toString();
        String lostDetail = etLostDetail.getText().toString();
        String lostId = user.getObjectId();    // 将丢失物品的id与user绑定。
        Log.d("message", "+++++-----------addLost: 用户id为：" + lostId);
        //String lostImg = imgPath;
        //String lostImg = "";
        String lostContact = etLostContact.getText().toString();

        lostitem.setLostItemName(lostName);
        lostitem.setType(lostType);
        lostitem.setLostTime(lostTime);
        lostitem.setDetail(lostDetail);
        lostitem.setContact(lostContact);
        lostitem.setUserName(user.getUsername());
        lostitem.setLostItemId(lostId);

        lostitem.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    //toast("添加数据成功，返回objectId为："+objectId);
                    Toast.makeText(AddNewLostActivity.this,"添加数据成功",Toast.LENGTH_SHORT).show();
                    // 添加数据成功之后直接返回上一个界面
                    finish();
                }else{
                    //toast("创建数据失败：" + e.getMessage());
                    Toast.makeText(AddNewLostActivity.this,"添加数据失败",Toast.LENGTH_SHORT).show();

                }
            }
        });

        // 上传图片
//        BmobFile bmobFile = new BmobFile(new File(lostImg));
//        bmobFile.upload(new UploadFileListener() {
//            @Override
//            public void done(BmobException e) {
//                if (e == null){
//                    Toast.makeText(AddNewLostActivity.this,"文件上传成功",Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(AddNewLostActivity.this,"文件上传失败" + e,Toast.LENGTH_SHORT).show();
//                    Log.d("message", "done: 文件上传失败"+e);
//                }
//            }
//        });

    }

    //上传失物图片
//    public void uploadLost(View view) {
//
//        //从相册中选择图片
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
//
//    }
//
//    //当图片被选中的返回结果
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
//
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                // 获取游标
//                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                cursor.moveToFirst();
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                imgPath = cursor.getString(columnIndex);
//                cursor.close();
//                sivPic.setImageBitmap(BitmapFactory.decodeFile(imgPath));
//            } else {
//                Toast.makeText(this, "You haven't picked Image",
//                        Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
//        }
//    }

    //返回
    public void back(View view) {
        finish();
    }
}