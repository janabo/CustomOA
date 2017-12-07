package com.dk.mp.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dk.mp.core.entity.OaApp;
import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.ui.HttpWebActivity;
import com.dk.mp.core.util.CoreSharedPreferencesHelper;
import com.dk.mp.core.util.StringUtils;
import com.dk.mp.core.util.security.Signature;
import com.dk.mp.main.R;
import com.dk.mp.main.db.RealmHelper;
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
    private CoreSharedPreferencesHelper helper;
    RealmHelper mRealmHelper;

    /**
     * 构造方法.
     */
    public MainAdapter(Context context, List list,CoreSharedPreferencesHelper helper,RealmHelper mRealmHelper) {
        this.context = context;
        this.list = list;
        this.helper = helper;
        this.mRealmHelper = mRealmHelper;
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
        private LinearLayout background_lin;
        private TextView more;

        public MyView(View itemView) {
            super(itemView);
            background_lin = (LinearLayout) itemView.findViewById(R.id.background_lin);
            modeltitle = (TextView) itemView.findViewById(R.id.modeltitle);// 模块标题
            modelsecondtitle = (TextView) itemView.findViewById(R.id.modelsecondtitle);// 模块副标题
            count = (TextView) itemView.findViewById(R.id.number);// 最新信息条数
            newimage = (ImageView) itemView.findViewById(R.id.newimage);// 新、无图标
            title = (TextView) itemView.findViewById(R.id.content);// 消息标题
            gotolist = (Button) itemView.findViewById(R.id.gotolist);// 查看更多
            more = (TextView) itemView.findViewById(R.id.more);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OaItemEntity bean = list.get(getLayoutPosition());
                    if(StringUtils.isNotEmpty(bean.getCount()) && Integer.parseInt(bean.getCount())>0) {
                        insertRealm(bean);
                        Intent intent = new Intent(getContext(), HttpWebActivity.class);
                        intent.putExtra("close_web",-1);
                        intent.putExtra("title", "详情");

                        String uid ="";
                        if(helper.getLoginMsg() != null){
                            uid = helper.getLoginMsg().getUid();
                            uid = Signature.encrypt(uid+"|"+Signature.encrypt("dake_oa_app_key")+"|"+System.currentTimeMillis());
                        }

                        intent.putExtra("url", bean.getDetailUrl() + "&token=" + uid);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });

            gotolist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OaItemEntity bean = list.get(getLayoutPosition());
                    insertRealm(bean);
                    Intent intent  = new Intent(getContext(), HttpWebActivity.class);
                    intent.putExtra("close_web",-1);
                    intent.putExtra("title",bean.getLabel());
                    String uid ="";
                    if(helper.getLoginMsg() != null){
                        uid = helper.getLoginMsg().getUid();
                        uid = Signature.encrypt(uid+"|"+Signature.encrypt("dake_oa_app_key")+"|"+System.currentTimeMillis());
                    }
                    intent.putExtra("url",bean.getUrl()+"&token="+uid);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

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
//            ((FootView)holder).linearLayout.setVisibility(View.GONE);
        } else if (position == list.size()){
            ((FootView)holder).linearLayout.setVisibility(View.VISIBLE);
        }
        if (position == list.size()) {
        } else {
            OaItemEntity bean= list.get(position);
            if(StringUtils.isNotEmpty(bean.getUrl())) {
                setBackground((MyView)holder,bean.getName());//自定义oa
            }else{
                setBackground((MyView)holder,bean.getIdentity());//非自定义
            }

            ((MyView)holder).modeltitle.setText(conventEmpToString(bean.getLabel()));
            ((MyView)holder).modelsecondtitle.setText("你需要查看的"+conventEmpToString(bean.getLabel()));

            ((MyView)holder).title.setText(!StringUtils.isNotEmpty(bean.getTitle())?"目前还没有你需要查看的"+bean.getLabel()+"哦！":bean.getTitle());
            ((MyView)holder).newimage.setImageResource((StringUtils.isNotEmpty(bean.getCount()) && !bean.getCount().equals("0")) ? R.mipmap.newimage : R.mipmap.noimage);
            if(StringUtils.isNotEmpty(bean.getCount()) && bean.getCount().length()>2){
                ((MyView)holder).count.setText("99");
                ((MyView)holder).more.setVisibility(View.VISIBLE);
            }else if(StringUtils.isNotEmpty(bean.getCount()) && bean.getCount().length()<=2){
                ((MyView)holder).count.setText(bean.getCount());
                ((MyView)holder).more.setVisibility(View.GONE);
            }else{
                ((MyView)holder).count.setText("0");
                ((MyView)holder).more.setVisibility(View.GONE);
            }

        }
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
        Intent intent= new Intent(getContext(), ManagerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 反转null 为“”
     * @return
     */
    public String conventEmpToString(String par){
        if(StringUtils.isNotEmpty(par)){
            return par;
        }
        return "";
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


    public void setBackground(MainAdapter.MyView holder, String appname){
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
