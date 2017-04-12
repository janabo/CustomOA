package com.dk.mp.core.util;

import android.content.Context;

import com.dk.mp.core.R;
import com.dk.mp.core.application.MyApplication;

/**
 * 图片工具类.
 * @since 
 * @version 2012-10-16
 * @author wangw
 */
public class ImageUtil {
	/** 
	 * 获取图片名称获取图片的资源id的方法 .
	 * @param imageName 图片名称
	 * @return  资源id
	 */
	public static int getResource(String imageName) {
		Context context = MyApplication.getContext();
		int resId = context.getResources().getIdentifier(imageName, "mipmap", context.getPackageName());
		return resId;
	}

	public static int getRes(String appname){
		int appid;
			switch (appname){
				case "tzgg"://通知公告
					appid = R.mipmap.app_tzgg;
					break;
				case "gzzd"://规章制度
					appid = R.mipmap.app_gzzd;
					break;
				case "zbap"://值班安排
					appid = R.mipmap.app_zbap;
					break;
				case "grrc"://领导日程
					appid = R.mipmap.app_ldrc;
					break;
				case "hygl"://会议管理
					appid = R.mipmap.app_hygl;
					break;
				case "cy"://我的传阅
					appid = R.mipmap.app_wdcy;
					break;
				case "gk"://公开
					appid = R.mipmap.app_gk;
					break;
				case "wdsh"://我的审核
					appid = R.mipmap.app_wdsh;
					break;
				case "wddb"://我的待办
					appid = R.mipmap.app_wddb;
					break;
				case "dwsq"://我的申请
					appid = R.mipmap.app_wdsq;
					break;
				case "wdcg"://我的草稿
					appid = R.mipmap.app_wdcg;
					break;
				case "ywhy"://邀我参加的会议
					appid = R.mipmap.app_ywhy;
					break;
				default:
					appid = R.mipmap.app_hygl;
					break;
			}
		return appid;
	}



}
