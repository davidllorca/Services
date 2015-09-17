package com.davidllorca.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;


public class ServicesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
        // o
        // startService(new Intent("com.davidllorca.MyService"));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }
}
