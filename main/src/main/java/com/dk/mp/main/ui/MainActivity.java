package com.dk.mp.main.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dk.mp.core.adapter.MyFragmentPagerAdapter;
import com.dk.mp.core.dialog.AlertDialog;
import com.dk.mp.core.setting.ui.SettingActivity;
import com.dk.mp.core.ui.BaseActivity;
import com.dk.mp.core.ui.BaseFragment;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.main.R;
import com.dk.mp.txl.ui.TxlFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener , View.OnClickListener{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    MainFragment mainFragment = new MainFragment();
    TxlFragment phoneFragment = new TxlFragment();
    ManagerFragment manageFragment = new ManagerFragment();

    private List<String> titles;
    private List<BaseFragment> fragments;
    private int[] images = new int[3];
    private int[] imagesSelected = new int[3];

    private TextView title;
    private SimpleDraweeView loginmess;
    private ImageView search;
    private String theme="标准";
//    public CoreSharedPreferencesHelper preference;

    @Override
    protected int getLayoutID() {
        return R.layout.main_activity;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_activity);
//        initTheme();
//        initView();
//    }

    @Override
    protected void initView() {
        super.initView();
        theme =  getSharedPreferences().getValue("font_type");
        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("通讯录");
        titles.add("管理");
        images = new int[]{R.mipmap.main_main,R.mipmap.main_phone,R.mipmap.main_manage};
        imagesSelected = new int[]{R.mipmap.main_main_selected,R.mipmap.main_phone_selected,R.mipmap.main_manage_selected};
        for(int i=0;i<titles.size();i++){
            mTabLayout.addTab(mTabLayout.newTab().setIcon(images[i]).setText(titles.get(i)));
        }

        fragments = new ArrayList<>();
        fragments.add(mainFragment);
        fragments.add(phoneFragment);
        fragments.add(manageFragment);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        mViewPager.setOffscreenPageLimit ( fragments.size ( ) );
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        resetTablayout();

        mTabLayout.addOnTabSelectedListener(this);

        title = (TextView) findViewById(R.id.oa_title);
        title.setText("自定义OA");
        loginmess = (SimpleDraweeView) findViewById(R.id.loginMess);
        loginmess.setOnClickListener(this);
        search = (ImageView) findViewById(R.id.oa_search);
    }

    /**
     * 初始化皮肤
     */
    protected void initTheme ( ) {
        preference = getSharedPreferences();
        String value = preference.getValue("font_type");
        if("大".equals(value)) {
            this.setTheme(com.dk.mp.core.R.style.style_large);
        }else if("特大".equals(value)){
            this.setTheme(com.dk.mp.core.R.style.style_big);
        }else{
            this.setTheme(com.dk.mp.core.R.style.style_norm);
        }
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.news_title);
        tv.setText(titles.get(position));
        tv.setTextColor(position == 0 ? Color.rgb(33,150,243) : Color.rgb(151,151,151));
        ImageView img = (ImageView) v.findViewById(R.id.imageView_title);
        img.setImageResource(position == 0 ? imagesSelected[position] : images[position]);
        return v;
    }

    private void resetTablayout() {
        for (int i=0;i<mTabLayout.getTabCount();i++){
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab!=null){
                tab.setCustomView(getTabView(i));
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        ImageView image = (ImageView) tab.getCustomView().findViewById(R.id.imageView_title);
        image.setImageResource(imagesSelected[tab.getPosition()]);
        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.news_title);
        textView.setTextColor(Color.rgb(33,150,243));

        if (tab.getPosition() != 0) {
            loginmess.setVisibility(View.INVISIBLE);
            search.setVisibility(View.INVISIBLE);
        } else {
            loginmess.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);
        }

        switch (tab.getPosition()) {
            case 0:
                title.setText("自定义OA");
                break;
            case 1:
                title.setText("通讯录");
                break;
            case 2:
                title.setText("管理");
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        ImageView image = (ImageView) tab.getCustomView().findViewById(R.id.imageView_title);
        image.setImageResource(images[tab.getPosition()]);
        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.news_title);
        textView.setTextColor(Color.rgb(151,151,151));
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View view) {
        if (view == loginmess) {
            startActivity(new Intent(this, SettingActivity.class));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog alertDialog = new AlertDialog(mContext);
            alertDialog.exitApp();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);//不设置进入退出动画
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringUtils.isNotEmpty(theme) && !theme.equals(getSharedPreferences().getValue("font_type"))) {
            reload();
        } else if (!StringUtils.isNotEmpty(theme) && StringUtils.isNotEmpty(getSharedPreferences().getValue("font_type"))) {
            reload();
        }
    }

//    public CoreSharedPreferencesHelper getSharedPreferences() {
//        if (preference == null){
//            preference = new CoreSharedPreferencesHelper(this);
//        }
//        return preference;
//    }
}
