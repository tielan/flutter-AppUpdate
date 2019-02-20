package com.tielan.update.appupdate.update;

import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import org.json.JSONObject;

import java.util.Map;

public class AppUpdateCallback  extends UpdateCallback {
    private static  String appVersion = "";
    public static void  setAppVersion(String appVersion){
        AppUpdateCallback.appVersion = appVersion;
    }

    /**
     * 解析json,自定义协议
     *
     * @param json 服务器返回的json
     * @return UpdateAppBean
     */
    protected UpdateAppBean parseJson(String json) {
        UpdateAppBean updateAppBean = new UpdateAppBean();
        try {
            JSONObject responseJson = new JSONObject(json);
            JSONObject result = (JSONObject) responseJson.get("result");
            updateAppBean.setUpdate(appVersion.compareTo(result.get("new_version")+"") < 0 ? "Yes": "No")
                    .setOriginRes(json)
                    .setNewVersion(result.get("new_version")+"")
                    .setApkFileUrl(result.get("apk_file_url")+"")
                    .setTargetSize(result.get("target_size")+"")
                    .setUpdateLog(result.get("update_log")+"")
                    .setConstraint("true".equals(result.get("constraint")))
                    .setNewMd5(result.get("new_md5")+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateAppBean;
    }

    /**
     * 有新版本
     *
     * @param updateApp        新版本信息
     * @param updateAppManager app更新管理器
     */
    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
        updateAppManager.showDialogFragment();
    }

    /**
     * 网路请求之后
     */
    protected void onAfter() {
    }


    /**
     * 没有新版本
     * @param error HttpManager实现类请求出错返回的错误消息，交给使用者自己返回，有可能不同的应用错误内容需要提示给客户
     */
    protected void noNewApp(String error) {
    }

    /**
     * 网络请求之前
     */
    protected void onBefore() {
    }

}