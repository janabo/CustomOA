package com.dk.mp.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dk.mp.core.ui.HttpWebActivity;
import com.dk.mp.main.R;
import com.dk.mp.main.entity.OaItemEntity;
import com.dk.mp.main.ui.ManagerActivity;

import java.util.List;

import static com.dk.mp.core.application.MyApplication.getContext;

/**
 * 作者：janabo on 2017/1/22 14:05
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context context;
    private LayoutInflater lif;
    private List<OaItemEntity> list;

    /**
     * 构造方法.
     */
    public MainAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    private class FootView extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        public FootView(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.footrootview);
        }
    }
    private class MyView extends RecyclerView.ViewHolder{
        private TextView modeltitle;
        private TextView modelsecondtitle;
        private TextView count;
        private ImageView newimage;
        private TextView title;
        private Button gotolist;

        public MyView(View itemView) {
            super(itemView);
            modeltitle = (TextView) itemView.findViewById(R.id.modeltitle);// 模块标题
            modelsecondtitle = (TextView) itemView.findViewById(R.id.modelsecondtitle);// 模块副标题
            count = (TextView) itemView.findViewById(R.id.number);// 最新信息条数
            newimage = (ImageView) itemView.findViewById(R.id.newimage);// 新、无图标
            title = (TextView) itemView.findViewById(R.id.content);// 消息标题
            gotolist = (Button) itemView.findViewById(R.id.gotolist);// 查看更多
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        lif = LayoutInflater.from(context);// 转化到context这个容器
        if (viewType == 0){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.main_listview_item, parent, false);// 设置要转化的layout文件
            return new MyView(view);
        } else if (viewType == 1) {
            View view = lif.inflate(R.layout.main_footview, parent, false);// 设置要转化的layout文件
            view.setTag("foot");
            view.setOnClickListener(this);
            return new FootView(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (list.size() == 0) {
            ((FootView)holder).linearLayout.setVisibility(View.GONE);
        } else if (position == list.size()){
            ((FootView)holder).linearLayout.setVisibility(View.VISIBLE);
        }

        if (position == list.size()) {
        } else {
            ((MyView)holder).modeltitle.setText(list.get(position).getLabel());
            ((MyView)holder).modelsecondtitle.setText("你需要查看的"+list.get(position).getLabel());
            ((MyView)holder).count.setText(list.get(position).getCount());
            ((MyView)holder).title.setText((list.get(position).getTitle()==null || list.get(position).getTitle().equals(""))?"目前还没有你需要查看的"+list.get(position).getLabel()+"哦！":list.get(position).getTitle());
            ((MyView)holder).title.setTag(list.get(position).getDetailUrl());
            ((MyView)holder).title.setOnClickListener((list.get(position).getTitle()==null || list.get(position).getTitle().equals(""))?null:this);
            ((MyView)holder).newimage.setImageResource(list.get(position).getCount().equals("0") ? R.mipmap.noimage : R.mipmap.newimage);
            ((MyView)holder).gotolist.setTag(list.get(position).getUrl());
            ((MyView)holder).gotolist.setOnClickListener(this);
        }
//        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == list.size()?1:0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == gridManager.getItemCount() - 1 ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getTag().toString().equals("foot")) {//编辑应用
            intent = new Intent(getContext(), ManagerActivity.class);
        } else {
            intent = new Intent(getContext(), HttpWebActivity.class);
            intent.putExtra("title","测试");
            intent.putExtra("url",view.getTag().toString());
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }
}
