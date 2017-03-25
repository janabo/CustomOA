package com.dk.mp.main.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;
import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.http.HttpUtil;
import com.dk.mp.core.http.request.HttpListener;
import com.dk.mp.core.ui.BaseFragment;
import com.dk.mp.core.util.DeviceUtil;
import com.dk.mp.core.widget.ErrorLayout;
import com.dk.mp.main.R;
import com.dk.mp.main.adapter.MainAdapter;
import com.dk.mp.main.db.RealmHelper;
import com.dk.mp.main.utils.CoustomManagerUtils;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqs on 2017/3/6.
 */

public class MainFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView myListView;
    private MainAdapter adapter;
    private List<OaItemEntity> list = new ArrayList<OaItemEntity>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ErrorLayout mError;
    private RealmHelper mRealmHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.main_listview;
    }

    @Override
    protected void initialize(final View view) {
        mRealmHelper = new RealmHelper(getContext(),getSharedPreferences());
        mError = (ErrorLayout) view.findViewById(R.id.main_error);
        swipeRefreshLayout = (SwipeRefreshLayout)view;
        swipeRefreshLayout.setOnRefreshListener(this);
        myListView = (RecyclerView) view.findViewById(R.id.recyclerView);
        myListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new MainAdapter(getContext(),list,getSharedPreferences(),mRealmHelper);
        myListView.setAdapter(adapter);
    }

    private void initDatas(){
        if(!DeviceUtil.checkNet()){//判断是否有网络
            myListView.setVisibility(View.GONE);
            mError.setErrorType(ErrorLayout.NETWORK_ERROR);
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        HttpUtil.getInstance().postJsonObjectRequest("apps/oa/listModule", null, new HttpListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                if (result.optInt("code") == 200){//成功返回数据
                    try {
                        List<OaItemEntity> oaItemEntityList = getGson().fromJson(result.getJSONArray("data").toString(),new TypeToken<List<OaItemEntity>>(){}.getType());
                        if (oaItemEntityList == null){
                            myListView.setVisibility(View.GONE);
                            mError.setErrorType(ErrorLayout.DATAFAIL);
                        } else if (oaItemEntityList.size() == 0) {
                            myListView.setVisibility(View.GONE);
                            mError.setErrorType(ErrorLayout.SEARCHNODATA);
                        } else {
                            myListView.setVisibility(View.VISIBLE);
                            list.clear();
                            List<OaItemEntity> alllist = CoustomManagerUtils.getEntitlyList(getSharedPreferences(),getGson(),oaItemEntityList);
                            List<OaItemEntity> mData = new ArrayList<OaItemEntity>();
                            for(OaItemEntity x : alllist){
                                if(x.isShow()){
                                    mData.add(x);
                                }
                            }
                            list.addAll(mData);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        myListView.setVisibility(View.GONE);
                        mError.setErrorType(ErrorLayout.DATAFAIL);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(VolleyError error) {
                myListView.setVisibility(View.GONE);
                mError.setErrorType(ErrorLayout.DATAFAIL);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        initDatas();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (DeviceUtil.checkNet()){
            myListView.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        }
        initDatas();
    }
}
