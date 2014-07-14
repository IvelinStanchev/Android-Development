package com.integrate.prominent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity{
	
	MediaPlayer mySong;
	
	@Override
	protected void onCreate(Bundle PeshosVariable) {
		// TODO Auto-generated method stub
		super.onCreate(PeshosVariable);
		setContentView(R.layout.pesho);
		mySong = MediaPlayer.create(Splash.this, R.raw.intro);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		
		if (music == true){
			mySong.start();
		}
		
		//many activities can be acomplished via threads
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(1000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent openMainActivity = new Intent("com.integrate.prominent.MENU");
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mySong.release();
		finish();
	}
	
	
	
}
