package com.davidllorca.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * When task finish service destroy itself and send a Broadcast with specific Action for filters.
 */
public class MyIntentService2 extends IntentService {

    public MyIntentService2() {
        super("MyIntentServiceName");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
            int result = downloadFile(new URL("http://wwww.somefile.com"));
            Log.d("IntentService2", "Downloaded " + result + " bytes");

            // Notify Activity that file has been downloaded with a broadcast
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("FILE_DOWNLOAD_ACTION");
            getBaseContext().sendBroadcast(broadcastIntent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
}
