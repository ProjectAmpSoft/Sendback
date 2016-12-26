package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;
import com.ampsoft.sendback.proc.ProcessIdSearch;
import com.ampsoft.sendback.proc.ProcessPwSearch;
import com.ampsoft.sendback.proc.ProcessSmsSend;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by saki on 16. 11. 25..
 */
public class PwSearchActivity extends Activity {

    private static boolean sAuth = false;
    private static boolean cAuth = false;

    Context mContext;
    LinearLayout llPwSearchBack;
    TextView tvPwSearchNext, tvPwSearchSmsSend, tvPwSearchSmsConfig;
    EditText etPwSearchId, etPwSearchPhone, etPwSearchAcode;

    String phone, SearchOk;
    String phone_config;
    int iNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_search);
        mContext = this;

        llPwSearchBack = (LinearLayout)findViewById(R.id.ll_pw_search_back);
        tvPwSearchNext = (TextView) findViewById(R.id.tv_pw_search_next);
        etPwSearchId = (EditText) findViewById(R.id.et_pw_search_id);
        etPwSearchPhone = (EditText) findViewById(R.id.et_pw_search_phone);
        etPwSearchAcode = (EditText) findViewById(R.id.et_pw_search_acode);
        tvPwSearchSmsSend = (TextView) findViewById(R.id.tv_pw_search_sms_send);
        tvPwSearchSmsConfig = (TextView) findViewById(R.id.tv_pw_search_sms_config);

        llPwSearchBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PwSearchActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvPwSearchSmsSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etPwSearchPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "휴대폰번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!sAuth) {
                    phone = etPwSearchPhone.getText().toString();
                    iNumber = (int) Math.floor(Math.random() * 1000000) + 100000;
                    if (iNumber > 1000000)
                        iNumber -= 100000;

                    // sending sms.
                    ArrayList<String> params = new ArrayList<String>();
                    Log.d("test01", params.toString());
                    doSmsProcess();
                    tvPwSearchSmsSend.setBackgroundResource(R.drawable.join3_sms_yellow_bg);
                    tvPwSearchSmsSend.setText("확인");
                    tvPwSearchSmsSend.setTextColor(Color.parseColor("#ffffff"));
                    phone_config = phone;
                    sAuth = true;
                } else {
                    return;
                }
            }
        });

        tvPwSearchSmsConfig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etPwSearchAcode.getText().toString().equals(String.valueOf(iNumber))) {
                    Toast toast = Toast.makeText(mContext, "핸드폰인증이 완료되었습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    tvPwSearchSmsConfig.setBackgroundResource(R.drawable.join3_sms_yellow_bg);
                    tvPwSearchSmsConfig.setText("확인");
                    tvPwSearchSmsConfig.setTextColor(Color.parseColor("#ffffff"));
                    cAuth = true;
                } else {
                    Toast toast = Toast.makeText(mContext, "인증번호가 맞지 않습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        tvPwSearchNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!cAuth) {
                    Toast toast = Toast.makeText(mContext, "휴대폰 인증을 해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                ArrayList<String> params = new ArrayList<String>();
                params.clear();
                params.add(etPwSearchId.getText().toString());
                params.add(phone);

                try {
                    SearchOk = new ProcessPwSearch(mContext).execute(params, null, null).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (SearchOk.equals("0")) {
                    Intent intent = new Intent(PwSearchActivity.this, PwChangeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void doSmsProcess() {
        ArrayList<String> params = new ArrayList<String>();
        params.clear();
        params.add(String.valueOf(iNumber));
        params.add(String.valueOf(phone));
        new ProcessSmsSend(mContext).execute(params, null, null);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PwSearchActivity.this, BackPressed.class);
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