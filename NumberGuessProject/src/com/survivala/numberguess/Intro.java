package com.survivala.numberguess;

import java.util.List;

import com.appnext.appnextsdk.Appnext;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

public class Intro extends Activity{

	MediaPlayer mySong;
	Appnext next1;
	
	@Override
	protected void onCreate(Bundle variable) {
		// TODO Auto-generated method stub
		super.onCreate(variable);
		
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.intro);
		
		next1 = new Appnext(this);
		next1.setAppID("92d2db20-d1c6-4c55-b220-cf7964f34274");
		
		mySong = MediaPlayer.create(Intro.this, R.raw.introsong);
		mySong.start();
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(7000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent openMainActivity = new Intent("com.survivala.numberguess.MAINPAGE");
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
		System.exit(0);
	}
}
