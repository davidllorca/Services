package com.davidllorca.services;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import java.net.MalformedURLException;
import java.net.URL;


public class ServicesActivity6 extends Activity {

    MyService6 serviceBinder;
    Intent intent;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // Called when the connection is made
            serviceBinder = ((MyService6.MyBinder) service).getService();

            try {
                URL[] urls = new URL[]{
                        new URL("http://www.amazon.com/somefile.pdf"),
                        new URL("http://www.google.com/somefile.pdf"),
                        new URL("http://www.elmundotoday.com/somefile.pdf")};
                // Assign the URLs to the service through the serviceBinder object
                serviceBinder.urls = urls;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            startService(intent);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // Called when the service disconnects
            serviceBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
    }

    public void startService(View view) {
        intent = new Intent(getBaseContext(), MyService6.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService6.class));
    }
}
