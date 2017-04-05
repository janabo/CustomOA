package com.dk.mp.core.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.dk.mp.core.R;
import com.dk.mp.core.entity.LoginMsg;
import com.dk.mp.core.http.HttpUtil;
import com.dk.mp.core.http.request.HttpListener;
import com.dk.mp.core.login.LoginActivity;
import com.dk.mp.core.util.CoreSharedPreferencesHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：janabo on 2016/12/14 15:08
 */
public class SplashActivity extends AppCompatActivity {
    public CoreSharedPreferencesHelper preference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_splash);
        preference = new CoreSharedPreferencesHelper(this);
        initialize();
    }


    protected void initialize() {
        mHander.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginMsg loginMsg = preference.getLoginMsg();
                if (loginMsg == null) {
                    mHander.sendEmptyMessage(0);
                } else {
                    login();
                }
            }
        },2000);
    }

    private void login(){
        final LoginMsg loginMsg = preference.getLoginMsg();
        if (loginMsg == null) return;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", loginMsg.getUid());
        map.put("password", loginMsg.getPsw());
        HttpUtil.getInstance().postJsonObjectRequest("login", map, new HttpListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result)  {
                try {
                    if (result.getInt("code") == 200) {
                        preference.setUserInfo(result.getJSONObject("data").toString());
                        mHander.sendEmptyMessage(2);
                    }else{
                        mHander.sendEmptyMessage(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    mHander.sendEmptyMessage(0);
                }
            }
            @Override
            public void onError(VolleyError error) {
                mHander.sendEmptyMessage(0);
            }
        });
    }

    Handler mHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    navigateToLogin("");
                    break;
                case 2:
                    loginSuccess();
                    break;
                default:
                    break;
            }
        }
    };

    private void loginSuccess() {
        if (isPasswordEmpty(SplashActivity.this)) {
            navigateToLock("set");
        } else {
            navigateToLock("vertify");
        }
    }

    /**
     * 跳转登录
     * @param action
     */
    private void navigateToLogin(String action) {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        intent.putExtra("from", "splash");
        startActivity(intent);
        finish();
    }

    /**
     * 跳转手势锁界面
     * @param action
     */
    private void navigateToLock(String action) {
        Intent intent = new Intent();
        intent.putExtra("from", "splash");
        intent.putExtra("action", action);
        intent.setAction("lock");
        startActivity(intent);
        finish();
    }

    public static boolean isPasswordEmpty(Context context) {
        SharedPreferences sp = context.getSharedPreferences("LocusPassWordView", Context.MODE_PRIVATE);
        return TextUtils.isEmpty(sp.getString("password", ""));
    }
}
