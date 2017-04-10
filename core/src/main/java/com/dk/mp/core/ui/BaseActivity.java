package com.dk.mp.core.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dk.mp.core.R;
import com.dk.mp.core.util.CoreSharedPreferencesHelper;
import com.google.gson.Gson;

/**
 * 作者：janabo on 2017/4/10 09:04
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext = this;
    public CoreSharedPreferencesHelper preference;
    private Gson gson;

    /**
     * @return 界面布局
     */
    protected abstract
    @LayoutRes
    int getLayoutID();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setContentView ( getLayoutID ( ) );
        getSharedPreferences();
        initView();
    }

    /**
     * 初始化
     */
    protected void initView ( ) {}

    /**
     * 初始化皮肤
     */
    protected void initTheme ( ) {
        preference = getSharedPreferences();
        String value = preference.getValue("font_type");
        if("大".equals(value)) {
            this.setTheme(R.style.style_large);
        }else if("特大".equals(value)){
            this.setTheme(R.style.style_big);
        }else{
            this.setTheme(R.style.style_norm);
        }
    }

    /**
     * 初始化皮肤.
     * @param title 标题栏文字
     */
    public void setTitle(int title) {
        setTitle(getReString(title));
    }


    /**
     * 获取string
     * @param string R.string.x
     * @return  ""
     */
    public String getReString(int string) {
        return getResources().getString(string);
    }

    public CoreSharedPreferencesHelper getSharedPreferences() {
        if (preference == null){
            preference = new CoreSharedPreferencesHelper(this);
        }
        return preference;
    }

    public Gson getGson() {
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }

    /**
     * @param title 标题栏文字
     */
    public void setTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.title);
        textView.setText(title);
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back();
            }
        });
    }

    /**
     * 返回
     */
    public void back() {
        onBackPressed();
    }
}
