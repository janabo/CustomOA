package com.dk.mp.main.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.ui.MyActivity;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.core.view.RecycleViewDivider;
import com.dk.mp.main.R;
import com.dk.mp.main.adapter.ManagerAppAdapter;
import com.google.gson.reflect.TypeToken;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqs on 2017/3/9.
 */

public class ManagerActivity extends MyActivity{

    private RecyclerView recyclerView;
    private ManagerAppAdapter adapter;
    private List<OaItemEntity> list = new ArrayList<OaItemEntity>();
    private LinearLayout footrootview;
    private List<OaItemEntity> hideApps = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.main_manageapp;
    }

    @Override
    protected void initialize() {
        super.initialize();
        setTitle("编辑应用");


        footrootview = (LinearLayout) findViewById(R.id.footrootview);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();

        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManagerAppAdapter(this,list,ManagerActivity.this);
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(adapter));
        recyclerView.addItemDecoration(new RecycleViewDivider(ManagerActivity.this, LinearLayoutManager.HORIZONTAL, StringUtils.dip2px(this,10), Color.rgb(244, 244, 244)));

        dragMgr.attachRecyclerView(recyclerView);

        //添加应用
        footrootview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,AddAppActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDatas(){
        hideApps.clear();
        list.clear();
        List<OaItemEntity> t = getGson().fromJson(getSharedPreferences().getValue(getSharedPreferences().getLoginMsg().getUid()+"_customoa"),new TypeToken<List<OaItemEntity>>(){}.getType());
        List<OaItemEntity> mData = new ArrayList<OaItemEntity>();
        for(OaItemEntity x : t){
            if(x.isShow()){
                mData.add(x);
            }else{
                hideApps.add(x);
            }
        }
        list.addAll(mData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void back() {
        super.back();
//        list.addAll(hideApps);
//        getSharedPreferences().setValue(getSharedPreferences().getLoginMsg().getUid()+"_customoa",getGson().toJson(list));
    }

    @Override
    protected void onPause() {
        super.onPause();
        list.addAll(hideApps);
        getSharedPreferences().setValue(getSharedPreferences().getLoginMsg().getUid()+"_customoa",getGson().toJson(list));
        list.clear();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initDatas();
    }

    public void deleteApp(OaItemEntity bean){
        hideApps.add(bean);
        adapter.notifyDataSetChanged();
    }
}
