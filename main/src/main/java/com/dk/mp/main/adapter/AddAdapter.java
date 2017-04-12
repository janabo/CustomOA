package com.dk.mp.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.main.R;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.List;

/**
 * 作者：janabo on 2017/3/20 16:34
 */
public class AddAdapter extends RecyclerView.Adapter<AddAdapter.MyViewHolder> implements View.OnClickListener{
    private List<OaItemEntity> mData;
    private Context mContext;
    LayoutInflater inflater;

    public AddAdapter(Context mContext,List<OaItemEntity> mData){
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public AddAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.main_manager_item,null,false);
        v.setOnClickListener(this);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OaItemEntity bean = mData.get(position);
//        setAppIcon(holder,bean.getLabel());
        if(StringUtils.isNotEmpty(bean.getUrl())) {
            setAppIcon(holder, bean.getName());//自定义oa
        }else{
            setAppIcon(holder, bean.getIdentity());//非自定义
        }
        holder.mTextView.setText(bean.getLabel());
        holder.mTextView.setTag(bean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //onitemclicklistener事件
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, OaItemEntity data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(OaItemEntity)(v.findViewById(R.id.item_text).getTag()));
        }
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
        }
    }

    public void setAppIcon(MyViewHolder holder, String appname){
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

    public List<OaItemEntity> getList() {
        return mData;
    }
}
