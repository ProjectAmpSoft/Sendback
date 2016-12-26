package com.ampsoft.sendback.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.ampsoft.sendback.R;

/**
 * Created by saki on 16. 12. 18..
 */
public class AgreementActivity  extends Activity {

    Context mContext;

    LinearLayout llAgreementBack;
    WebView webviewAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        mContext = this;

        webviewAgreement = (WebView) findViewById(R.id.webview_agreement);
        llAgreementBack = (LinearLayout) findViewById(R.id.ll_agreement_back);

        webviewAgreement.getSettings().setJavaScriptEnabled(true);
        webviewAgreement.loadUrl("http://ampsoft.cafe24.com/sendback/agreement.php");
        webviewAgreement.setWebViewClient(new WebViewClientClass());

        llAgreementBack.setOnClickListener(new View.OnClickListener() {
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