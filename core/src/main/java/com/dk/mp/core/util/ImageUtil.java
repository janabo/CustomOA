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
				case "通知公告":
					appid = R.mipmap.app_tzgg;
					break;
				case "规章制度":
					appid = R.mipmap.app_gzzd;
					break;
				case "值班安排":
					appid = R.mipmap.app_zbap;
					break;
				case "领导日程":
					appid = R.mipmap.app_ldrc;
					break;
				case "会议管理":
					appid = R.mipmap.app_hygl;
					break;
				case "我的传阅":
					appid = R.mipmap.app_wdcy;
					break;
				case "公开":
					appid = R.mipmap.app_gk;
					break;
				case "我的审核":
					appid = R.mipmap.app_wdsh;
					break;
				case "我的待办":
					appid = R.mipmap.app_wddb;
					break;
				case "我的申请":
					appid = R.mipmap.app_wdsq;
					break;
				case "我的草稿":
					appid = R.mipmap.app_wdcg;
					break;
				case "邀我参加的会议":
					appid = R.mipmap.app_ywhy;
					break;
				default:
					appid = R.mipmap.app_hygl;
					break;
			}
		return appid;
	}



}
