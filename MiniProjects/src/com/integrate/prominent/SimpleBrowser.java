package com.integrate.prominent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener{

	EditText url;
	WebView myBrow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		
		myBrow = (WebView) findViewById(R.id.wvBrowser);
		
		myBrow.getSettings().setJavaScriptEnabled(true);
		myBrow.getSettings().setLoadWithOverviewMode(true);
		myBrow.getSettings().setUseWideViewPort(true);
		
		myBrow.setWebViewClient(new myViewClient());
		
		try{
			myBrow.loadUrl("http://www.youtube.com");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		Button go = (Button) findViewById(R.id.bGo);
		Button back = (Button) findViewById(R.id.bBack);
		Button refresh = (Button) findViewById(R.id.bRefresh);
		Button forward = (Button) findViewById(R.id.bForward);
		Button clearHistory = (Button) findViewById(R.id.bHistory);
		url = (EditText) findViewById(R.id.etURL);
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bGo:
			String theWebsite = url.getText().toString();
			myBrow.loadUrl(theWebsite);
			//hiding the Keyboard after using an EditText
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			
			break;
		case R.id.bBack:
			if (myBrow.canGoBack()){
				myBrow.goBack();	
			}
			break;
		case R.id.bForward:
			if (myBrow.canGoForward()){
				myBrow.goForward();	
			}
			break;
		case R.id.bRefresh:
			myBrow.reload();
			break;
		case R.id.bHistory:
			myBrow.clearHistory();
			break;
		}
	}
}
