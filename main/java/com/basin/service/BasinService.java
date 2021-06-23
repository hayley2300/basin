package com.basin.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BasinService extends Service {

    private IBinder mIBinder = new BasinBinder();

    public int var = 777; //서비스바인딩의 예시로 출력할 값

    public class BasinBinder extends Binder {
        BasinService getService(){
            return BasinService.this;
        }
    }

    @Override // 서비스가 최초 생성될 때만 호출
    public void onCreate() {
    }

    @Override // startService()로 서비스를 시작할 때 호출
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override // bindService()로 바인딩을 실행할 때 호출
    public IBinder onBind(Intent intent) {

        return mIBinder;
    }

    @Override // unbindService()로 바인딩을 해제할 때 호출
    public boolean onUnbind(Intent intent) {

        return true;
    }

    @Override // 이미 onUnbind()가 호출된 후에 bindService()로 바인딩을 실행할 때 호출
    public void onRebind(Intent intent) {

    }

    @Override // 서비스가 소멸될 때 호출
    public void onDestroy() {

    }

}
