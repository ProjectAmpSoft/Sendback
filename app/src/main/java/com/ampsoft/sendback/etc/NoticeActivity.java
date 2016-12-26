package com.ampsoft.sendback.etc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ampsoft.sendback.DrawerActivity;
import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;

/**
 * Created by saki on 16. 12. 14..
 */
public class NoticeActivity extends Activity {

    LinearLayout llEtcNoticeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_notice);

        llEtcNoticeBack = (LinearLayout) findViewById(R.id.ll_etc_notice_back);

        llEtcNoticeBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NoticeActivity.this, BackPressed.class);
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
