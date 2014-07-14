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

public class ChoosingNamesFivePlayers extends Activity implements OnClickListener{

	public static EditText player1FivePlayers, player2FivePlayers, 
	player3FivePlayers, player4FivePlayers, player5FivePlayers;
	Button goFive;
	Appnext next1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.choosingnamesfiveplayers);
		
		next1 = new Appnext(this);
		next1.setAppID("92d2db20-d1c6-4c55-b220-cf7964f34274");
		
		player1FivePlayers = (EditText) findViewById(R.id.etPlayer1ChoosingFivePlayers);
		player2FivePlayers = (EditText) findViewById(R.id.etPlayer2ChoosingFivePlayers);
		player3FivePlayers = (EditText) findViewById(R.id.etPlayer3ChoosingFivePlayers);
		player4FivePlayers = (EditText) findViewById(R.id.etPlayer4ChoosingFivePlayers);
		player5FivePlayers = (EditText) findViewById(R.id.etPlayer5ChoosingFivePlayers);
		goFive = (Button) findViewById(R.id.bGoFivePlayers);
		goFive.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bGoFivePlayers:
			Intent i = new Intent(ChoosingNamesFivePlayers.this, FivePlayersMain.class);
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
		Intent intent = new Intent(ChoosingNamesFivePlayers.this, ChoosingNumberOfPlayers.class);
		startActivityForResult(intent, 0);
		finish();
	}
}
