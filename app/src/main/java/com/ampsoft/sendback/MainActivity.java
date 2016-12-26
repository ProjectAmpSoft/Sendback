package com.ampsoft.sendback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.ampsoft.sendback.lib.SendbackSharedPreferences;
import com.ampsoft.sendback.login.LoginActivity;

/**
 * Created by saki on 16. 11. 9..
 */

public class MainActivity extends Activity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);
                String id = "";

                id = pref.getString("id");

                if (id.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, DrawerActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 1500);

    }

}