package com.ampsoft.sendback.etc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.ampsoft.sendback.lib.SendbackSharedPreferences;
import com.ampsoft.sendback.proc.ProcessChangePw;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by saki on 16. 12. 16..
 */
public class ChangePwActivity extends Activity {

    Context mContext;

    LinearLayout llEtcChangePwBack;

    EditText etEtcChangePwPw, etEtcChangePwPwc;
    TextView tvEtcChangePwBtn;

    String ChangeOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);
        mContext = this;

        llEtcChangePwBack = (LinearLayout) findViewById(R.id.ll_etc_change_pw_back);

        etEtcChangePwPw = (EditText) findViewById(R.id.et_etc_change_pw_pw);
        etEtcChangePwPwc = (EditText) findViewById(R.id.et_etc_change_pw_pwc);
        tvEtcChangePwBtn = (TextView) findViewById(R.id.tv_etc_change_pw_btn);

        llEtcChangePwBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePwActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvEtcChangePwBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (etEtcChangePwPw.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "비밃번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (validatePassword(etEtcChangePwPw.getText().toString()) == false) {
                    Toast toast = Toast.makeText(mContext, "비밀번호는 8~12자리 입니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if (etEtcChangePwPw.getText().toString().equals(etEtcChangePwPwc.getText().toString())) {
                    doProcess();
                    if (ChangeOk.equals("0")) {
                        Intent intent = new Intent(ChangePwActivity.this, ChangePwOkActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast toast = Toast.makeText(mContext, "비밀번호와 비밀번호 확인이 맞지 않습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

            }
        });

    }

    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{8,12}$"); // 8자리 ~ 12자리까지 가능

    public static boolean validatePassword(String pwStr) {
        Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(pwStr);
        Log.d("test02", "matcher = " + matcher);
        Log.d("test02", "matcher.matches() = " + matcher.matches());
        return matcher.matches();
    }

    public void doProcess() {
        ArrayList<String> params = new ArrayList<String>();
        SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);

        params.clear();
        params.add(pref.getString("id"));
        params.add(etEtcChangePwPw.getText().toString());

        try {
            ChangeOk = new ProcessChangePw(mContext).execute(params, null, null).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChangePwActivity.this, BackPressed.class);
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
