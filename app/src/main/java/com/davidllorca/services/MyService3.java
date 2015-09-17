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

public class MyService3 extends Service {

   @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Toast.makeText(this, "Service3 started", Toast.LENGTH_LONG).show();

        try {
            new DoBackgroundTask().execute(
                    new URL("http://www.amazon.com/somefile.pdf"),
                    new URL("http://www.google.com/somefile.pdf"),
                    new URL("http://www.elmundotoday.com/somefile.pdf")
            );
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
        Toast.makeText(this, "Service3 destroy", Toast.LENGTH_LONG).show();
    }
}
