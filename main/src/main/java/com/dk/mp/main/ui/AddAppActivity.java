package com.dk.mp.main.ui;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dk.mp.core.ui.MyActivity;
import com.dk.mp.core.view.RecycleViewDivider;
import com.dk.mp.core.widget.ErrorLayout;
import com.dk.mp.main.R;
import com.dk.mp.main.adapter.AddAdapter;
import com.dk.mp.main.entity.OaItemEntity;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加应用
 * 作者：janabo on 2017/3/20 16:13
 */
public class AddAppActivity extends MyActivity implements View.OnClickListener {
    RelativeLayout title_bar;
    LinearLayout recentUse_layout,oprition_layout,other_layout;//最近使用，业务管理，其它
    RecyclerView oprition_recycler_view;
    AddAdapter bAdapter;//业务管理
    ErrorLayout main_error;
    private List<OaItemEntity> list = new ArrayList<OaItemEntity>();

    @Override
    protected int getLayoutID() {
        return R.layout.main_manager;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("添加应用");
        setRightText("确定", Color.rgb(148,196,248), this);
        initDatas();
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        title_bar.setVisibility(View.VISIBLE);
        main_error = (ErrorLayout) findViewById(R.id.main_error);
        main_error.setVisibility(View.GONE);
        recentUse_layout = (LinearLayout) findViewById(R.id.recentUse_layout);
        oprition_layout = (LinearLayout) findViewById(R.id.oprition_layout);
        other_layout = (LinearLayout) findViewById(R.id.other_layout);
        recentUse_layout.setVisibility(View.GONE);
        other_layout.setVisibility(View.GONE);
        oprition_recycler_view = (RecyclerView) findViewById(R.id.oprition_recycler_view);

        RecycleViewDivider vDivider = new RecycleViewDivider(mContext, GridLayoutManager.VERTICAL, 1, Color.rgb(201, 201, 201));
        RecycleViewDivider hDivider = new RecycleViewDivider(mContext, GridLayoutManager.HORIZONTAL, 1, Color.rgb(201, 201, 201));
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        bAdapter = new AddAdapter(mContext,list);
        oprition_recycler_view.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
        oprition_recycler_view.setAdapter(bAdapter);
        oprition_recycler_view.setItemAnimator(animator);
        oprition_recycler_view.addItemDecoration(vDivider);//添加分割线
        oprition_recycler_view.addItemDecoration(hDivider);//添加分割线
    }

    private void initDatas(){
        List<OaItemEntity> t = getGson().fromJson(getSharedPreferences().getValue(getSharedPreferences().getLoginMsg().getUid()+"_customoa"),new TypeToken<List<OaItemEntity>>(){}.getType());
        List<OaItemEntity> mData = new ArrayList<OaItemEntity>();
        for(OaItemEntity x : t){
            if(!x.isShow() && "true".equals(x.getDiy())){
                mData.add(x);
            }
        }
        list.addAll(mData);
    }

    @Override
    public void onClick(View view) {

    }
}
