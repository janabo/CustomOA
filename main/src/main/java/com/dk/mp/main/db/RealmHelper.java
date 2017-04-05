package com.dk.mp.main.db;

import android.content.Context;

import com.dk.mp.core.entity.OaApp;
import com.dk.mp.core.util.CoreSharedPreferencesHelper;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * 作者：janabo on 2017/3/16 15:10
 */
public class RealmHelper {
    CoreSharedPreferencesHelper preferencesHelper;
    private Realm mRealm;
    public RealmHelper(Context context,CoreSharedPreferencesHelper preferencesHelper) {
        mRealm = Realm.getDefaultInstance();
        this.preferencesHelper = preferencesHelper;
    }

    /**
     * 新增最新使用
     * @param app
     */
    public void addApp(final OaApp app){
        String uid = preferencesHelper.getLoginMsg().getUid();
        app.setId(new Date().getTime());
        app.setUserid(uid);
        mRealm.beginTransaction();
        queryAllOaApp(uid,app.getIdentity());
        mRealm.copyToRealmOrUpdate(app);
        mRealm.commitTransaction();
    }

    public void queryAllOaApp(String uid,String identity){
        RealmResults<OaApp> jbxxs = mRealm.where(OaApp.class).equalTo("userid",uid).equalTo("identity",identity).findAll();
        if(jbxxs.size()>0){
            jbxxs.deleteAllFromRealm();
            return;
        }
        RealmResults<OaApp> jbxxss = mRealm.where(OaApp.class).equalTo("userid",uid).findAll();
        jbxxss = jbxxss.sort("id", Sort.DESCENDING);
        for(int i=5;i<jbxxss.size();i++){
            jbxxss.deleteFromRealm(i);
            i--;
        }
    }

    /**
     * 查询最新使用的应用
     * @return
     */
    public List<OaApp> queryAllOaApp(){
        String uid = preferencesHelper.getLoginMsg().getUid();
        RealmResults<OaApp> jbxxs = mRealm.where(OaApp.class).equalTo("userid",uid).findAll();
        jbxxs = jbxxs.sort("id", Sort.DESCENDING);
        return mRealm.copyFromRealm(jbxxs);
    }

    /**
     * 关闭数据库
     */
    public void onClose(){
        if(mRealm != null){
            mRealm.close();
        }
    }
}
