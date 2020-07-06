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

import com.example.benefitalumni1.model.FoundItem;
import com.example.benefitalumni1.model.User;
import com.loopj.android.image.SmartImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class NewFoundActivity extends Activity {

    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.iv_addFound)
    ImageView ivAddFound;
    private List<FoundItem> list_foundItem;
    private User user;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);      // 去掉标题栏
        setContentView(R.layout.activity_found);
        ButterKnife.bind(this);

        //获取当前登陆对象
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        getAllFoundItem();//查询所有的拾物


    }

    private void getAllFoundItem() {
        BmobQuery<FoundItem> query = new BmobQuery<>();
        query.findObjects(new FindListener<FoundItem>() {
            @Override
            public void done(List<FoundItem> list, BmobException e) {
                if (e == null) {
                    list_foundItem = list;
                    listAdapter = new ListAdapter(NewFoundActivity.this, list_foundItem);
                    lv.setAdapter(listAdapter);
                }
            }
        });

    }


    class ListAdapter extends BaseAdapter {

        private Context context;
        private List<FoundItem> list;

        public ListAdapter(Context context, List<FoundItem> list) {
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
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item, null);
                holder = new ViewHolder();
                holder.sivIcon = (SmartImageView) convertView.findViewById(R.id.siv_icon);
                holder.tvType = (TextView) convertView.findViewById(R.id.tv_type);
                holder.tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
                holder.item = (RelativeLayout) convertView.findViewById(R.id.item);//每一条item
                parent.setTag(holder);
            } else {
                holder = (ViewHolder) parent.getTag();
            }
            final FoundItem foundItem = list.get(position);
            holder.tvType.setText(foundItem.getType());
            // 获取图片
            //holder.sivIcon.setImageUrl(lostItem.getPic().toString());
            holder.tvDescription.setText(foundItem.getDetail());
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NewFoundActivity.this, NewFoundDetailActivity.class);
                    intent.putExtra("foundItem", foundItem);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
            });

            return convertView;
        }
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

    @OnClick({R.id.back, R.id.iv_addFound})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_addFound:
                Intent intent = new Intent(NewFoundActivity.this, AddNewFoundActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
                break;
        }
    }


}