package com.ampsoft.sendback.etc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;

/**
 * Created by saki on 16. 12. 19..
 */
public class ChangePwOkActivity extends Activity {

    Context mContext;

    TextView tvChangePwOkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw_ok);
        mContext = this;

        tvChangePwOkBtn = (TextView) findViewById(R.id.tv_change_pw_ok_btn);

        tvChangePwOkBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePwOkActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChangePwOkActivity.this, BackPressed.class);
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
