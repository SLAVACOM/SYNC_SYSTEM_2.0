package com.example.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.myapplication.adapter.BtCONST;
import com.example.myapplication.blut.BtConnect;

public class MyIntentService extends IntentService {
    private BtConnect btConnect;


    public MyIntentService() {
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String mac = this.getSharedPreferences(BtCONST.MY_PREF, MODE_PRIVATE).getString(BtCONST.MAC_KEY, "");
        System.out.println(mac);
        btConnect = new BtConnect(mac);
        btConnect.connecting(new Runnable() {
            @Override
            public void run() {

                switch (intent.getAction()) {
                    case "A_button":
                        btConnect.sendMess("A");
                        btConnect.sendMess("a");
                        btConnect.getConnectThread().closeConnection();
                        break;
                    case "B_button":
                        btConnect.sendMess("B");
                        btConnect.sendMess("b");
                        btConnect.getConnectThread().closeConnection();
                        break;
                    case "C_button":
                        btConnect.sendMess("C");
                        btConnect.sendMess("c");
                        btConnect.getConnectThread().closeConnection();
                        break;
                    case "D_button":
                        btConnect.sendMess("D");
                        btConnect.sendMess("d");
                        btConnect.getConnectThread().closeConnection();
                        break;
                }
            }
        });
    }
}