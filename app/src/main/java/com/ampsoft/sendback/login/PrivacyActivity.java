package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ampsoft.sendback.R;

/**
 * Created by saki on 16. 12. 19..
 */
public class PrivacyActivity  extends Activity {

    Context mContext;

    LinearLayout llPrivacyBack;
    WebView webviewPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        mContext = this;

        webviewPrivacy = (WebView) findViewById(R.id.webview_privacy);
        llPrivacyBack = (LinearLayout) findViewById(R.id.ll_privacy_back);

        webviewPrivacy.getSettings().setJavaScriptEnabled(true);
        webviewPrivacy.loadUrl("http://ampsoft.cafe24.com/sendback/privacy.php");
        webviewPrivacy.setWebViewClient(new WebViewClientClass());

        llPrivacyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}