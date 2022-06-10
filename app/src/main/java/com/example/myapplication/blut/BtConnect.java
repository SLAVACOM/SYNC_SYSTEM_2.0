package com.example.myapplication.blut;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.adapter.BtCONST;

public class BtConnect {
    private SharedPreferences sPreferences;
    private BluetoothAdapter btAdapter;
    private BluetoothDevice device;

    private ConnectThread connectThread;

    private String mac;

    public BtConnect(Context context) {
        sPreferences = context.getSharedPreferences(BtCONST.MY_PREF,Context.MODE_PRIVATE);
        mac = sPreferences.getString(BtCONST.MAC_KEY,"");
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public BtConnect(String deviceMac) {
        mac = deviceMac;
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }
    public BluetoothDevice connecting(Runnable r){
        if(!btAdapter.isEnabled() || mac.isEmpty()) return null;
        device = btAdapter.getRemoteDevice(mac);

        if( device == null) return device;
        connectThread = new ConnectThread(btAdapter, device, r);
        connectThread.start();
        return  device;
    }

    public void sendMess(String message){
        if (connectThread!=null){
            if (connectThread.getReceiveThread() != null) {
                try {
                    connectThread.getReceiveThread().sendMess(message.getBytes());
                } catch (Exception e) {

                }
            }
        }
    }

    public ConnectThread getConnectThread() {
        return connectThread;
    }
}

