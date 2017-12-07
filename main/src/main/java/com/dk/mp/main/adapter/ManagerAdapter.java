package com.dk.mp.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dk.mp.core.entity.OaApp;
import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.ui.HttpWebActivity;
import com.dk.mp.core.util.CoreSharedPreferencesHelper;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.core.util.security.Signature;
import com.dk.mp.main.R;
import com.dk.mp.main.db.RealmHelper;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.List;

/**
 * 作者：janabo on 2017/3/16 14:11
 */
public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.MyViewHolder>{
    private List<OaItemEntity> mData;
    private Context mContext;
    LayoutInflater inflater;
    private CoreSharedPreferencesHelper helper;
    RealmHelper mRealmHelper;

    public ManagerAdapter(Context mContext,List<OaItemEntity> mData,CoreSharedPreferencesHelper helper,RealmHelper mRealmHelper){
        this.mContext = mContext;
        this.mData = mData;
        this.helper = helper;
        this.mRealmHelper = mRealmHelper;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.main_manager_item,null,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OaItemEntity bean = mData.get(position);
        if(StringUtils.isNotEmpty(bean.getUrl())) {
            setAppIcon(holder, bean.getName());//自定义oa
        }else{
            setAppIcon(holder, bean.getIdentity());//非自定义
        }
        holder.mTextView.setText(bean.getLabel());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends AbstractDraggableItemViewHolder {
        public FrameLayout mContainer;
        public ImageView imageView;
        public TextView mTextView;

        public MyViewHolder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            imageView = (ImageView) v.findViewById(R.id.item_image);
            mTextView = (TextView) v.findViewById(R.id.item_text);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OaItemEntity bean = mData.get(getLayoutPosition());
                    insertRealm(bean);
                    Intent intent;
                    if(StringUtils.isNotEmpty(bean.getUrl())){
                        intent  = new Intent(mContext, HttpWebActivity.class);
                        intent.putExtra("title",bean.getLabel());
                        intent.putExtra("close_web",-1);
                        String uid ="";
                        if(helper.getLoginMsg() != null){
                            uid = helper.getLoginMsg().getUid();
                            uid = Signature.encrypt(uid+"|"+Signature.encrypt("dake_oa_app_key")+"|"+System.currentTimeMillis());
                        }
                        intent.putExtra("url",bean.getUrl()+"&token="+uid);
                    }else{
                        intent = new Intent();
                        intent.putExtra("title",bean.getLabel());
                        intent.setAction(mContext.getString(R.string.projectcode)+"_" +bean.getIdentity());
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
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

    public void setAppIcon(MyViewHolder holder,String appname){
        switch (appname){
            case "tzgg"://通知公告
                holder.imageView.setImageResource(R.mipmap.app_tzgg);
                break;
            case "gzzd"://规章制度
                holder.imageView.setImageResource(R.mipmap.app_gzzd);
                break;
            case "zbap"://值班安排
                holder.imageView.setImageResource(R.mipmap.app_zbap);
                break;
            case "grrc"://领导日程
                holder.imageView.setImageResource(R.mipmap.app_ldrc);
                break;
            case "hygl"://会议管理
                holder.imageView.setImageResource(R.mipmap.app_hygl);
                break;
            case "cy"://我的传阅
                holder.imageView.setImageResource(R.mipmap.app_wdcy);
                break;
            case "gk"://公开
                holder.imageView.setImageResource(R.mipmap.app_gk);
                break;
            case "wdsh"://我的审核
                holder.imageView.setImageResource(R.mipmap.app_wdsh);
                break;
            case "wddb"://我的待办
                holder.imageView.setImageResource(R.mipmap.app_wddb);
                break;
            case "dwsq"://我的申请
                holder.imageView.setImageResource(R.mipmap.app_wdsq);
                break;
            case "wdcg"://我的草稿
                holder.imageView.setImageResource(R.mipmap.app_wdcg);
                break;
            case "ywhy"://邀我参加的会议
                holder.imageView.setImageResource(R.mipmap.app_ywhy);
                break;
            default:
                holder.imageView.setImageResource(R.mipmap.app_hygl);
                break;
        }
    }
}
