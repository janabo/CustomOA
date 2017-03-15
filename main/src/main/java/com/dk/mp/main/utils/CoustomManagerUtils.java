package com.dk.mp.main.utils;

import com.dk.mp.core.util.CoreSharedPreferencesHelper;
import com.dk.mp.main.entity.OaItemEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqs on 2017/3/10.
 */

public class CoustomManagerUtils {

    public static List<OaItemEntity> getEntitlyList(CoreSharedPreferencesHelper preferencesHelper, Gson gson, List<OaItemEntity> oaItemEntityList){

        //获取用户数据
        String uid = preferencesHelper.getLoginMsg().getUid();

        //获取本地的list数据
        List<OaItemEntity> loaclDatas = gson.fromJson(preferencesHelper.getValue(uid + "_customoa"),new TypeToken<List<OaItemEntity>>(){}.getType());

        //第一次登陆，本地没有初始数据
        if (loaclDatas == null || loaclDatas.size() == 0) {
            for (int i = 0;i < oaItemEntityList.size();i++){
                oaItemEntityList.get(i).setId(i);
                oaItemEntityList.get(i).setShow(true);
            }
            preferencesHelper.setValue(uid + "_customoa",gson.toJson(oaItemEntityList));
            return oaItemEntityList;
        }

        //本地存在原始数据
        List<OaItemEntity> list = new ArrayList<OaItemEntity>();
        for (OaItemEntity oldentity:loaclDatas){
            for (OaItemEntity newentity:oaItemEntityList){
                if (oldentity.getName().equals(newentity.getName())){
                    newentity.setShow(oldentity.isShow());
                    list.add(newentity);
                }
            }
        }
        for (OaItemEntity t : oaItemEntityList){
            boolean isin = false;
            for (OaItemEntity f : list) {
                if (t.getName().equals(f.getName())){
                    isin = true;
                    break;
                }
            }
            if(!isin) {
                t.setShow(true);
                list.add(t);
            }
        }
        for (int i = 0;i < list.size();i++){
            list.get(i).setId(i);
        }
        preferencesHelper.setValue(uid + "_customoa",gson.toJson(list));
        return list;
    }
}
