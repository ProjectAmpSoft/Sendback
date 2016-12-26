package com.ampsoft.sendback.lib;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.ampsoft.sendback.R;

/**
 * Created by saki on 16. 12. 19..
 */
public class BackPressed extends Activity {

    Context mContext;

    TextView tvBackpressedYes, tvBackpressedNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_backpressed);
        mContext = this;

        tvBackpressedYes = (TextView) findViewById(R.id.tv_backpressed_yes);
        tvBackpressedNo = (TextView) findViewById(R.id.tv_backpressed_no);

        tvBackpressedYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, (new Intent()).setAction("1"));
                finish();
            }
        });

        tvBackpressedNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
