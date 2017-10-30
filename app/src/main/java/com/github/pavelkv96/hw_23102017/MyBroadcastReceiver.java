package com.github.pavelkv96.hw_23102017;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.pavelkv96.hw_23102017.Service.MyService;

public class MyBroadcastReceiver extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    TextView mTaskOneTextView;
    TextView mTaskTwoTextView;
    TextView mTaskThreeTextView;
    BroadcastReceiver mBroadcastReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_broadcast_receiver);

        mTaskOneTextView = (TextView) findViewById(R.id.tvTask1);
        mTaskTwoTextView = (TextView) findViewById(R.id.tvTask2);
        mTaskThreeTextView = (TextView) findViewById(R.id.tvTask3);

        mBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(Constants.PARAM_TASK, 0);
                int status = intent.getIntExtra(Constants.PARAM_STATUS, 0);
                Log.d(LOG_TAG, "onReceive: task = " + task + ", status = " + status);

                if (status == Constants.STATUS_START) {
                    switch (task) {
                        case Constants.TASK1_CODE:
                            mTaskOneTextView.setText(Constants.TASK_ONE + Constants.START);
                            break;
                        case Constants.TASK2_CODE:
                            mTaskTwoTextView.setText(Constants.TASK_TWO + Constants.START);
                            break;
                        case Constants.TASK3_CODE:
                            mTaskThreeTextView.setText(Constants.TASK_THREE + Constants.START);
                            break;
                    }
                }

                if (status == Constants.STATUS_FINISH) {
                    int result = intent.getIntExtra(Constants.PARAM_RESULT, 0);
                    switch (task) {
                        case Constants.TASK1_CODE:
                            mTaskOneTextView.setText(Constants.TASK_ONE + Constants.FINISH_RESULT + result);
                            break;
                        case Constants.TASK2_CODE:
                            mTaskTwoTextView.setText(Constants.TASK_TWO + Constants.FINISH_RESULT + result);
                            break;
                        case Constants.TASK3_CODE:
                            mTaskThreeTextView.setText(Constants.TASK_THREE + Constants.FINISH_RESULT + result);
                            break;
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(Constants.TASK_ONE);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    public void onClickStart(View v) {
        Intent intentService;

        intentService = new Intent(this, MyService.class);
        intentService.putExtra(Constants.PARAM_TIME, 7);
        intentService.putExtra(Constants.PARAM_TASK, Constants.TASK1_CODE);
        startService(intentService);

        intentService = new Intent(this, MyService.class);
        intentService.putExtra(Constants.PARAM_TIME, 4);
        intentService.putExtra(Constants.PARAM_TASK, Constants.TASK2_CODE);
        startService(intentService);

        intentService = new Intent(this, MyService.class);
        intentService.putExtra(Constants.PARAM_TIME, 3);
        intentService.putExtra(Constants.PARAM_TASK, Constants.TASK3_CODE);
        startService(intentService);
    }
}