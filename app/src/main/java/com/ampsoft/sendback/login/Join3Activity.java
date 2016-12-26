package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;
import com.ampsoft.sendback.proc.ProcessJoin;
import com.ampsoft.sendback.proc.ProcessSmsSend;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by saki on 16. 11. 23..
 */
public class Join3Activity extends Activity {

    private static boolean sAuth = false;
    private static boolean cAuth = false;

    Context mContext;
    private InputMethodManager ipm;
    LinearLayout llJoin3All, llJoin3Back;
    TextView tvJoin3SmsSend, tvJoin3SmsConfig, tvJoin3Next;
    EditText etJoin3Email, etJoin3Phone, etJoin3ACode;

    String id, pw, name;
    String Device, Serial, AndroidId;
    String joinOk;

    String phone;
    String phone_config;
    int iNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join3);
        mContext = this;
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");

        ipm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        llJoin3All = (LinearLayout) findViewById(R.id.ll_Join3_all);

        llJoin3Back = (LinearLayout) findViewById(R.id.ll_join3_back);
        tvJoin3Next = (TextView) findViewById(R.id.tv_join3_next);
        etJoin3Email = (EditText) findViewById(R.id.et_join3_email);
        etJoin3Phone = (EditText) findViewById(R.id.et_join3_phone);
        etJoin3ACode = (EditText) findViewById(R.id.et_join3_acode);
        tvJoin3SmsSend = (TextView) findViewById(R.id.tv_join3_sms_send);
        tvJoin3SmsConfig = (TextView) findViewById(R.id.tv_join3_sms_config);

        TelephonyManager Tmanager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        Device = "" + Tmanager.getDeviceId();
        Serial = "" + Tmanager.getSimSerialNumber();
        AndroidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        Log.d("test02", "Device = " + Device);
        Log.d("test02", "Serial = " + Serial);
        Log.d("test02", "AndroidId = " + AndroidId);

        llJoin3All.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ipm.hideSoftInputFromWindow(llJoin3All.getWindowToken(), 0);

                return false;
            }
        });

        llJoin3Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Join3Activity.this, Join2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tvJoin3SmsSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etJoin3Phone.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "휴대폰번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!sAuth) {
                    phone = etJoin3Phone.getText().toString();
                    iNumber = (int) Math.floor(Math.random() * 1000000) + 100000;
                    if (iNumber > 1000000)
                        iNumber -= 100000;

                    // sending sms.
                    ArrayList<String> params = new ArrayList<String>();
                    Log.d("test01", params.toString());
                    doSmsProcess();
                    tvJoin3SmsSend.setBackgroundResource(R.drawable.join3_sms_yellow_bg);
                    tvJoin3SmsSend.setText("확인");
                    tvJoin3SmsSend.setTextColor(Color.parseColor("#ffffff"));
                    phone_config = phone;
                    sAuth = true;
                } else {
                    return;
                }
            }
        });

        tvJoin3SmsConfig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (etJoin3ACode.getText().toString().equals(String.valueOf(iNumber))) {
                    Toast toast = Toast.makeText(mContext, "핸드폰인증이 완료되었습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    tvJoin3SmsConfig.setBackgroundResource(R.drawable.join3_sms_yellow_bg);
                    tvJoin3SmsConfig.setText("확인");
                    tvJoin3SmsConfig.setTextColor(Color.parseColor("#ffffff"));
                    cAuth = true;
                } else {
                    Toast toast = Toast.makeText(mContext, "인증번호가 맞지 않습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        tvJoin3Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (validateEmail(etJoin3Email.getText().toString()) == false) {
                    Toast toast = Toast.makeText(mContext, "E_Mail을 확인해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                if (!cAuth) {
                    Toast toast = Toast.makeText(mContext, "휴대폰 인증을 해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                doProcess();
                if (joinOk.equals("0")) {
                    Intent intent = new Intent(Join3Activity.this, Join4Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void doProcess() {
        ArrayList<String> params = new ArrayList<String>();

        params.clear();
        params.add(id);
        params.add(pw);
        params.add(name);
        params.add(etJoin3Phone.getText().toString());
        params.add(AndroidId);
        params.add(etJoin3Email.getText().toString());

        try {
            joinOk = new ProcessJoin(mContext).execute(params, null, null).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
        Intent intent = new Intent(Join3Activity.this, BackPressed.class);
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