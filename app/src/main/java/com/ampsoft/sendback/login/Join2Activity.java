package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by saki on 16. 11. 22..
 */
public class Join2Activity extends Activity {

    Context mContext;
    private InputMethodManager ipm;
    LinearLayout llJoin2All, llJoin2Back;
    TextView tvJoin2Next;
    EditText etJoin2Id, etJoin2Pw, etJoin2Pwc;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join2);
        mContext = this;
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        ipm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        llJoin2All = (LinearLayout) findViewById(R.id.ll_join2_all);

        llJoin2Back = (LinearLayout)findViewById(R.id.ll_join2_back);
        tvJoin2Next = (TextView) findViewById(R.id.tv_join2_next);
        etJoin2Id = (EditText) findViewById(R.id.et_join2_id);
        etJoin2Pw = (EditText) findViewById(R.id.et_join2_pw);
        etJoin2Pwc = (EditText) findViewById(R.id.et_join2_pwc);

        llJoin2All.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ipm.hideSoftInputFromWindow(llJoin2All.getWindowToken(), 0);

                return false;
            }
        });


        llJoin2Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Join2Activity.this, Join1Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tvJoin2Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (validateId(etJoin2Id.getText().toString()) == false) {
                    Toast toast = Toast.makeText(mContext, "ID는 8~12자리 입니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if (validatePassword(etJoin2Pw.getText().toString()) == false) {
                    Toast toast = Toast.makeText(mContext, "비밀번호는 8~12자리 입니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                if (!etJoin2Pw.getText().toString().equals(etJoin2Pwc.getText().toString())) {
                    Toast toast = Toast.makeText(mContext, "비밀번호와 비밀번호 확인이 맞지않습니다.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                Intent intent = new Intent(Join2Activity.this, Join3Activity.class);
                intent.putExtra("name", name);
                intent.putExtra("id", etJoin2Id.getText().toString());
                intent.putExtra("pw", etJoin2Pw.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }

    public static final Pattern VALID_ID_REGEX_ALPHA_NUM = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{8,12}$"); // 8자리 ~ 12자리까지 가능

    public static boolean validateId(String idStr) {
        Matcher matcher = VALID_ID_REGEX_ALPHA_NUM.matcher(idStr);
        Log.d("test02", "matcher = " + matcher);
        Log.d("test02", "matcher.matches() = " + matcher.matches());
        return matcher.matches();
    }

    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{8,12}$"); // 8자리 ~ 12자리까지 가능

    public static boolean validatePassword(String pwStr) {
        Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(pwStr);
        Log.d("test02", "matcher = " + matcher);
        Log.d("test02", "matcher.matches() = " + matcher.matches());
        return matcher.matches();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Join2Activity.this, BackPressed.class);
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