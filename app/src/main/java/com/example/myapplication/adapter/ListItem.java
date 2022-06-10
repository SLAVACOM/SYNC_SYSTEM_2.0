package com.example.myapplication.adapter;

import android.app.LauncherActivity;
import android.bluetooth.BluetoothDevice;

public class ListItem extends LauncherActivity.ListItem {
    private BluetoothDevice bluetoothDevice;

    private String itemType =BtAdapter.DEF_ITEM_TYPE;

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }
    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }



}
