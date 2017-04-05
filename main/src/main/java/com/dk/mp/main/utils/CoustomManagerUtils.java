package com.dk.mp.main.utils;

import com.dk.mp.core.entity.OaItemEntity;
import com.dk.mp.core.util.CoreSharedPreferencesHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                OaItemEntity oItem = oaItemEntityList.get(i);
                oItem.setId(i);
                oaItemEntityList.get(i).setShow("true".equals(oItem.getDiy())?true:false);
            }
            preferencesHelper.setValue(uid + "_customoa",gson.toJson(oaItemEntityList));
            return oaItemEntityList;
        }

        Map<String,String> oldMap = new HashMap<>();
        //本地存在原始数据
        List<OaItemEntity> list = new ArrayList<OaItemEntity>();
        for (OaItemEntity oldentity:loaclDatas){
            oldMap.put(oldentity.getLabel(),oldentity.getLabel());
            for (OaItemEntity newentity:oaItemEntityList){
                if (oldentity.getLabel().equals(newentity.getLabel())){
                    newentity.setShow(oldentity.isShow());
                    list.add(newentity);
                }
            }
        }

        for (OaItemEntity t : oaItemEntityList){
            if(oldMap.get(t.getLabel())==null){
                t.setShow("true".equals(t.getDiy())?true:false);
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
