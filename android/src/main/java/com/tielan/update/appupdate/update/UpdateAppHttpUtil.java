package com.tielan.update.appupdate.update;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.vector.update_app.HttpManager;

import java.io.File;
import java.util.Map;

public class UpdateAppHttpUtil implements HttpManager {
    /**
     * 异步get
     *
     * @param url      get请求地址
     * @param params   get参数
     * @param callBack 回调
     */
    @Override
    public void asyncGet(String url, Map<String, String> params, final Callback callBack) {
        Log.d("AppUpdate",url);
        OkGo.<String>get(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                callBack.onResponse(response.body());
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                callBack.onError(response.message());
            }
        });
    }

    /**
     * 异步post
     *
     * @param url      post请求地址
     * @param params   post请求参数
     * @param callBack 回调
     */
    @Override
    public void asyncPost( String url,  Map<String, String> params,  final Callback callBack) {
        OkGo.<String>post(url).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                callBack.onResponse(response.body());
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                callBack.onError(response.message());
            }
        });

    }

    /**
     * 下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 回调
     */
    @Override
    public void download( String url,  String path,  String fileName,  final FileCallback callback) {
        String downloadUrl = AppVersionManager.getRealURL(url);
        OkGo.<File>get(downloadUrl).execute(new com.lzy.okgo.callback.FileCallback(path,fileName){
            @Override
            public void onStart(com.lzy.okgo.request.base.Request<File, ? extends com.lzy.okgo.request.base.Request> request) {
                callback.onBefore();
            }

            @Override
            public void downloadProgress(Progress progress) {
                callback.onProgress((float) (progress.currentSize*1.0/progress.totalSize), progress.totalSize);
            }
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<File> response) {
                callback.onResponse(response.body());
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<File> response) {
                super.onError(response);
                callback.onError(response.message());
            }
        });
    }
}