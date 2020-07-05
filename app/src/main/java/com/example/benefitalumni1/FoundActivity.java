package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.benefitalumni1.entity.FoundItem;
import com.example.benefitalumni1.entity.User;
import com.example.benefitalumni1.util.Global;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.image.SmartImageView;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//拾物模块界面
public class FoundActivity extends Activity {
    private ListView lv;
    private List<FoundItem> list;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_found);

        //获取当前登陆对象
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        lv = (ListView) findViewById(R.id.lv);
        getAllFoundItem();//查询所有的拾物
    }
    //跳转到新增拾物信息页面
    public void goAddFoundItem(View view) {
        Intent intent = new Intent(this, AddFoundActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void getAllFoundItem() {
        OkHttpClient client = new OkHttpClient();
        String urlStr = Global.ROOT + "GetAllFoundItem";

        Request request = new Request.Builder().url(urlStr).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<FoundItem>>() {
                }.getType();
                list = gson.fromJson(str, listType);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (list != null) {
                            MyAdapter myAdapter = new MyAdapter(list);
                            lv.setAdapter(myAdapter);
                        }
                    }
                });
            }
        });
    }

    //适配器
    public class MyAdapter extends BaseAdapter {
        List<FoundItem> list;
        ListView listView;

        public MyAdapter(List list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (listView == null) {
                listView = (ListView) parent;
            }
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item, null);
                holder = new ViewHolder();
                holder.siv = (SmartImageView) convertView.findViewById(R.id.siv_icon);
                holder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
                holder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
                holder.item = (RelativeLayout) convertView.findViewById(R.id.item);//每一条item
                parent.setTag(holder);
            } else {
                holder = (ViewHolder) parent.getTag();
            }
            final FoundItem foundItem = list.get(position);
            holder.tv_type.setText(foundItem.getType().toString());
            holder.siv.setImageUrl(Global.PicPath + foundItem.getPic().toString());
            holder.tv_description.setText(foundItem.getDetail());
            //给每一个item添加单击事件
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FoundActivity.this, FoundDetailActivity.class);
                    intent.putExtra("foundItem", foundItem);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
            });

            return convertView;
        }

        class ViewHolder {
            SmartImageView siv;
            TextView tv_type;
            TextView tv_description;
            RelativeLayout item;
        }
    }

    //返回HomeActivity主界面
    public void back(View view) {
        finish();
    }
}
