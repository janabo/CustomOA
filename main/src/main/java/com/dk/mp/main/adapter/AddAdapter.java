package com.dk.mp.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dk.mp.main.R;
import com.dk.mp.main.entity.OaItemEntity;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.List;

/**
 * 作者：janabo on 2017/3/20 16:34
 */
public class AddAdapter extends RecyclerView.Adapter<AddAdapter.MyViewHolder>{
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
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OaItemEntity bean = mData.get(position);
        setAppIcon(holder,bean.getLabel());
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
//                    Intent intent;
//                    if(StringUtils.isNotEmpty(bean.getUrl())){
//                        intent  = new Intent(mContext, HttpWebActivity.class);
//                        intent.putExtra("title",bean.getLabel());
//                        intent.putExtra("url",bean.getUrl()+"&token="+helper.getLoginMsg().getUid());
//                    }else{
//                        intent = new Intent();
//                        intent.putExtra("title",bean.getLabel());
//                        intent.setAction(mContext.getString(R.string.projectcode)+"_" +bean.getIdentity());
//                        intent.addCategory(Intent.CATEGORY_DEFAULT);
//                    }
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    mContext.startActivity(intent);
                }
            });
        }
    }

    public void setAppIcon(MyViewHolder holder, String appname){
        switch (appname){
            case "通知公告":
                holder.imageView.setImageResource(R.mipmap.app_tzgg);
                break;
            case "规章制度":
                holder.imageView.setImageResource(R.mipmap.app_gzzd);
                break;
            case "值班安排":
                holder.imageView.setImageResource(R.mipmap.app_zbap);
                break;
            case "领导日程":
                holder.imageView.setImageResource(R.mipmap.app_ldrc);
                break;
            case "会议管理":
                holder.imageView.setImageResource(R.mipmap.app_hygl);
                break;
            case "我的传阅":
                holder.imageView.setImageResource(R.mipmap.app_wdcy);
                break;
            case "公开":
                holder.imageView.setImageResource(R.mipmap.app_gk);
                break;
            case "我的审核":
                holder.imageView.setImageResource(R.mipmap.app_wdsh);
                break;
            case "我的待办":
                holder.imageView.setImageResource(R.mipmap.app_wddb);
                break;
            case "我的申请":
                holder.imageView.setImageResource(R.mipmap.app_wdsq);
                break;
            case "我的草稿":
                holder.imageView.setImageResource(R.mipmap.app_wdcg);
                break;
            default:
                holder.imageView.setImageResource(R.mipmap.app_hygl);
                break;
        }
    }
}
