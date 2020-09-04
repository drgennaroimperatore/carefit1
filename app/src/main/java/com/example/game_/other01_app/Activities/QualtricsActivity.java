package com.example.game_.other01_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.game_.other01_app.R;
import com.example.game_.other01_app.Utility.QualtricsJSLink;

public class QualtricsActivity extends AppCompatActivity {

    public static final int WELCOME_SURVEY = 1;
    public static final int END_SURVEY = 2;
    private static String targetUUID = "Testing";//should be URL safe
    private static String targetURL = "https://strathsci.qualtrics.com/jfe/form/SV_1TA816ywxOXF7GR?uuid=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Carefit-QUALTRICSTEST", "Creating");
        setContentView(R.layout.activity_qualtrics);

        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl(targetURL+ targetUUID);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new QualtricsJSLink(myWebView, this), "StrathAndroid");
    }

    public static void setup (int whichSurvey, String uuid){
        if (whichSurvey==WELCOME_SURVEY)
            targetURL = "https://strathsci.qualtrics.com/jfe/form/SV_3JjBPBBTuctl2lL?uuid=";
        else if (whichSurvey==END_SURVEY)
            targetURL = "https://strathsci.qualtrics.com/jfe/form/SV_abM79Zt8WmKaBFz?uuid=";
        else
            Log.e("Carefit-QUALTRICSTEST", "Invalid survey number");
        targetUUID = uuid;
    }
}