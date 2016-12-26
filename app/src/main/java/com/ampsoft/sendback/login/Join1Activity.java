package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;

/**
 * Created by saki on 16. 11. 13..
 */
public class Join1Activity extends Activity {
    Context mContext;
    private InputMethodManager ipm;
    LinearLayout llJoin1All, llJoin1Back;
    TextView tvJoin1Next, tvAgreementView, tvPrivacyView;
    EditText etJoin1Name;
    CheckBox cbJoin1Agreement1, cbJoin1Agreement2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join1);
        mContext = this;

        ipm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        llJoin1All = (LinearLayout) findViewById(R.id.ll_join1_all);

        llJoin1Back = (LinearLayout) findViewById(R.id.ll_join1_back);
        tvJoin1Next = (TextView) findViewById(R.id.tv_join1_next);
        etJoin1Name = (EditText) findViewById(R.id.et_join1_name);
        cbJoin1Agreement1 = (CheckBox) findViewById(R.id.cb_join1_agreement1);
        cbJoin1Agreement2 = (CheckBox) findViewById(R.id.cb_join1_agreement2);
        tvAgreementView = (TextView) findViewById(R.id.tv_agreement_view);
        tvPrivacyView = (TextView) findViewById(R.id.tv_privacy_view);

        llJoin1All.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ipm.hideSoftInputFromWindow(llJoin1All.getWindowToken(), 0);

                return false;
            }
        });

        llJoin1Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Join1Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvAgreementView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Join1Activity.this, AgreementActivity.class);
                startActivity(intent);
            }
        });

        tvPrivacyView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Join1Activity.this, PrivacyActivity.class);
                startActivity(intent);
            }
        });

        tvJoin1Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbJoin1Agreement1.isChecked() == false) {
                    Toast toast = Toast.makeText(mContext, "서비스 이용약관을 동의해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if (cbJoin1Agreement2.isChecked() == false) {
                    Toast toast = Toast.makeText(mContext, "개인정보 수집 및 이용을 동의해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if (etJoin1Name.getText().toString().length() < 2 || etJoin1Name.getText().toString().length() > 6) {
                    Toast toast = Toast.makeText(mContext, "이름을 확인해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                Intent intent = new Intent(Join1Activity.this, Join2Activity.class);
                intent.putExtra("name", etJoin1Name.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Join1Activity.this, BackPressed.class);
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
