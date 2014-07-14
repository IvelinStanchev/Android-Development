package com.survivala.numberguess;

import java.util.List;

import com.appnext.appnextsdk.Appnext;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener, OnCheckedChangeListener{

	Button playGame, sound, quit;
	public static MediaPlayer bgSound;
	Button button;
	public static CheckBox checkBox;
	final Context context = this;
	
	Appnext next1;
	Appnext next2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		
		playGame = (Button) findViewById(R.id.bPlayGame);
		sound = (Button) findViewById(R.id.bSound);
		quit = (Button) findViewById(R.id.bQuit);
		playGame.setOnClickListener(this);
		sound.setOnClickListener(this);
		quit.setOnClickListener(this);
		
		bgSound = MediaPlayer.create(MainActivity.this, R.raw.background);
		bgSound.start();
		bgSound.setLooping(true); // Set looping
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bPlayGame:
			Intent i = new Intent(MainActivity.this, ChoosingNumberOfPlayers.class);
			startActivityForResult(i, 0);
			break;
		case R.id.bSound:
			// custom dialog
						final Dialog dialog = new Dialog(context);
						dialog.setContentView(R.layout.music);
						dialog.setTitle("Music - On/Off");
			 
						// set the custom dialog components - text, image and button
			 
						Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
						checkBox = (CheckBox) dialog.findViewById(R.id.cbSound);
						if (!bgSound.isPlaying()){
							checkBox.setText("OFF");
							checkBox.setChecked(false);
						}
						checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

						        if(isChecked)
						        {
						        	checkBox.setText("ON");
						        	if (!bgSound.isPlaying()){
							        	bgSound = MediaPlayer.create(MainActivity.this, R.raw.background);
							    		bgSound.start();
							    		bgSound.setLooping(true); // Set looping
						        	}
						        }
						        else
						        {
						        	checkBox.setText("OFF");
						        	bgSound.stop();
						        }
						    }
						});
						
						// if button is clicked, close the custom dialog
						dialogButton.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
			 
						dialog.show();
			break;
		case R.id.bQuit:
			new AlertDialog.Builder(this)
	           .setMessage("Are you sure you want to exit?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   	bgSound.stop();
	                    MainActivity.this.finish();
	               }
	           })
	           .setNegativeButton("No", null)
	           .show();
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}

	@Override
	  protected void onPause() {
	    //if (this.isFinishing()){ //basically BACK was pressed from this activity
	      //bgSound.setVolume(0, 0);
	      //Toast.makeText(MainActivity.this, "YOU PRESSED BACK FROM YOUR 'HOME/MAIN' ACTIVITY", Toast.LENGTH_SHORT).show();
	    //}
	    Context context = getApplicationContext();
	    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
	    if (!taskInfo.isEmpty()) {
	      ComponentName topActivity = taskInfo.get(0).topActivity; 
	      if (!topActivity.getPackageName().equals(context.getPackageName())) {
	        bgSound.setVolume(0, 0);
	        //Toast.makeText(MainActivity.this, "YOU LEFT YOUR APP", Toast.LENGTH_SHORT).show();
	      }
	    }
	    
	    //PROBLEM WITH PRESSING HOME BUTTON
	    
	    /*Context context = getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        if (!taskInfo.isEmpty()) {
          ComponentName topActivity = taskInfo.get(0).topActivity; 
          if (!topActivity.getPackageName().equals(context.getPackageName())) {
            bgSound.setVolume(0, 0);
            Toast.makeText(xYourClassNamex.this, "YOU LEFT YOUR APP", Toast.LENGTH_SHORT).show();
          }
        }*/
	    super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		bgSound.setVolume(1, 1);
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
           .setMessage("Are you sure you want to exit?")
           .setCancelable(false)
           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
            	   	bgSound.stop();
                    MainActivity.this.finish();
               }
           })
           .setNegativeButton("No", null)
           .show();
	}
}
