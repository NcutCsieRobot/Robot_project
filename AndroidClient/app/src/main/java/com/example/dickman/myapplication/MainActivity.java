package com.example.dickman.myapplication;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            UDP_Request a = new UDP_Request ("255.255.255.255", 9487, 9487, 1000, 1000);
            a.send("9487");
            Log.d("receive", a.receive());

        } catch (IOException e) {
            Log.e("error", e.toString());
        }
    }
}


