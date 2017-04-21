package com.tjstudy.bledemo2;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.tjstudy.bledemo2.base.BaseActivity;
import com.tjstudy.bledemo2.base.OnPermissionCallbackListener;

import java.util.List;

public class MainActivity extends BaseActivity {

    private BluetoothManager mBLEManager;
    private BluetoothAdapter mBLEAdapter;
    private BluetoothAdapter.LeScanCallback mBLECallBack;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPermission();
        initBLE();
    }

    public void onStart(View view) {
        scanDevice(true);
    }

    private void initBLE() {
        mBLEManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBLEAdapter = mBLEManager.getAdapter();
        mBLECallBack = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                Log.e("MainActivity", "device address=" + device.getAddress());
            }
        };
    }

    private void scanDevice(boolean enable) {
        if (enable) {
            Log.e("MainActivity", "在扫描");
            //指定最长的扫描时间
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e("MainActivity", "关闭扫描");
                    mBLEAdapter.stopLeScan(mBLECallBack);
                }
            }, 10000);
            mBLEAdapter.stopLeScan(mBLECallBack);//据网上的博客说  先关闭  再启动 成功率会高一些
            mBLEAdapter.startLeScan(mBLECallBack);
        } else {
            Log.e("MainActivity", "关闭扫描");
            mBLEAdapter.stopLeScan(mBLECallBack);
        }
    }

    /**
     * 动态申请权限
     */
    private void initPermission() {
        onRequestPermission(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                new OnPermissionCallbackListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied(List<String> deniedPermissions) {

                    }
                });
    }
}
