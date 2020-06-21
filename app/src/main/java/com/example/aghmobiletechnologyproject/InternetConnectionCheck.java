package com.example.aghmobiletechnologyproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class InternetConnectionCheck extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            if(noConnectivity){
                Toast.makeText(context, "Internet disconnected", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Internet connected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
