package com.integrate.prominent;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class myViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView v, String url){
		v.loadUrl(url);
		return true;
	}
}
