package com.tielan.update.appupdate.update;

import android.app.Activity;

import com.vector.update_app.UpdateAppManager;

/**
 * Created by Administrator on 2019/2/20 0020.
 */
//NetApiManager.getBaseUrl()+"/index.php/api?method=app.update&format=json&v=v1&version="+version+"&versionCode="+versionCode
public class AppVersionManager {

    public static boolean check(Activity mContext,String updateUrl){
        new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(mContext)
                .setIgnoreDefParams(true)
                .setAppKey("")
                //更新地址
                .setUpdateUrl(updateUrl)
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                .build()
                .checkNewApp(new AppUpdateCallback());
        return true;
    }

    public static String getRealURL(String url) {
        return url;
    }
}
