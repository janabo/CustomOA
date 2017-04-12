package com.dk.mp.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.main.R;
import com.dk.mp.main.ui.ManagerActivity;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.List;

/**
 * Created by dongqs on 16/7/23.
 */
public class ManagerAppAdapter extends RecyclerView.Adapter<ManagerAppAdapter.MyViewHolder> implements DraggableItemAdapter<ManagerAppAdapter.MyViewHolder>{

    private Context mContext;
    private List<OaItemEntity> list;
    private Vibrator mVibrator;
    private ManagerActivity activity;

    public class MyViewHolder extends AbstractDraggableItemViewHolder {
        public LinearLayout mContainer;
        public TextView titlelable;
        private ImageView deleteapp;
        private LinearLayout background_lin;

        public MyViewHolder(View v) {
            super(v);
            background_lin = (LinearLayout) v.findViewById(R.id.background_lin);
            mContainer = (LinearLayout) v.findViewById(R.id.rootview);
            titlelable = (TextView) v.findViewById(R.id.titlelable);
            deleteapp = (ImageView) v.findViewById(R.id.delete_app);
            deleteapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OaItemEntity o = list.get(getLayoutPosition());
                    o.setShow(false);
                    list.remove(o);
                    activity.deleteApp(o);
                }
            });
        }
    }

    public ManagerAppAdapter(Context context, List<OaItemEntity> list,ManagerActivity activity) {
        this.list = list;
        mContext = context;
        this.activity = activity;
        mVibrator = (Vibrator)mContext.getSystemService(Context.VIBRATOR_SERVICE);
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_manageapp_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final OaItemEntity item = list.get(position);
        holder.titlelable.setText(item.getLabel());
        if(StringUtils.isNotEmpty(item.getUrl())) {
            setBackground(holder,item.getName());//自定义oa
        }else{
            setBackground(holder,item.getIdentity());//非自定义
        }

        int dragState = holder.getDragStateFlags();
        if (((dragState & DraggableItemConstants.STATE_FLAG_IS_UPDATED) != 0)) {
            if ((dragState & DraggableItemConstants.STATE_FLAG_IS_ACTIVE) != 0) {
                mVibrator.vibrate(50); //震动一下
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        OaItemEntity movedItem = list.remove(fromPosition);
        list.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    public void setBackground(MyViewHolder holder, String appname){
        switch (appname){
            case "tzgg"://通知公告
                holder.background_lin.setBackgroundColor(Color.rgb(239,125,90));
                break;
            case "gzzd"://规章制度
                holder.background_lin.setBackgroundColor(Color.rgb(103,115,183));
                break;
            case "zbap"://值班安排
                holder.background_lin.setBackgroundColor(Color.rgb(85,165,28));
                break;
            case "grrc"://领导日程
                holder.background_lin.setBackgroundColor(Color.rgb(45,147,200));
                break;
            case "hygl"://会议管理
                holder.background_lin.setBackgroundColor(Color.rgb(0,131,194));
                break;
            case "cy"://我的传阅
                holder.background_lin.setBackgroundColor(Color.rgb(0,168,136));
                break;
            case "gk"://公开
                holder.background_lin.setBackgroundColor(Color.rgb(50,177,108));
                break;
            case "wdsh"://我的审核
                holder.background_lin.setBackgroundColor(Color.rgb(236,105,65));
                break;
            case "wddb"://我的待办
                holder.background_lin.setBackgroundColor(Color.rgb(0,155,223));
                break;
            case "dwsq"://我的申请
                holder.background_lin.setBackgroundColor(Color.rgb(0,175,171));
                break;
            case "wdcg"://我的草稿
                holder.background_lin.setBackgroundColor(Color.rgb(242,139,0));
                break;
            case "ywhy"://邀我参加的会议
                holder.background_lin.setBackgroundColor(Color.rgb(52,144,65));
                break;
            default:
                holder.background_lin.setBackgroundColor(Color.rgb(0,155,223));
                break;
        }
    }
}
