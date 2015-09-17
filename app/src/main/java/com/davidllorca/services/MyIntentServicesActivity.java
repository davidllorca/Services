package com.davidllorca.services;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *
 */
public class MyIntentServicesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Button stopServiceButton = (Button)findViewById(R.id.stop_service_btn);
        stopServiceButton.setVisibility(View.GONE);
    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyIntentService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyIntentService.class));
    }
}
