package com.survivala.numberguess;

import java.util.List;

import com.appnext.appnextsdk.Appnext;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ChoosingNamesFourPlayers extends Activity implements OnClickListener{

	public static EditText player1FourPlayers, player2FourPlayers, 
						player3FourPlayers, player4FourPlayers;
	Button goFour;
	Appnext next1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.choosingnamesfourplayers);
		
		player1FourPlayers = (EditText) findViewById(R.id.etPlayer1ChoosingFourPlayers);
		player2FourPlayers = (EditText) findViewById(R.id.etPlayer2ChoosingFourPlayers);
		player3FourPlayers = (EditText) findViewById(R.id.etPlayer3ChoosingFourPlayers);
		player4FourPlayers = (EditText) findViewById(R.id.etPlayer4ChoosingFourPlayers);
		goFour = (Button) findViewById(R.id.bGoFourPlayers);
		goFour.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bGoFourPlayers:
			Intent i = new Intent(ChoosingNamesFourPlayers.this, FourPlayersMain.class);
			startActivityForResult(i, 0);
			finish();
			break;
		}
	}
	
	@Override
	  protected void onPause() {
	    Context context = getApplicationContext();
	    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
	    if (!taskInfo.isEmpty()) {
	      ComponentName topActivity = taskInfo.get(0).topActivity; 
	      if (!topActivity.getPackageName().equals(context.getPackageName())) {
	        MainActivity.bgSound.setVolume(0, 0);
	      }
	    }
	    super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MainActivity.bgSound.setVolume(1, 1);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(ChoosingNamesFourPlayers.this, ChoosingNumberOfPlayers.class);
		startActivityForResult(intent, 0);
		finish();
	}
}
