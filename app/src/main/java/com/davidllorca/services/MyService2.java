package com.davidllorca.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MyService2 extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Toast.makeText(this, "Service2 started", Toast.LENGTH_LONG).show();

        // Basic example
        try {
            int result = downloadFile(new URL("http://wwww.somefile.com"));
            Toast.makeText(this, "Downloaded " + result + " bytes", Toast.LENGTH_LONG).show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    private int downloadFile(URL url) {
        try {
            // Simulate taking some time to download a file
            // UI thread will sleep too!!! Service will run in the same thread
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service2 destroy", Toast.LENGTH_LONG).show();
    }
}
