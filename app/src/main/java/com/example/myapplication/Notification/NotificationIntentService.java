package com.example.myapplication.Notification;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.adapter.BtCONST;
import com.example.myapplication.blut.BtConnect;


public class NotificationIntentService extends IntentService {

    private BtConnect btConnect;
    public NotificationIntentService() {
        super("NotificationIntent_Service");

    }

    @Override
    public void onHandleIntent(@Nullable Intent intent) {
        String mac = this.getSharedPreferences(BtCONST.MY_PREF, MODE_PRIVATE).getString(BtCONST.MAC_KEY, "");
        System.out.println(mac);
        btConnect = new BtConnect(mac);
        btConnect.connecting(new Runnable() {
            @Override
            public void run() {

                switch (intent.getAction()) {
                    case "A_button":
                        btConnect.sendMess("A");
                        btConnect.getConnectThread().closeConnection();
                        break;
                    case "B_button":
                        btConnect.sendMess("B");
                        btConnect.getConnectThread().closeConnection();
                        break;
                    case "C_button":
                        btConnect.sendMess("C");
                        btConnect.getConnectThread().closeConnection();
                        break;
                    case "D_button":
                        btConnect.sendMess("D");
                        btConnect.getConnectThread().closeConnection();
                        break;
                }
            }
        });
    }
}
