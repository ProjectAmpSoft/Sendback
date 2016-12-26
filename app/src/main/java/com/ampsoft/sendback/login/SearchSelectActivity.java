package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ampsoft.sendback.R;

/**
 * Created by saki on 16. 11. 24..
 */
public class SearchSelectActivity extends Activity {

    Context mContext;

    LinearLayout llSearchSelectAll;
    TextView tvSearchIdSearch, tvSearchPwSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_select);
        mContext = this;

        llSearchSelectAll = (LinearLayout) findViewById(R.id.ll_search_select_all);
        tvSearchIdSearch = (TextView) findViewById(R.id.tv_search_id_search);
        tvSearchPwSearch = (TextView) findViewById(R.id.tv_search_pw_search);

        llSearchSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("select","0");
                setResult(RESULT_OK,  (intent.setAction("1")));
                finish();
            }
        });
        tvSearchIdSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("select","1");
                setResult(RESULT_OK,  (intent.setAction("1")));
                finish();

            }
        });
        tvSearchPwSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("select","2");
                setResult(RESULT_OK,  (intent.setAction("1")));
                finish();
            }
        });

    }
}
