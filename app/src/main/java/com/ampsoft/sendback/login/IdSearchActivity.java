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
import com.ampsoft.sendback.proc.ProcessJoin;
import com.ampsoft.sendback.proc.ProcessSmsSend;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by saki on 16. 11. 25..
 */
public class IdSearchActivity extends Activity {

    private static boolean sAuth = false;
    private static boolean cAuth = false;

    Context mContext;
    LinearLayout llIdSearchBack;
    TextView tvIdSearchNext, tvIdSearchSmsSend, tvIdSearchSmsConfig;
    EditText etIdSearchName, etIdSearchPhone, etIdSearchAcode;

    String phone, SearchOk;
    String phone_config;
    int iNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_search);
        mContext = this;

        llIdSearchBack = (LinearLayout) findViewById(R.id.ll_id_search_back);
        tvIdSearchNext = (TextView) findViewById(R.id.tv_id_search_next);
        etIdSearchName = (EditText) findViewById(R.id.et_id_search_name);
        etIdSearchPhone = (EditText) findViewById(R.id.et_id_search_phone);
        etIdSearchAcode = (EditText) findViewById(R.id.et_id_search_acode);
        tvIdSearchSmsSend = (TextView) findViewById(R.id.tv_id_search_sms_send);
        tvIdSearchSmsConfig = (TextView) findViewById(R.id.tv_id_search_sms_config);

        llIdSearchBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdSearchActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvIdSearchSmsSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etIdSearchPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "휴대폰번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!sAuth) {
                    phone = etIdSearchPhone.getText().toString();
                    iNumber = (int) Math.floor(Math.random() * 1000000) + 100000;
                    if (iNumber > 1000000)
                        iNumber -= 100000;

                    // sending sms.
                    ArrayList<String> params = new ArrayList<String>();
                    Log.d("test01", params.toString());
                    doSmsProcess();
                    tvIdSearchSmsSend.setBackgroundResource(R.drawable.join3_sms_yellow_bg);
                    tvIdSearchSmsSend.setText("확인");
                    tvIdSearchSmsSend.setTextColor(Color.parseColor("#ffffff"));
                    phone_config = phone;
                    sAuth = true;
                } else {
                    return;
                }
            }
        });

        tvIdSearchSmsConfig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etIdSearchAcode.getText().toString().equals(String.valueOf(iNumber))) {
                    Toast toast = Toast.makeText(mContext, "핸드폰인증이 완료되었습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    tvIdSearchSmsConfig.setBackgroundResource(R.drawable.join3_sms_yellow_bg);
                    tvIdSearchSmsConfig.setText("확인");
                    tvIdSearchSmsConfig.setTextColor(Color.parseColor("#ffffff"));
                    cAuth = true;
                } else {
                    Toast toast = Toast.makeText(mContext, "인증번호가 맞지 않습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        tvIdSearchNext.setOnClickListener(new View.OnClickListener() {

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
                params.add(etIdSearchName.getText().toString());
                params.add(phone);

                try {
                    SearchOk = new ProcessIdSearch(mContext).execute(params, null, null).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (SearchOk.equals("0")) {
                    Intent intent = new Intent(IdSearchActivity.this, IdSearchOkActivity.class);
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
        Intent intent = new Intent(IdSearchActivity.this, BackPressed.class);
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