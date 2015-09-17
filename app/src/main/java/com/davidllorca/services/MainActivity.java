package com.davidllorca.services;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set same Listener for all buttons
        LinearLayout layout =(LinearLayout)findViewById(R.id.main_layout);
        for (int i = 0; i < layout.getChildCount(); i++) {
            layout.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simple_service_btn:
                startActivity(new Intent(MainActivity.this, ServicesActivity.class));
                break;
            case R.id.simple_service_ui_thread_btn:
                startActivity(new Intent(MainActivity.this, ServicesActivity2.class));
                break;
            case R.id.service_async_btn:
                startActivity(new Intent(MainActivity.this, ServicesActivity3.class));
                break;
            case R.id.repeated_task_service_btn:
                startActivity(new Intent(MainActivity.this, ServicesActivity4.class));
                break;
            case R.id.intent_service_btn:
                startActivity(new Intent(MainActivity.this, MyIntentServicesActivity.class));
                break;
            case R.id.communicate_service_activity_btn:
                startActivity(new Intent(MainActivity.this, MyIntentServicesActivity2.class));
                break;
            case R.id.link_activity_service_btn:
                startActivity(new Intent(MainActivity.this, ServicesActivity5.class));
                break;
            case R.id.access_service_from_activity_btn:
                startActivity(new Intent(MainActivity.this, ServicesActivity6.class));
                break;
            default:
                break;
        }
    }
}
