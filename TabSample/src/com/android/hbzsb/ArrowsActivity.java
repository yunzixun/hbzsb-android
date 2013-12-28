package com.android.hbzsb;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * @author Adil Soomro
 *
 */
@SuppressLint("SetJavaScriptEnabled")
public class ArrowsActivity extends Activity {
	WebView wv;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrowspage);  
        
        
        wv = (WebView) findViewById(R.id.wv1);
        wv.loadUrl("http://3g.hbzsb.cn");
        wv.setWebViewClient(new WebViewClientListener());
        wv.getSettings().setJavaScriptEnabled(true);  

        
        IntentFilter filter = new IntentFilter("com.android.first");
        registerReceiver(myReceiver, filter);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        try {
        	wv.loadUrl("http://3g.hbzsb.cn");
        } catch (Exception e) {}
    }
    
    private BroadcastReceiver myReceiver = new BroadcastReceiver(){  
    	  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	wv.clearHistory();
        	wv.loadUrl("http://3g.hbzsb.cn");
        }  
          
    }; 
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
        	wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    


    
     
    
    private class WebViewClientListener extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            wv.loadUrl(url);
            return true;
        }
        
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            wv.loadUrl("404");
          	 ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress1);
           	 progressbar.setVisibility(View.INVISIBLE);
        }
        
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
       	 super.onPageStarted(view, url, favicon);
       	 
       	 ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress1);
       	 progressbar.setVisibility(View.VISIBLE);
		}
       
       public void onPageFinished(WebView view, String url) {
       	 super.onPageFinished(view, url);
       	 
       	 ProgressBar progressbar = (ProgressBar) findViewById(R.id.progress1);
       	 progressbar.setVisibility(View.INVISIBLE);
       }
    }
    
    public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
        try {
            webView.stopLoading();
        } catch (Exception e) {
        }
    }
 
}