package com.ampsoft.sendback.etc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;
import com.ampsoft.sendback.lib.SendbackSharedPreferences;
import com.ampsoft.sendback.proc.ProcessChangeEmail;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by saki on 16. 12. 16..
 */
public class ChangeEmailActivity extends Activity {

    Context mContext;

    LinearLayout llEtcChangeEmailBack;

    EditText etEtcChangeEmailEmail;
    TextView tvEtcChangeEmailBtn;

    String ChangeOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        mContext = this;

        llEtcChangeEmailBack = (LinearLayout) findViewById(R.id.ll_etc_change_email_back);

        etEtcChangeEmailEmail = (EditText) findViewById(R.id.et_etc_change_email_email);
        tvEtcChangeEmailBtn = (TextView) findViewById(R.id.tv_etc_change_email_btn);

        llEtcChangeEmailBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeEmailActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvEtcChangeEmailBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (validateEmail(etEtcChangeEmailEmail.getText().toString()) == false) {
                    Toast toast = Toast.makeText(mContext, "E_Mail을 확인해주세요.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                } else {
                    doProcess();
                    if (ChangeOk.equals("0")) {
                        Intent intent = new Intent(ChangeEmailActivity.this, ChangeEmailOkActivity.class);
                        startActivity(intent);
                        finish();
                    }
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
        SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);

        params.clear();
        params.add(pref.getString("id"));
        params.add(etEtcChangeEmailEmail.getText().toString());

        try {
            ChangeOk = new ProcessChangeEmail(mContext).execute(params, null, null).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChangeEmailActivity.this, BackPressed.class);
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
