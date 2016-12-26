package com.ampsoft.sendback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ampsoft.sendback.lib.BackPressed;

/**
 * Created by saki on 16. 12. 15..
 */
public class SaveActicity extends Activity {

    TextView tvSaveCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        tvSaveCancel = (TextView) findViewById(R.id.tv_save_cancel);

        tvSaveCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveActicity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SaveActicity.this, BackPressed.class);
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
