package com.dk.mp.main.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dk.mp.core.dialog.AlertDialog;
import com.dk.mp.core.entity.User;
import com.dk.mp.core.login.LoginActivity;
import com.dk.mp.core.ui.BaseActivity;
import com.dk.mp.core.util.CoreSharedPreferencesHelper;
import com.dk.mp.core.util.SnackBarUtil;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.core.view.locus.LocusPassWordView;
import com.dk.mp.main.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 手势密码
 * 作者：janabo on 2017/3/25 10:13
 */
public class LockActivity extends BaseActivity implements View.OnClickListener,LocusPassWordView.OnCompleteListener {
    SimpleDraweeView loginMess;//用户头像
    FrameLayout id_user_avatarView;
    private TextView mUserName;
    private TextView mLockHint;
    private LocusPassWordView mLockView;
    private TextView mForgetText;

    private String from;
    private String action;
    private String pwd;
    private int count = 10;
    private LinearLayout lock_message;
    public CoreSharedPreferencesHelper preference;


    @Override
    protected int getLayoutID() {
        return R.layout.lock_activity;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();
//        preference = new CoreSharedPreferencesHelper(this);
//        initView();
//        initialize();
//    }

    @Override
    protected void initView() {
        super.initView();
        preference = new CoreSharedPreferencesHelper(this);
        if(preference.getInt("lock_num")>-1){
            count=preference.getInt("lock_num");
        }
        id_user_avatarView= (FrameLayout) findViewById(R.id.id_user_avatarView);
        loginMess = (SimpleDraweeView) findViewById(R.id.loginMess);
        mUserName = (TextView) findViewById(R.id.id_user_name);
        mLockHint = (TextView) findViewById(R.id.id_lock_hint);
        mLockView = (LocusPassWordView) findViewById(R.id.id_lock_view);
        mForgetText = (TextView) findViewById(R.id.id_forget_pwd);
        lock_message = (LinearLayout) findViewById(R.id.lock_message);
        mForgetText.setOnClickListener(this);
        mLockView.setOnCompleteListener(this);
        initialize();
    }


    protected void initialize() {
        from = getIntent().getStringExtra("from");
        action = getIntent().getStringExtra("action");
        if ("set".equals(action)) {
            mLockHint.setText("请绘制解锁图像");
            mForgetText.setVisibility(View.GONE);
            LocusPassWordView.clearPassword(LockActivity.this);
            id_user_avatarView.setVisibility(View.INVISIBLE);
            mUserName.setVisibility(View.INVISIBLE);
        } else if ("vertify".equals(action)) {
            mLockHint.setVisibility(View.GONE);
            mForgetText.setVisibility(View.VISIBLE);
        }else{
            mForgetText.setVisibility(View.VISIBLE);
        }
        showUser();
    }

    private void showUser() {
        User user = preference.getUser();
        if (user != null) {
            if(StringUtils.isNotEmpty(user.getPhoto())){
//                ImageUtil.setImageView(LockActivity.this, mUserAvatar, user.getPhoto(), R.drawable.touming, R.drawable.touming);
            }
            if (!TextUtils.isEmpty(user.getUserName())) {
                mUserName.setText(user.getUserName());
        }
        }
    }

    @Override
    public void onClick(View view) {
        new AlertDialog(mContext).show("提示", "忘记手势密码，需重新登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(LockActivity.this, LoginActivity.class);
                intent.putExtra("from", "lock");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onComplete(String password) {
        if ("set".equals(action)) {
            mLockHint.setText("请再次绘制解锁图像");
            mLockView.clearPassword();
            action = "repeat";
            pwd = password;
        } else if ("repeat".equals(action)) {
            if (pwd.equals(password)) {
                preference.setInt("lock_num", -1);
                // 设置成功，保存，跳转
                showErrorMsg(lock_message,"绘制成功");
                LocusPassWordView.setPassword(LockActivity.this, password);
                navigateToMain();
            } else {
                // 两次设置不一致
                mLockHint.setText("绘制解锁图像与第一次不相同，请重新绘制");
                mLockView.clearPassword();
                action = "set";
            }
        } else if ("vertify".equals(action)) {
            if (mLockView.getPassword().equals(password)) {
                // 密码验证正确，跳转
                preference.setInt("lock_num", -1);
                navigateToMain();
            } else {
                // 验证不正确，继续
                mLockHint.setVisibility(View.VISIBLE);
                mForgetText.setVisibility(View.VISIBLE);
                mLockHint.setTextColor(Color.RED);
                int countt=--count;
                mLockHint.setText("密码错误你还可以输入" + countt + "次");
                preference.setInt("lock_num", countt);
                if (count > 0) {
                    mLockView.clearPassword();
                } else {
                    preference.setInt("lock_num", countt);
                    preference.cleanUser();
                    LocusPassWordView.clearPassword(LockActivity.this);
                    Intent intent = new Intent(LockActivity.this, LoginActivity.class);
                    intent.putExtra("from", "lock");
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    private void navigateToMain() {
        Intent intent = new Intent(LockActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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

    /**
     * 显示snakebar 错误信息
     * @param v
     * @param msg
     */
    public void showErrorMsg(View v,String msg){
        SnackBarUtil.showShort(v,msg);
    }
}
