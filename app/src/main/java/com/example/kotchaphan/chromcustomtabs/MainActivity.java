package com.example.kotchaphan.chromcustomtabs;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomTabsServiceConnection customTabsServiceConnection;
    private CustomTabsSession customTabsSession;
    
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
                //disconnect
            }
        };
        CustomTabsClient.bindCustomTabsService(this, chromePackage, customTabsServiceConnection);
    }

    public void createCustomTabSession(CustomTabsClient customTabsClient){
        customTabsSession = customTabsClient.newSession(new CustomTabsCallback(){
            @Override
            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                super.onNavigationEvent(navigationEvent, extras);

                //Then Navigation Event on Custom Tab
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //killer object
        if(customTabsServiceConnection != null)
            unbindService(customTabsServiceConnection);
    }
}
