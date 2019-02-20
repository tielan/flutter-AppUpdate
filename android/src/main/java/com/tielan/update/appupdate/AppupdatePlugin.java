package com.tielan.update.appupdate;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.tielan.update.appupdate.update.AppVersionManager;
import com.tielan.update.appupdate.utils.APKVersionCodeUtils;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * AppupdatePlugin
 */
public class AppupdatePlugin implements MethodCallHandler {

    private Activity activity;

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "appupdate");
        channel.setMethodCallHandler(new AppupdatePlugin(registrar.activity()));
    }

    AppupdatePlugin(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("getAppVersion")) {
            result.success(APKVersionCodeUtils.getVerName(activity));
        } else if (call.method.equals("getVersionCode")) {
            result.success(APKVersionCodeUtils.getVersionCode(activity));
        } else if (call.method.equals("check")) {
            result.success(AppVersionManager.check(activity,call.argument("url")+""));
        } else {
            result.notImplemented();
        }
    }
  //https://pmall.52pht.com/index.php/api?method=app.update&format=json&v=v1&

}

