package com.dk.mp.main.ui;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.ui.MyActivity;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.core.view.RecycleViewDivider;
import com.dk.mp.core.widget.ErrorLayout;
import com.dk.mp.main.R;
import com.dk.mp.main.adapter.AddAdapter;
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
    private List<OaItemEntity>  showApp = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.main_manager;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("添加应用");
        setRightText("确定", Color.rgb(193,193,193), this);
        initDatas();
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        title_bar.setVisibility(View.VISIBLE);
        main_error = (ErrorLayout) findViewById(R.id.main_error);
        recentUse_layout = (LinearLayout) findViewById(R.id.recentUse_layout);
        oprition_layout = (LinearLayout) findViewById(R.id.oprition_layout);
        other_layout = (LinearLayout) findViewById(R.id.other_layout);
        recentUse_layout.setVisibility(View.GONE);
        other_layout.setVisibility(View.GONE);
        oprition_recycler_view = (RecyclerView) findViewById(R.id.oprition_recycler_view);
        if(list.size()>0){
            main_error.setErrorType(ErrorLayout.HIDE_LAYOUT);
        }else{
            main_error.setErrorType(ErrorLayout.NODATA);
        }
        RecycleViewDivider vDivider = new RecycleViewDivider(mContext, GridLayoutManager.VERTICAL, 1, Color.rgb(201, 201, 201));
        RecycleViewDivider hDivider = new RecycleViewDivider(mContext, GridLayoutManager.HORIZONTAL, 1, Color.rgb(201, 201, 201));
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        bAdapter = new AddAdapter(mContext,list);
        oprition_recycler_view.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
        oprition_recycler_view.setAdapter(bAdapter);
        oprition_recycler_view.setItemAnimator(animator);
        oprition_recycler_view.addItemDecoration(vDivider);//添加分割线
        oprition_recycler_view.addItemDecoration(hDivider);//添加分割线
        bAdapter.setOnItemClickListener(new AddAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, OaItemEntity data) {
                ImageView add = (ImageView) view.findViewById(R.id.gragItemViewAdd);
                add.setVisibility(add.getVisibility() == View.GONE? View.VISIBLE: View.GONE);
                data.setSelected(!data.isSelected());
                boolean b = false;
                for(OaItemEntity app:bAdapter.getList()){
                    if(app.isSelected()){
                        b = true;
                        break;
                    }
                }
                if(b){
                    setRightTextColor(getResources().getColor(R.color.black));
                }else{
                    setRightTextColor(Color.rgb(193,193,193));
                }
            }
        });
    }

    private void initDatas(){
        List<OaItemEntity> t = getGson().fromJson(getSharedPreferences().getValue(getSharedPreferences().getLoginMsg().getUid()+"_customoa"),new TypeToken<List<OaItemEntity>>(){}.getType());
        List<OaItemEntity> mData = new ArrayList<OaItemEntity>();
        for(OaItemEntity x : t){
            if(!x.isShow() && StringUtils.isNotEmpty(x.getDetailUrl())){
                mData.add(x);
            }else{
                showApp.add(x);
            }
        }
        list.addAll(mData);
    }

    @Override
    public void onClick(View view) {
        boolean b = false;
        for(OaItemEntity app:bAdapter.getList()){
            if(app.isSelected()){
                b = true;
                break;
            }
        }
        if(!b) return;
        for(OaItemEntity app:bAdapter.getList()){
            if(app.isSelected()){
                app.setShow(true);
            }
            showApp.add(app);
        }
        getSharedPreferences().setValue(getSharedPreferences().getLoginMsg().getUid()+"_customoa",getGson().toJson(showApp));
        finish();
    }
}
