package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;
import com.ampsoft.sendback.lib.SendbackSharedPreferences;
import com.ampsoft.sendback.proc.ProcessIdSearch;
import com.ampsoft.sendback.proc.ProcessJoin;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by saki on 16. 11. 25..
 */
public class IdSearchOkActivity extends Activity {

    Context mContext;
    TextView tvIdSearchOkNext, tvIdSearchOkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_search_ok);
        mContext = this;

        tvIdSearchOkNext = (TextView) findViewById(R.id.tv_id_search_ok_next);
        tvIdSearchOkId = (TextView) findViewById(R.id.tv_id_search_ok_id);

        SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);
        tvIdSearchOkId.setText(pref.getString("id"));

        tvIdSearchOkNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);
                pref.putString("id","");
                pref.putString("phone","");
                Intent intent = new Intent(IdSearchOkActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(IdSearchOkActivity.this, BackPressed.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (String.valueOf(resultCode).equals("-1")) {
            finish();
        }
    }
}
