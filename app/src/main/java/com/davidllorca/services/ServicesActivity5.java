package com.davidllorca.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.net.MalformedURLException;
import java.net.URL;


public class ServicesActivity5 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
    }

    public void startService(View view) {
        Intent intent = new Intent(getBaseContext(), MyService5.class);
        try {
            URL[] urls = new URL[]{
                    new URL("http://www.amazon.com/somefile.pdf"),
                    new URL("http://www.google.com/somefile.pdf"),
                    new URL("http://www.elmundotoday.com/somefile.pdf")};
            // Add urls to Extras
            intent.putExtra("URLs", urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        startService(intent);
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService5.class));
    }
}
