package com.example.game_.other01_app.Utility;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.game_.other01_app.Activities.DashboardActivity;

/* A class to support callbacks from YouTube though the iframe API in a local HTML file */
public class YouTubeJSLink {
    private static YouTubeTimer callerLink;

    public YouTubeJSLink(WebView myWebView, YouTubeTimer caller){
        callerLink = caller;
        Log.d("YouTubeJSLink", "Link established (caller = "+(caller!=null)+")");
    }

    @JavascriptInterface
    public void youtubetimeset(String time){
        Log.d("YOUTUBELINK","call back "+time);
        try{
            float timeF  = Float.parseFloat(time);
            callerLink.timeinfo(timeF);
        } catch (Exception e){
            Log.e("YOUTUBELINK", "parse failed "+e+" -- "+e.getMessage());
        }
    }


    public interface YouTubeTimer {
        public void timeinfo(float time);
    }
}
