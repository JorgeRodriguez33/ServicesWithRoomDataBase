package com.example.serviceandroidwithroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class BroadcastReceiverTest extends BroadcastReceiver {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        //Si recibo el aviso de que el dispositivo se termino de reinciar entonces inicio servicio
            if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
                Intent serviceIntent = new Intent(context,ForegroundService.class);
                context.startForegroundService(serviceIntent);
            }
    }


}
