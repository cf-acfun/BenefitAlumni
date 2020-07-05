package com.example.benefitalumni1.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZZZ on 2020/3/27.
 */
public class SPSave {
    //保存用户名和密码
    public static boolean saveUserInfo(Context context, String userName, String pwd) {
        try {
            //获取SharedPreferences对象
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            //把数据写到文件中(默认路径，data/data/<包名>/shared_prefs/目录下) 文件格式是XXX.xml
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("userName", userName);
            editor.putString("pwd", pwd);
            editor.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //从文件中读取用户信息
    public static Map<String, String> getUserInfo(Context context) {
        try {
            //获取SharedPreferences对象
            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            String userName = sp.getString("userName", null);
            String pwd = sp.getString("pwd", null);
            Map<String, String> userMap = new HashMap<String, String>();
            userMap.put("userName", userName);
            userMap.put("pwd", pwd);
            return userMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
