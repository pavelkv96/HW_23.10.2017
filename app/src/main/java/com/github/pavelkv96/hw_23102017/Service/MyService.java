package com.github.pavelkv96.hw_23102017.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.github.pavelkv96.hw_23102017.Constants;

public class MyService extends Service {
    final String LOG_TAG = "myLogs";
    ExecutorService executorService;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        executorService = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");
        int time = intent.getIntExtra(Constants.PARAM_TIME, 1);
        int task = intent.getIntExtra(Constants.PARAM_TASK, 0);

        MyRun myRun = new MyRun(startId, time, task);
        executorService.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private class MyRun implements Runnable {
        int time;
        int startId;
        int task;

        MyRun(int startId, int time, int task) {
            this.time = time;
            this.startId = startId;
            this.task = task;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Intent intent = new Intent(Constants.TASK_ONE);
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                intent.putExtra(Constants.PARAM_TASK, task);
                intent.putExtra(Constants.PARAM_STATUS, Constants.STATUS_START);
                sendBroadcast(intent);

                TimeUnit.SECONDS.sleep(time);

                intent.putExtra(Constants.PARAM_STATUS, Constants.STATUS_FINISH);
                intent.putExtra(Constants.PARAM_RESULT, time * 100);
                sendBroadcast(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult(" + startId + ") = " + stopSelfResult(startId));
        }
    }
}