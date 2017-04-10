package com.dk.mp.core.ui;

import android.animation.Animator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dk.mp.core.R;
import com.dk.mp.core.db.RealmHelper;
import com.dk.mp.core.entity.OaApp;
import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.util.CoreSharedPreferencesHelper;
import com.dk.mp.core.util.ImageUtil;
import com.dk.mp.core.util.PinyinUtil;
import com.dk.mp.core.util.SnackBarUtil;
import com.dk.mp.core.util.StringUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.dk.mp.core.application.MyApplication.getContext;

/**
 * 作者：janabo on 2016/12/14 14:53
 */
public abstract class MyActivity extends AppCompatActivity{
    public Context mContext = this;
    public CoreSharedPreferencesHelper preference;
    private Gson gson;

    private DrawerLayout drawerLayout;
    private Button homeButton;
    private LinearLayout itemContent;
    private String classname;
    FrameLayout frameLayout;

    private int x;
    private int y;
    private RealmHelper mRealmHelper;

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
//        setContentView ( getLayoutID ( ) );
        classname = this.getClass().getName();
        x = getIntent().getIntExtra("x",-10);
        y = getIntent().getIntExtra("y",-10);
        if(getLayoutID() == R.layout.mp_login) {
            setContentView ( getLayoutID ( ) );
            initView();
        }else{
            setContentView(R.layout.core);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(getLayoutID(), null);
            frameLayout = (FrameLayout) findViewById(R.id.id_content);
            frameLayout.addView(view);
            initView();
            initialize ( );
        }
        intentFilter2.addAction("flishall");
        registerReceiver(receiver, intentFilter2);
        getSharedPreferences();
        mRealmHelper = new RealmHelper(getContext(),preference);
        //在自己的应用初始Activity中加入如下两行代码
//        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
//        refWatcher.watch(this);
    }

    private IntentFilter intentFilter2 = new IntentFilter();
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!"com.dk.mp.main.ui.MainActivity".equals(classname)){
                finish();
            }
        }
    };

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
     * 初始化
     */
    protected void initView ( ) {}
    /**
     * 初始化
     */
    protected void initialize ( ) {
        initDock();
    }

    /**
     * 初始化皮肤.
     * @param title 标题栏文字
     */
    public void setTitle(int title) {
        setTitle(getReString(title));
    }

    public void setRightText(String text, View.OnClickListener listener) {
        try {
            TextView right_txt = (TextView) findViewById(R.id.right_txt);
            right_txt.setText(text);
            right_txt.setVisibility(View.VISIBLE);
            right_txt.setOnClickListener(listener);
        } catch (Exception e) {
        }
    }

    public void setRightTextColor(int color) {
        try {
            TextView right_txt = (TextView) findViewById(R.id.right_txt);
            right_txt.setTextColor(color);
        } catch (Exception e) {
        }
    }

    public void setRightText(String text, int color, View.OnClickListener listener) {
        try {
            TextView right_txt = (TextView) findViewById(R.id.right_txt);
            right_txt.setText(text);
            right_txt.setVisibility(View.VISIBLE);
            right_txt.setTextColor(color);
            right_txt.setOnClickListener(listener);
        } catch (Exception e) {
        }
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
        if (x != -10) {
            frameLayout.post(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Animator animator = createRevealAnimator(true, x, y);
                        animator.start();
                    }
                }
            });
        } else {
            onBackPressed();
        }
    }

    public void initDock(){
        final LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        homeButton = (Button) findViewById(R.id.homebutton);
        Drawable drawable1 = ContextCompat.getDrawable(this,R.mipmap.home);
        drawable1.setBounds(0, 0, 40, 40);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        homeButton.setCompoundDrawables(drawable1, null, null, null);//只放左边
        homeButton.setBackground(null);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                sendBroadcast(new Intent().setAction("flishall"));
            }
        });
        itemContent = (LinearLayout) findViewById(R.id.itemContent);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {}
            @Override
            public void onDrawerOpened(View drawerView) {
                itemContent.removeAllViews();
                List<OaItemEntity> apps = getRecentUseApp();
                if (apps != null) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
                    View endview = new View(mContext);
                    endview.setLayoutParams(params);
                    for (final OaItemEntity app : apps) {
                        View addview = new View(mContext);
                        addview.setLayoutParams(params);
                        itemContent.addView(addview);
                        View view = inflater.inflate(R.layout.gridview_item, null);
                        ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
                        TextView textView= (TextView) view.findViewById(R.id.item_text);
                        textView.setText(app.getLabel());
                        if(StringUtils.isNotEmpty(app.getUrl())) {
                            imageView.setImageResource(ImageUtil.getRes(app.getName()));
                        }else{
                            imageView.setImageResource(ImageUtil.getRes(app.getIdentity()));
                        }

                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                drawerLayout.closeDrawers();
                                sendBroadcast(new Intent().setAction("flishall"));
                                insertRealm(app);
                                Intent intent;
                                if(StringUtils.isNotEmpty(app.getUrl())){
                                    intent  = new Intent(mContext, HttpWebActivity.class);
                                    intent.putExtra("title",app.getLabel());
                                    intent.putExtra("url",app.getUrl()+"&token="+preference.getLoginMsg().getUid());
                                }else{
                                    intent = new Intent();
                                    intent.putExtra("title",app.getLabel());
                                    intent.setAction(mContext.getString(R.string.projectcode)+"_" +app.getIdentity());
                                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                                }
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mContext.startActivity(intent);

                            }
                        });
                        itemContent.addView(view);
                    }
                    itemContent.addView(endview);
                }
            }
            @Override
            public void onDrawerClosed(View drawerView) {}
            @Override
            public void onDrawerStateChanged(int newState) {}
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }
    }

    /**
     * 公共手机返回按钮事件.
     * @param keyCode keyCode
     * @param event  KeyEvent
     * @return  boolean
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 显示snakebar 错误信息
     * @param v
     * @param msg
     */
    public void showErrorMsg(View v,String msg){
        SnackBarUtil.showShort(v,msg);
    }

    public void showErrorMsg(String msg){
        SnackBarUtil.showShort(frameLayout,msg);
    }

    private Animator createRevealAnimator(boolean reversed, int x, int y) {
        float hypot = (float) Math.hypot(frameLayout.getHeight(), frameLayout.getWidth());
        float startRadius = reversed ? hypot : 0;
        float endRadius = reversed ? 0 : hypot;

        Animator animator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            animator = ViewAnimationUtils.createCircularReveal(
                    frameLayout, x, y,
                    startRadius,
                    endRadius);
        }
        animator.setDuration(400);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (reversed)
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}
                @Override
                public void onAnimationEnd(Animator animation) {
                    frameLayout.setVisibility(View.INVISIBLE);
                    finish();
                }
                @Override
                public void onAnimationCancel(Animator animation) {}
                @Override
                public void onAnimationRepeat(Animator animation) {}
            });
        return animator;
    }


    public static void getBackgroud(View v ,String str){
        str = PinyinUtil.getInitial(str);
        if("abc".contains(str)){
            v.setBackgroundResource(R.drawable.circle_border_abc);
        }else if("def".contains(str)){
            v.setBackgroundResource(R.drawable.circle_border_def);
        }else if("ghi".contains(str)){
            v.setBackgroundResource(R.drawable.circle_border_ghi);
        }else if("jkl".contains(str)){
            v.setBackgroundResource(R.drawable.circle_border_jkl);
        }else if("mno".contains(str)){
            v.setBackgroundResource(R.drawable.circle_border_mno);
        }else if("pqr".contains(str)){
            v.setBackgroundResource(R.drawable.circle_border_pqr);
        }else if("stu".contains(str)){
            v.setBackgroundResource(R.drawable.circle_border_stu);
        }else{
            v.setBackgroundResource(R.drawable.circle_border_vwxyz);
        }
    }

    public void showBrithdayTheme(){
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, com.dk.mp.core.R.color.colorPrimaryHy));
        }
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.top);
        if (layout == null) {
            RelativeLayout v = (RelativeLayout) frameLayout.getChildAt(0);
            RelativeLayout t = (RelativeLayout) v.getChildAt(0);
            t.setBackgroundColor(ContextCompat.getColor(this, com.dk.mp.core.R.color.colorPrimaryHy));
        } else {
            layout.setBackgroundColor(ContextCompat.getColor(this, com.dk.mp.core.R.color.colorPrimaryHy));
        }

        ImageView view = (ImageView) findViewById(R.id.brithday);
        view.setVisibility(View.VISIBLE);
    }

    public void insertRealm(OaItemEntity bean){
        OaApp oaApp = new OaApp();
        oaApp.setUrl(bean.getUrl());
        oaApp.setTitle(bean.getTitle());
        oaApp.setName(bean.getName());
        oaApp.setLabel(bean.getLabel());
        oaApp.setBussessName(bean.getBussessName());
        oaApp.setCount(bean.getCount());
        oaApp.setDetailUrl(bean.getDetailUrl());
        oaApp.setDiy(bean.getDiy());
        oaApp.setIdentity(bean.getIdentity());
        mRealmHelper.addApp(oaApp);
    }

    /**
     * 最新使用的
     */
    public List<OaItemEntity> getRecentUseApp(){
        List<OaItemEntity> items = new ArrayList<>();
        List<OaApp> apps = mRealmHelper.queryAllOaApp();
        for(OaApp o : apps){
            OaItemEntity oa = new OaItemEntity();
            oa.setBussessName(o.getBussessName());
            oa.setCount(o.getCount());
            oa.setDetailUrl(o.getDetailUrl());
            oa.setDiy(o.getDiy());
            oa.setLabel(o.getLabel());
            oa.setName(o.getName());
            oa.setTitle(o.getTitle());
            oa.setUrl(o.getUrl());
            oa.setIdentity(o.getIdentity());
            items.add(oa);
        }
        return items;
    }
}
