package com.android.hbzsb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 1000; // —”≥Ÿ¡˘√Î  
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.splash);  
  

        
//        new Handler().postDelayed(new Runnable() {  
//            public void run() {  
//                Intent mainIntent = new Intent(SplashActivity.this,  
//                		TabSample.class);  
//                SplashActivity.this.startActivity(mainIntent); 
//            	SplashActivity.this.finish();  
//            }  
//  
//        }, SPLASH_DISPLAY_LENGHT);  
        
        
        Thread mSplashThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    synchronized(this){
                        // Wait given period of time or exit on touch
                        sleep(SPLASH_DISPLAY_LENGHT);
                    }
                }
                catch(InterruptedException ex)
                {                    
                }
                finally 
                {
                    // Run next activity
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, TabSample.class);
                    startActivity(intent);
                    SplashActivity.this.finish();  
   
                }
            }
        };

        mSplashThread.start(); 
  
    }  
}
