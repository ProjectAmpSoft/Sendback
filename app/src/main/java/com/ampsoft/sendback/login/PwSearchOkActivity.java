package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;
import com.ampsoft.sendback.lib.SendbackSharedPreferences;

/**
 * Created by saki on 16. 11. 25..
 */
public class PwSearchOkActivity extends Activity {

    Context mContext;
    TextView tvPwSearchOkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_search_ok);
        mContext = this;

        tvPwSearchOkBtn = (TextView) findViewById(R.id.tv_pw_search_ok_btn);

        tvPwSearchOkBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);
                pref.putString("id","");
                pref.putString("phone","");
                Intent intent = new Intent(PwSearchOkActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PwSearchOkActivity.this, BackPressed.class);
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
