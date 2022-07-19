package com.example.broadcastcounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String intentAction = intent.getAction();
        if(intentAction!=null) {
            String toast = "unknown";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    Intent local = new Intent();
                    local.setAction("service.to.activity.transfer");
                    local.putExtra("powerConnected", 1);
                    context.sendBroadcast(local);
                    break;
            }
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
        }
    }

}
