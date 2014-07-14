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
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ChoosingNumberOfPlayers extends Activity implements OnCheckedChangeListener, OnClickListener{
	
	Button bGo;
	RadioGroup rgPlayers;
	boolean firstChosen, secondChosen, thirdChosen, fourthChosen, fifthChosen;
	String setData;
	int checkChecked = 0;
	Appnext next1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.choosingnumberofplayers);
		
		bGo = (Button) findViewById(R.id.bGo);
		rgPlayers = (RadioGroup) findViewById(R.id.rgPlayers);
		
		bGo.setOnClickListener(this);
		rgPlayers.setOnCheckedChangeListener(this);
		
		firstChosen = true;
		secondChosen = false;
		thirdChosen = false;
		fourthChosen = false;
		fifthChosen = false;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId){
		case R.id.player1:
			firstChosen = true;
			secondChosen = false;
			thirdChosen = false;
			fourthChosen = false;
			fifthChosen = false;
			break;
		case R.id.player2:
			firstChosen = false;
			secondChosen = true;
			thirdChosen = false;
			fourthChosen = false;
			fifthChosen = false;
			break;
		case R.id.player3:
			firstChosen = false;
			secondChosen = false;
			thirdChosen = true;
			fourthChosen = false;
			fifthChosen = false;
			break;
		case R.id.player4:
			firstChosen = false;
			secondChosen = false;
			thirdChosen = false;
			fourthChosen = true;
			fifthChosen = false;
			break;
		case R.id.player5:
			firstChosen = false;
			secondChosen = false;
			thirdChosen = false;
			fourthChosen = false;
			fifthChosen = true;
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bGo:
			if (firstChosen){
				Intent i = new Intent(ChoosingNumberOfPlayers.this, OnePlayerMain.class);
				startActivityForResult(i, 0);
				finish();
			}
			else if (secondChosen){
				Intent i = new Intent(ChoosingNumberOfPlayers.this, ChoosingNamesTwoPlayers.class);
				startActivityForResult(i, 0);
				finish();
			}
			else if (thirdChosen){
				Intent i = new Intent(ChoosingNumberOfPlayers.this, ChoosingNamesThreePlayers.class);
				startActivityForResult(i, 0);
				finish();
			}
			else if (fourthChosen){
				Intent i = new Intent(ChoosingNumberOfPlayers.this, ChoosingNamesFourPlayers.class);
				startActivityForResult(i, 0);
				finish();
			}
			else{
				Intent i = new Intent(ChoosingNumberOfPlayers.this, ChoosingNamesFivePlayers.class);
				startActivityForResult(i, 0);
				finish();
			}
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
		finish();
		//Intent i = new Intent(ChoosingNumberOfPlayers.this, ChoosingNamesFivePlayers.class);
		//startActivityForResult(i, 0);
	}
}
