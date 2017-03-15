package com.dk.mp.main.ui;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dk.mp.core.ui.MyActivity;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.core.view.RecycleViewDivider;
import com.dk.mp.main.R;
import com.dk.mp.main.adapter.ManagerAppAdapter;
import com.dk.mp.main.entity.OaItemEntity;
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

    @Override
    protected int getLayoutID() {
        return R.layout.main_manageapp;
    }

    @Override
    protected void initialize() {
        super.initialize();
        setTitle("编辑应用");

        initDatas();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        // Setup D&D feature and RecyclerView
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();

        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManagerAppAdapter(this,list);
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(adapter));
        recyclerView.addItemDecoration(new RecycleViewDivider(ManagerActivity.this, LinearLayoutManager.HORIZONTAL, StringUtils.dip2px(this,10), Color.rgb(244, 244, 244)));


        dragMgr.attachRecyclerView(recyclerView);
    }

    private void initDatas(){
        List<OaItemEntity> t = getGson().fromJson(getSharedPreferences().getValue(getSharedPreferences().getLoginMsg().getUid()+"_customoa"),new TypeToken<List<OaItemEntity>>(){}.getType());
        list.addAll(t);
    }

    @Override
    public void back() {
        super.back();
        getSharedPreferences().setValue(getSharedPreferences().getLoginMsg().getUid()+"_customoa",getGson().toJson(list));
    }
}
