package com.example.pop;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class OrderActivity extends Activity {
	private String url;
	private WebView myWebView;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map);
	    myWebView = (WebView)findViewById(R.id.webView);
	    Bundle gotBasket = getIntent().getExtras();
	    url = gotBasket.getString("addr");
	    myWebView.getSettings().setJavaScriptEnabled(true);
	    myWebView.getSettings().setUseWideViewPort( true );
	    myWebView.getSettings().setLoadWithOverviewMode( true );
		myWebView.loadUrl(url);
	  }
}