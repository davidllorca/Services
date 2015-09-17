package com.davidllorca.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class ServicesActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService2.class));
        // o
        // startService(new Intent("com.davidllorca.MyService"));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService2.class));
    }
}
