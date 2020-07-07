package com.example.benefitalumni1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import butterknife.OnClick;


public class MainActivity extends Activity {


    @butterknife.Bind(R.id.btn_skip)
    Button btn_Skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使图片铺满屏幕
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //去掉状态栏
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);

        // sleep 6 秒钟进入login界面
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 6; i >= 1; i--) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 在子线程中发送消息
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = "倒计时 " + i;
                    // 发送消息
                    handler.sendMessage(msg);

                }
                goToLoginActivity();
            }
        }).start();


    }

    // 跳转到主界面
    public void goToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    //创建一个 Handler 更新UI，接到消息后更新UI
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                btn_Skip.setText(msg.obj.toString());
            }
        }
    };

    @OnClick(R.id.btn_skip)
    public void onClick(){

    }
}
