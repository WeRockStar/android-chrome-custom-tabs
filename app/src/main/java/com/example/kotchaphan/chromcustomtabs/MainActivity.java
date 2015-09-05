package com.example.kotchaphan.chromcustomtabs;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsService;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomTabsServiceConnection customTabsServiceConnection;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connectCustomTabsService(){
        String chromePackage = "com.android.chrome";
        customTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }
    }

    
}
