package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.guide.GuideActivity;
import com.ampsoft.sendback.lib.BackPressed;

/**
 * Created by saki on 16. 11. 23..
 */
public class Join4Activity extends Activity {

    Context mContext;
    TextView tvJoin4Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join4);
        mContext = this;

        tvJoin4Next = (TextView) findViewById(R.id.tv_join3_next);

        tvJoin4Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Join4Activity.this, GuideActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Join4Activity.this, BackPressed.class);
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