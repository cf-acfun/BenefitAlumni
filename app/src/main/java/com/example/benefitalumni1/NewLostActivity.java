package com.example.benefitalumni1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.benefitalumni1.model.LostItem;
import com.example.benefitalumni1.model.User;
import com.loopj.android.image.SmartImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

//失物模块界面
public class NewLostActivity extends Activity {

    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.lv)
    ListView listView;
    @Bind(R.id.iv_addLost)
    ImageView ivAddLost;
    //private ListView listView;
    private List<LostItem> list_lostItem;
    private User user;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_lost);
        ButterKnife.bind(this);

        // 在这里输入Bmob项目ID
        Bmob.initialize(this, "Application ID");

        //获取当前登陆对象
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        //listView = (ListView) findViewById(R.id.lv);
        getAllLostItem();//查询所有的失物


    }

    //跳转到新增失物信息页面
    public void goAddLostItem(View view) {
//        Intent intent = new Intent(this, AddLostActivity.class);
        Intent intent = new Intent(this, AddNewLostActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    private void getAllLostItem() {
        BmobQuery<LostItem> query = new BmobQuery<>();
        query.findObjects(new FindListener<LostItem>() {
            @Override
            public void done(List<LostItem> list, BmobException e) {
                if (e == null) {
                    list_lostItem = list;
                    listAdapter = new ListAdapter(NewLostActivity.this,list_lostItem);
                    listView.setAdapter(listAdapter);
                }
            }
        });

    }

    class ListAdapter extends BaseAdapter {

        private Context context;
        private List<LostItem> list;

        public ListAdapter(Context context, List<LostItem> list) {
            this.context = context;
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
        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.lv_item, null);
//            }

            ViewHolder holder = null;
            if (convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item,null);
                holder = new ViewHolder();
                holder.sivIcon = (SmartImageView) convertView.findViewById(R.id.siv_icon);
                holder.tvType = (TextView) convertView.findViewById(R.id.tv_type);
                holder.tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
                holder.item = (RelativeLayout) convertView.findViewById(R.id.item);//每一条item
                parent.setTag(holder);
            } else {
                holder = (ViewHolder) parent.getTag();
            }
            final LostItem lostItem = list.get(position);
            holder.tvType.setText(lostItem.getType());
            // 获取图片
            //holder.sivIcon.setImageUrl(lostItem.getPic().toString());
            holder.tvDescription.setText(lostItem.getDetail());
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NewLostActivity.this, NewLostDetailActivity.class);
                    intent.putExtra("lostItem", lostItem);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
            });

            return convertView;

        }

        class ViewHolder {
            // 图像
            @Bind(R.id.siv_icon)
            SmartImageView sivIcon;
            // 类型
            @Bind(R.id.tv_type)
            TextView tvType;
            // 描述
            @Bind(R.id.tv_description)
            TextView tvDescription;
            @Bind(R.id.item)
            RelativeLayout item;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }

            public ViewHolder() {

            }
        }
    }


    @OnClick({R.id.textView, R.id.iv_addLost})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView:
                Intent intent = new Intent(NewLostActivity.this, HomeActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_addLost:
                Intent intent1 = new Intent(this, AddNewLostActivity.class);
                intent1.putExtra("user", user);
                startActivity(intent1);
                finish();
                break;
        }
    }
}