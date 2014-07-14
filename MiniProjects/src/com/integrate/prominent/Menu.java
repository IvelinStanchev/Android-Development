package com.integrate.prominent;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{

	String classes[] = { "MainActivity", "TextPlay", "Email", "Camera",
						 "Data", "GFX", "GFXSurface", "SoundStuff",
						 "Slider", "Tabs", "SimpleBrowser", "Flipper",
						 "SharedPrefs", "InternalData", "ExternalData",
						 "SQLiteExample", "Accelerate", "HttpExample",
						 "GLExample", "GLCubeRenderer", "Voice",
						 "StatusBar", "SeekBarVolume"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		try{
			String cheese = classes[position];
			Class myClass = Class.forName("com.integrate.prominent." + cheese);
			Intent myIntent = new Intent(Menu.this, myClass);
			startActivity(myIntent);
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.main, menu);
		
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.aboutMe:
			Intent i = new Intent("com.integrate.prominent.ABOUT");
			startActivity(i);//-->Manifest --> adding theme
			break;
		case R.id.preferences:
			Intent p = new Intent("com.integrate.prominent.PREFS");
			startActivity(p);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}
}
