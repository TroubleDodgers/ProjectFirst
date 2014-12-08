package com.example.pop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MapActivity extends Activity {
	private String url;
	private WebView myWebView;
	
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map);
	    myWebView = (WebView)findViewById(R.id.webView);
	    Bundle gotBasket = getIntent().getExtras();
	    url = "https://maps.yandex.ua/?text=" + gotBasket.getString("addr") + " ส่ๅโ";
		myWebView.loadUrl(url);
	  }
}