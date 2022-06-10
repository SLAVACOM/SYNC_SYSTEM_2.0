package com.example.myapplication.blut;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
;

import com.example.myapplication.AppStatus;

import java.io.IOException;


public class ConnectThread extends Thread {
    private ReceiveThread receiveThread;
    protected BluetoothAdapter btAdapter;
    private BluetoothSocket mainSocket;
    public static final String UUID = "00001101-0000-1000-8000-00805F9B34FB";
    Runnable r;


    @SuppressLint("MissingPermission")
    public ConnectThread(BluetoothAdapter btAdapter, BluetoothDevice device, Runnable r) {
        this.btAdapter = btAdapter;
        this.r = r;
        try {
            try {
                mainSocket = device.createRfcommSocketToServiceRecord(java.util.UUID.fromString(UUID));
            } catch (SecurityException e) {
            }
        } catch (IOException e) {

        }
    }




    @SuppressLint("MissingPermission")
    @Override
    public void run() {
        try {
            btAdapter.cancelDiscovery();
        } catch (SecurityException e) {
        }
        try {
            try {
                mainSocket.connect();
                receiveThread = new ReceiveThread(mainSocket);
                receiveThread.start();
            } catch (SecurityException e) {
            }
            Log.d("MyLog", "Connected");
            if (r != null) r.run();
            AppStatus.connectStatus= true;

        } catch (IOException e) {
            Log.d("MyLog", "Not connected");
            AppStatus.connectStatus=false;
        }
    }

    public void closeConnection() {
        try {
            mainSocket.close();
            AppStatus.connectStatus= false;
        } catch (IOException e) {
        }
    }


    public ReceiveThread getReceiveThread() {
        return receiveThread;
    }
}
