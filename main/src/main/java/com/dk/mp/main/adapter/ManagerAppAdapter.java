package com.dk.mp.main.adapter;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dk.mp.main.R;
import com.dk.mp.main.entity.OaItemEntity;
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

    public class MyViewHolder extends AbstractDraggableItemViewHolder {
        public LinearLayout mContainer;
        public TextView titlelable;
        private ImageView deleteapp;

        public MyViewHolder(View v) {
            super(v);
            mContainer = (LinearLayout) v.findViewById(R.id.rootview);
            titlelable = (TextView) v.findViewById(R.id.titlelable);
            deleteapp = (ImageView) v.findViewById(R.id.delete_app);
        }
    }

    public ManagerAppAdapter(Context context, List<OaItemEntity> list) {
        this.list = list;
        mContext = context;
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
}
