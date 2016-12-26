package com.ampsoft.sendback.etc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.ampsoft.sendback.DrawerActivity;
import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;

/**
 * Created by saki on 16. 12. 14..
 */
public class CreditActivity extends Activity {

    LinearLayout llEtcCreditBack;
    RadioButton rbEtcCredit6, rbEtcCredit12, rbEtcCredit24;
    String addMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_credit);

        llEtcCreditBack = (LinearLayout) findViewById(R.id.ll_etc_credit_back);
        rbEtcCredit6 = (RadioButton) findViewById(R.id.rb_etc_credit_6);
        rbEtcCredit12 = (RadioButton) findViewById(R.id.rb_etc_credit_12);
        rbEtcCredit24 = (RadioButton) findViewById(R.id.rb_etc_credit_24);

        llEtcCreditBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rbEtcCredit6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rbEtcCredit12.setChecked(false);
                rbEtcCredit24.setChecked(false);
                addMonth = "6";
            }
        });

        rbEtcCredit12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rbEtcCredit6.setChecked(false);
                rbEtcCredit24.setChecked(false);
                addMonth = "12";
            }
        });

        rbEtcCredit24.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rbEtcCredit6.setChecked(false);
                rbEtcCredit12.setChecked(false);
                addMonth = "24";
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreditActivity.this, BackPressed.class);
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