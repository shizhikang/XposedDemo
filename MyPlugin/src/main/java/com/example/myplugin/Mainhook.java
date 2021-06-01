package com.example.myplugin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Mainhook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("shizhikang - Mainhook", "handleLoadPackage: " + loadPackageParam.packageName);

        if (loadPackageParam.packageName.equals("com.baidu.BaiduMap")) {
            XposedHelpers.findAndHookMethod(Activity.class, "startActivity" , Intent.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.i("shizhikang - Mainhook", "startActivity: " + param.thisObject);
                    Log.i("shizhikang - Mainhook", "startActivity: " + param.args);
                    super.beforeHookedMethod(param);
                }
            });
        }

    }
}
