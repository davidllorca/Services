package com.davidllorca.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MyService5 extends Service {

    int counter = 0;
    static final int UPDATE_INTERVAL = 1000;
    private Timer timer;

    public MyService5() {
        this.timer = new Timer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Toast.makeText(this, "Service5 started", Toast.LENGTH_LONG).show();

        Object[] objUrls = (Object[]) intent.getExtras().get("URLs");
        URL[] urls = new URL[objUrls.length];
        for (int i = 0; i < urls.length; i++) {
            urls[i] = (URL) objUrls[i];
        }
        new DoBackgroundTask().execute(urls);
        return START_STICKY;
    }

    private void doSomeThingRepeated() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() { // It will run in another Thread
                Log.d("Downloading files", "MyService5" + String.valueOf(++counter));
            }
        }, 0, UPDATE_INTERVAL);
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

    private class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytesDownloaded = 0;
            for (int i = 0; i < count; i++) {
                totalBytesDownloaded += downloadFile(urls[i]);
                // Calculate percentage downloaded and report its progress
                publishProgress((int) (((i + 1) / (float) count) * 100));
            }
            return totalBytesDownloaded;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            Log.d("Downloading files", String.valueOf(progress[0]) + "% downloaded");
            Toast.makeText(getBaseContext(), String.valueOf(progress[0]) + "% downloaded", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(), "Downloaded " + result + " bytes", Toast.LENGTH_SHORT).show();
            stopSelf(); // Do the same to stopService()
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
        }
        Toast.makeText(this, "Service5 destroy", Toast.LENGTH_LONG).show();
    }
}
