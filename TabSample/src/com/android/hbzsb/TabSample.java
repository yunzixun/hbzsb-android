package com.android.hbzsb;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Adil Soomro
 *
 */
public class TabSample extends TabActivity{
	/** Called when the activity is first created. */
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setTabs() ;
		
		TabHost tabHost = getTabHost();
 
		
	    //click on seleccted tab
	    int numberOfTabs = tabHost.getTabWidget().getChildCount();
	    for(int t=0; t<numberOfTabs; t++){
	        tabHost.getTabWidget().getChildAt(t).setOnTouchListener(new View.OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent event) {
	                if(event.getAction()==MotionEvent.ACTION_UP){

	                    String currentSelectedTag = TabSample.this.getTabHost().getCurrentTabTag();
	                    String currentTag = (String)v.getTag();
	                    Log.d(this.getClass().getSimpleName(), "currentSelectedTag: " + currentSelectedTag + " currentTag: " + currentTag);
	                    if(currentSelectedTag.equalsIgnoreCase(currentTag)){
	                    	TabSample.this.getTabHost().setCurrentTabByTag(currentTag);
	                        String newSelectedTabTag = TabSample.this.getTabHost().getCurrentTabTag();
  
	                        int i = getTabHost().getCurrentTab();

	                        if (i == 0) {
                                Intent intent = new Intent();
                                intent.setAction("com.android.first");
                                TabSample.this.sendBroadcast(intent);
	                        }
	                        else if (i ==1) {
                                Intent intent = new Intent();
                                intent.setAction("com.android.second");
                                TabSample.this.sendBroadcast(intent);
	                        }
	                        else if (i ==2) {
                                Intent intent = new Intent();
                                intent.setAction("com.android.third");
                                TabSample.this.sendBroadcast(intent);
	                        }
	                        else
	                        {
	                        	Toast toast=Toast.makeText(getApplicationContext(), "tab", Toast.LENGTH_SHORT);  
	                            toast.show();  
	                        }
	                        
 
                        	return true;
	                    }
	                }
	                return false;
	            }
	            
	        });
	    }
		
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener()  
        {  
            public void onTabChanged(String tabId)  
            {  
            	
//                int i = getTabHost().getCurrentTab();
//
//                if (i == 0) {
//                	WebView wv = (WebView) findViewById(R.id.wv1);
//                    wv.loadUrl("http://www.lingsky.com/projects/mobile/hbzsb/1.html");
//                }
//                else if (i ==1) {
//                	WebView wv = (WebView) findViewById(R.id.wv2);
//                    wv.loadUrl("http://www.lingsky.com/projects/mobile/hbzsb/2.html");
//                }
//                else if (i ==2) {
//                	WebView wv = (WebView) findViewById(R.id.wv3);
//                    wv.loadUrl("http://bbs.hbzsb.cn/home.php?mod=space&do=notice&mobile=2");
//                }
//
//                else
//                {
//                	Toast toast=Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT);  
//                    toast.show();  
//                }
                
            }  
            
            
        });  
	}
	private void setTabs()
	{
		addTab("主页", R.drawable.tab_home, ArrowsActivity.class);
		addTab("论坛", R.drawable.tab_forum, OptionsActivity.class);
		addTab("会员", R.drawable.tab_profile, ProfilesActivity.class);
	}
	
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec(labelId);	
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		tabIndicator.setTag(labelId);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
		

	}
}