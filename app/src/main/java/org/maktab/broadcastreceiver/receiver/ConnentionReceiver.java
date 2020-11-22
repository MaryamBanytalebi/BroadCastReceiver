package org.maktab.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import org.maktab.broadcastreceiver.model.BroadCastLog;
import org.maktab.broadcastreceiver.reository.BroadCastLogsDBRepository;
import org.maktab.broadcastreceiver.reository.IRepository;

public class ConnentionReceiver extends BroadcastReceiver {

    private IRepository mIRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        mIRepository = BroadCastLogsDBRepository.getInstance(context);

        int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN);

        if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
//            Log.d("Network Available ", "WI-FI On");
//            Toast.makeText(context, "WI-FI On: " + intent.getAction(), Toast.LENGTH_SHORT).show();
            Long timestampLong = System.currentTimeMillis()/1000;
            String timestamp = timestampLong.toString();
            BroadCastLog broadCastLog = new BroadCastLog("WiFi","On",timestamp);
            mIRepository.insertBroadCastLog(broadCastLog);
        }
        else if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
//            Log.d("Network Available ", "WI-FI Off");
//            Toast.makeText(context, "WI-FI Off" + intent.getAction(), Toast.LENGTH_SHORT).show();
            Long timestampLong = System.currentTimeMillis()/1000;
            String timestamp = timestampLong.toString();
            BroadCastLog broadCastLog = new BroadCastLog("WiFi","Off",timestamp);
            mIRepository.insertBroadCastLog(broadCastLog);
        }
    }
}
