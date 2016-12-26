package com.ampsoft.sendback.etc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ampsoft.sendback.DrawerActivity;
import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;
import com.ampsoft.sendback.lib.SendbackSharedPreferences;

/**
 * Created by saki on 16. 12. 15..
 */
public class ProfileActivity extends Activity {

    Context mContext;
    LinearLayout llEtcProfileBack;
    TextView tvEtcProfileId, tvEtcProfilePw, tvEtcProfilePhone, tvEtcProfileEmail;
    ImageView ivEtcProfilePw, ivEtcProfilePhone, ivEtcProfileEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_profile);
        mContext = this;

        llEtcProfileBack = (LinearLayout) findViewById(R.id.ll_etc_profile_back);
        tvEtcProfileId = (TextView) findViewById(R.id.tv_etc_profile_id);
        tvEtcProfilePw = (TextView) findViewById(R.id.tv_etc_profile_pw);
        tvEtcProfilePhone = (TextView) findViewById(R.id.tv_etc_profile_phone);
        tvEtcProfileEmail = (TextView) findViewById(R.id.tv_etc_profile_email);
        ivEtcProfilePw = (ImageView) findViewById(R.id.iv_etc_profile_pw);
        ivEtcProfileEmail = (ImageView) findViewById(R.id.iv_etc_profile_email);

        SendbackSharedPreferences pref = new SendbackSharedPreferences(mContext);
        tvEtcProfileId.setText(pref.getString("id"));
        tvEtcProfilePhone.setText(pref.getString("phone"));
        tvEtcProfileEmail.setText(pref.getString("email"));

        llEtcProfileBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivEtcProfilePw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangePwActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivEtcProfileEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChangeEmailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, BackPressed.class);
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
