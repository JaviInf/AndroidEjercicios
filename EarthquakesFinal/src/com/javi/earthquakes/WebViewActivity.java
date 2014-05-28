package com.javi.earthquakes;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.os.Build;

public class WebViewActivity extends Activity {

	private WebView browser;
	 private ProgressBar progressBar;
	 private String link;
	 
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
         link=getIntent().getStringExtra("URL");
        browser = (WebView)findViewById(R.id.webView1);
         
                //habilitamos javascript y el zoom
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setBuiltInZoomControls(true);
                 
                //habilitamos los plugins (flash)
        browser.getSettings().setPluginsEnabled(true);
         
                browser.loadUrl(link);
         
        browser.setWebViewClient(new WebViewClient()
        {
                        // evita que los enlaces se abran fuera nuestra app en el navegador de android
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return false;
            }   
             
        });
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        
        browser.setWebChromeClient(new WebChromeClient() 
        {
            public void onProgressChanged(WebView view, int progress) 
            {               
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                WebViewActivity.this.setProgress(progress * 1000);
 
                progressBar.incrementProgressBy(progress);
 
                if(progress == 100)
                {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    } 
    
    public void onReceivedTitle(WebView view, String title)
    {
        WebViewActivity.this.setTitle("WebView demo: " + WebViewActivity.this.browser.getTitle());
 
}
    }