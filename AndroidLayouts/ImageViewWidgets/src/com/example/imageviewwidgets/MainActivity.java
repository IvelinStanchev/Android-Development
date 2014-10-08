package com.example.imageviewwidgets;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	ImageView bull;
	ImageView cow;
	ImageView camel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bull = (ImageView) findViewById(R.id.bull);
		cow = (ImageView) findViewById(R.id.cow);
		camel = (ImageView) findViewById(R.id.camel);

		bull.setOnClickListener(this);
		cow.setOnClickListener(this);
		camel.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		Dialog dialog = new Dialog(this);
		if (v.getId() == R.id.bull) {
			dialog.setTitle(bull.getContentDescription());
		}
		else if (v.getId() == R.id.cow) {
			dialog.setTitle(cow.getContentDescription());
		}
		else if (v.getId() == R.id.camel) {
			dialog.setTitle(camel.getContentDescription());
		}
		
		dialog.show();
	}
}
