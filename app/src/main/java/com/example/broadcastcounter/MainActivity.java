package com.example.broadcastcounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class MainActivity extends AppCompatActivity {
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    private PowerReceiver mReceiver = new PowerReceiver();
    private TextView counter;
    private int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = findViewById(R.id.powerCounter);
        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        mReceiver = new PowerReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //UI update here
                if (intent != null){
                    int extra = intent.getIntExtra("connectedPower", 0);
                   count++;
                   counter.setText(Integer.toString(count));
                }
            }
        };

//        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
        this.registerReceiver(mReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
        this.unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }
}