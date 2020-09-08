package com.example.game_.other01_app.Utility;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.game_.other01_app.Activities.DashboardActivity;
import com.example.game_.other01_app.Activities.QualtricsActivity;

public class QualtricsJSLink {
    QualtricsActivity caller;

    public QualtricsJSLink(WebView myWebView, QualtricsActivity caller){
        this.caller = caller;
        Log.d("QUALTRICSTEST", "Link established");
    }

    @JavascriptInterface
    public void back2android(){
        Log.d("QUALTRICSTEST", "***** Back in Android ******");
        AlertDialog alertDialog = new AlertDialog.Builder(caller).create();
        alertDialog.setTitle("CareFit Surveys");
        alertDialog.setMessage("Thank you for your input.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        caller.runOnUiThread(new Runnable() {// need to run on main UI thread
                            public void run() {
                                Intent k = new Intent(caller, DashboardActivity.class);
                                k.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                caller.startActivity(k);
                            };
                        });
                    }
                });
        alertDialog.show();
    }
}
