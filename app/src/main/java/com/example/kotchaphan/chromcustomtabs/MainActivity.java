package com.example.kotchaphan.chromcustomtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CustomTabsServiceConnection customTabsServiceConnection;
    private CustomTabsSession customTabsSession;
    private Uri uri = Uri.parse("http://www.akexorcist.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btnClcik)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomTabs();
            }
        });

        //call service
        connectCustomTabsService();
    }

    public void connectCustomTabsService() {
        String chromePackage = "com.android.chrome";
        customTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //connected
                createCustomTabSession(customTabsClient);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                //disconnect
            }
        };
        CustomTabsClient.bindCustomTabsService(this, chromePackage, customTabsServiceConnection);
    }

    public void createCustomTabSession(CustomTabsClient customTabsClient) {
        //เตรียมพร้อมจะทำงาน
        customTabsClient.warmup(0);
        customTabsSession = customTabsClient.newSession(new CustomTabsCallback() {
            @Override
            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                super.onNavigationEvent(navigationEvent, extras);

                //Then Navigation Event on Custom Tab
            }
        });
        customTabsSession.mayLaunchUrl(uri, null, null);
    }

    public void openCustomTabs() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "demo@hotmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "demo subject");
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        builder.setShowTitle(true);
        builder.setToolbarColor(Color.rgb(236, 64, 122));
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.backspace));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.send);
        builder.setActionButton(bitmap, "Send email", pendingIntent);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, uri);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //killer object
        if (customTabsServiceConnection != null)
            unbindService(customTabsServiceConnection);
    }
}
