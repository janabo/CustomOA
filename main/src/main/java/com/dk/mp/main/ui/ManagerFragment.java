package com.dk.mp.main.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.dk.mp.core.entity.OaApp;
import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.http.HttpUtil;
import com.dk.mp.core.http.request.HttpListener;
import com.dk.mp.core.ui.BaseFragment;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.core.view.RecycleViewDivider;
import com.dk.mp.core.widget.ErrorLayout;
import com.dk.mp.main.R;
import com.dk.mp.main.adapter.ManagerAdapter;
import com.dk.mp.main.db.RealmHelper;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理
 * 作者：janabo on 2017/3/16 11:53
 */
public class ManagerFragment extends BaseFragment {
    private Context mContext;
    LinearLayout recentUse_layout,oprition_layout,other_layout;//最近使用，业务管理，其它
    RecyclerView recycler_view,oprition_recycler_view,other_recycler_view;
    ManagerAdapter uAdapter;//最近使用
    ManagerAdapter bAdapter;//业务管理
    ManagerAdapter oAdapter;//其它
    List<OaItemEntity> uData = new ArrayList<>();
    List<OaItemEntity> bData = new ArrayList<>();
    List<OaItemEntity> oData = new ArrayList<>();
    private ErrorLayout mError;
    private RealmHelper mRealmHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.main_manager;
    }

    @Override
    protected void initialize(View view) {
        mContext = getContext();
        mRealmHelper = new RealmHelper(getContext(),getSharedPreferences());
        mError = (ErrorLayout) view.findViewById(R.id.main_error);
        recentUse_layout = (LinearLayout) view.findViewById(R.id.recentUse_layout);
        oprition_layout = (LinearLayout) view.findViewById(R.id.oprition_layout);
        other_layout = (LinearLayout) view.findViewById(R.id.other_layout);
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        oprition_recycler_view = (RecyclerView) view.findViewById(R.id.oprition_recycler_view);
        other_recycler_view = (RecyclerView) view.findViewById(R.id.other_recycler_view);
        RecycleViewDivider vDivider = new RecycleViewDivider(getActivity(), GridLayoutManager.VERTICAL, 1, Color.rgb(201, 201, 201));
        RecycleViewDivider hDivider = new RecycleViewDivider(getActivity(), GridLayoutManager.HORIZONTAL, 1, Color.rgb(201, 201, 201));
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        uAdapter = new ManagerAdapter(mContext,uData,getSharedPreferences(),mRealmHelper);
        recycler_view.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(uAdapter);
        recycler_view.setItemAnimator(animator);
        recycler_view.addItemDecoration(vDivider);//添加分割线
        recycler_view.addItemDecoration(hDivider);//添加分割线

        bAdapter = new ManagerAdapter(mContext,bData,getSharedPreferences(),mRealmHelper);
        oprition_recycler_view.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
        oprition_recycler_view.setAdapter(bAdapter);
        oprition_recycler_view.setItemAnimator(animator);
        oprition_recycler_view.addItemDecoration(vDivider);//添加分割线
        oprition_recycler_view.addItemDecoration(hDivider);//添加分割线

        oAdapter = new ManagerAdapter(mContext,oData,getSharedPreferences(),mRealmHelper);
        other_recycler_view.setLayoutManager(new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false));
        other_recycler_view.setAdapter(oAdapter);
        other_recycler_view.setItemAnimator(animator);
        other_recycler_view.addItemDecoration(vDivider);//添加分割线
        other_recycler_view.addItemDecoration(hDivider);//添加分割线
        getData();
    }

    public void getData(){
        HttpUtil.getInstance().postJsonObjectRequest("apps/oa/listModule", null, new HttpListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (result.optInt("code") == 200){//成功返回数据
                    try {
                        List<OaItemEntity> oaItemEntityList = getGson().fromJson(result.getJSONArray("data").toString(),new TypeToken<List<OaItemEntity>>(){}.getType());
                        if (oaItemEntityList == null){
                            mError.setErrorType(ErrorLayout.DATAFAIL);
                        } else if (oaItemEntityList.size() == 0) {
                            mError.setErrorType(ErrorLayout.NODATA);
                        } else {
                            mError.setErrorType(ErrorLayout.HIDE_LAYOUT);
                            bData.clear();
                            oData.clear();
                            for(OaItemEntity o : oaItemEntityList){
                                if(StringUtils.isNotEmpty(o.getDetailUrl()))
                                    bData.add(o);
                                else
                                    oData.add(o);
                            }
                            if(bData.size()>0)
                                oprition_layout.setVisibility(View.VISIBLE);
                            else
                                oprition_layout.setVisibility(View.GONE);
                            if(oData.size()>0)
                                other_layout.setVisibility(View.VISIBLE);
                            else
                                other_layout.setVisibility(View.GONE);

                            bAdapter.notifyDataSetChanged();
                            oAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        mError.setErrorType(ErrorLayout.DATAFAIL);
                    }
                }
            }

            @Override
            public void onError(VolleyError error) {
                mError.setErrorType(ErrorLayout.DATAFAIL);
            }
        });
    }

    /**
     * 最新使用的
     */
    public void getRecentUseApp(){
        uData.clear();
        List<OaApp> apps = mRealmHelper.queryAllOaApp();
        int i = 0;
        for(OaApp o : apps){
            i++;
            if(i>4)
                break;
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
            uData.add(oa);
        }
        if(uData.size()<=0){
            recentUse_layout.setVisibility(View.GONE);
        }else{
            recentUse_layout.setVisibility(View.VISIBLE);
        }
        uAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        getRecentUseApp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mRealmHelper.onClose();
    }
}
