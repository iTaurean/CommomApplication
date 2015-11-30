package com.android.lvxin.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.android.lvxin.sdk.proxy.AppProxy;

/**
 * @ClassName: AppService
 * @Author: lvxin
 * @Description: 随App启动的全局Service
 * @Date: 11/26/15 16:10
 */
public class AppService extends Service {

    private ServiceBinder mBinder;

    public AppService() {
        mBinder = new ServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public static class ServiceBinder extends Binder {

        public AppProxy getProxy() {
            return new AppProxy();
        }
    }
}
