package com.survivala.numberguess;

import java.util.List;
import java.util.Random;

import com.appnext.appnextsdk.Appnext;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThreePlayersMain extends Activity implements OnClickListener{

	static Random random = new Random();
	EditText player1, player2, player3;
	TextView tvPlayer1, tvPlayer2, tvPlayer3, aboveMessage;
	static int generated;
	final Context context = this;
	MediaPlayer mp, otherClassSound;
	Button goo;
	Vibrator vibrator;
	long[] pattern = { 0, 500, 500 };
	Appnext next1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.threeplayersmain);
		
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		aboveMessage = (TextView) findViewById(R.id.tvThreePlayers);
		
		//inputs
		player1 = (EditText) findViewById(R.id.etPlayer1ThreePlayers);
		player2 = (EditText) findViewById(R.id.etPlayer2ThreePlayers);
		player3 = (EditText) findViewById(R.id.etPlayer3ThreePlayers);
		
		//names
		tvPlayer1 = (TextView) findViewById(R.id.tvPlayer1ThreePlayers);
		tvPlayer2 = (TextView) findViewById(R.id.tvPlayer2ThreePlayers);
		tvPlayer3 = (TextView) findViewById(R.id.tvPlayer3ThreePlayers);
		
		goo = (Button) findViewById(R.id.bSeeResultThreePlayers);
		goo.setOnClickListener(this);
		
		otherClassSound = MainActivity.bgSound;
		mp = MediaPlayer.create(ThreePlayersMain.this, R.raw.congratulations);
		
		tvPlayer1.setText(ChoosingNamesThreePlayers.player1ThreePlayers.getText().toString());
		tvPlayer2.setText(ChoosingNamesThreePlayers.player2ThreePlayers.getText().toString());
		tvPlayer3.setText(ChoosingNamesThreePlayers.player3ThreePlayers.getText().toString());
		
		aboveMessage.setText(ChoosingNamesThreePlayers.player1ThreePlayers.getText().toString() + " must type a number and\n" + ChoosingNamesThreePlayers.player2ThreePlayers.getText().toString() + ", " + ChoosingNamesThreePlayers.player3ThreePlayers.getText().toString() + " should guess it!");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSeeResultThreePlayers:
			String players1inputAsString = player1.getText().toString();
			String players2inputAsString = player2.getText().toString();
			String players3inputAsString = player3.getText().toString();
			if (!players1inputAsString.matches("") && !players2inputAsString.matches("") && !players3inputAsString.matches("")){
				
				int players1Number = Integer.parseInt(player1.getText().toString());
				int players2Number = Integer.parseInt(player2.getText().toString());
				int players3Number = Integer.parseInt(player3.getText().toString());
				
				generated = players1Number;
				
				int player2substracted = generated - players2Number;
				int player3substracted = generated - players3Number;
				
				if (generated < 0){
					generated *= (-1);
				}
				
				if (player2substracted < 0){
					player2substracted *= (-1);
				}
				
				if (player3substracted < 0){
					player3substracted *= (-1);
				}
				
				if (player2substracted < player3substracted){
					//player2 wins
					final Dialog dialog = new Dialog(context);
					dialog.setContentView(R.layout.congratulations);
					dialog.setTitle("CONGRATULATIONS");
					
					vibrator.vibrate(pattern, 0);
					
					Thread t = new Thread(){
						public void run(){
							try{
								sleep(7000);
							}
							catch(InterruptedException e){
								e.printStackTrace();
							}
							finally{
								vibrator.cancel();
							}
						}
					};
					t.start();
					
					MainActivity.bgSound.setVolume(0, 0);
					mp.start();
					// set the custom dialog components - text, image and button
		 
					Button tryAgain = (Button) dialog.findViewById(R.id.bTryAgain);
					TextView message = (TextView) dialog.findViewById(R.id.tvResult);
					tryAgain.setOnClickListener(this);
					
					message.setText(tvPlayer2.getText() + " with his number " + players2Number + "\nis closer to " + generated + "!");
					
					//if button is clicked, close the custom dialog
					tryAgain.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							mp.release();
							vibrator.cancel();
							MainActivity.bgSound.setVolume(1, 1);
							finish();
							Intent intent = new Intent(ThreePlayersMain.this, ThreePlayersMain.class);
							startActivityForResult(intent, 0);
						}
					});
		 
					dialog.show();
				}
				else if (player2substracted > player3substracted){
					//player3 wins
					final Dialog dialog = new Dialog(context);
					dialog.setContentView(R.layout.congratulations);
					dialog.setTitle("CONGRATULATIONS");
					
					vibrator.vibrate(pattern, 0);
					
					Thread t = new Thread(){
						public void run(){
							try{
								sleep(7000);
							}
							catch(InterruptedException e){
								e.printStackTrace();
							}
							finally{
								vibrator.cancel();
							}
						}
					};
					t.start();
					
					MainActivity.bgSound.setVolume(0, 0);
					mp.start();
					// set the custom dialog components - text, image and button
		 
					Button tryAgain = (Button) dialog.findViewById(R.id.bTryAgain);
					TextView message = (TextView) dialog.findViewById(R.id.tvResult);
					tryAgain.setOnClickListener(this);
					
					message.setText(tvPlayer3.getText() + " with his number " + players3Number + "\nis closer to " + generated + "!");
					
					//if button is clicked, close the custom dialog
					tryAgain.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							mp.release();
							vibrator.cancel();
							MainActivity.bgSound.setVolume(1, 1);
							finish();
							Intent intent = new Intent(ThreePlayersMain.this, ThreePlayersMain.class);
							startActivityForResult(intent, 0);
						}
					});
		 
					dialog.show();
				}
				else{
					//draw
					final Dialog dialog = new Dialog(context);
					dialog.setContentView(R.layout.congratulations);
					dialog.setTitle("CONGRATULATIONS");
					
					vibrator.vibrate(pattern, 0);
					
					Thread t = new Thread(){
						public void run(){
							try{
								sleep(7000);
							}
							catch(InterruptedException e){
								e.printStackTrace();
							}
							finally{
								vibrator.cancel();
							}
						}
					};
					t.start();
					
					MainActivity.bgSound.setVolume(0, 0);
					mp.start();
					// set the custom dialog components - text, image and button
		 
					Button tryAgain = (Button) dialog.findViewById(R.id.bTryAgain);
					TextView message = (TextView) dialog.findViewById(R.id.tvResult);
					tryAgain.setOnClickListener(this);
					
					message.setText("DRAW !");
					
					//if button is clicked, close the custom dialog
					tryAgain.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							mp.release();
							vibrator.cancel();
							MainActivity.bgSound.setVolume(1, 1);
							finish();
							Intent intent = new Intent(ThreePlayersMain.this, ThreePlayersMain.class);
							startActivityForResult(intent, 0);
						}
					});
		 
					dialog.show();
				}
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
		Intent intent = new Intent(ThreePlayersMain.this, ChoosingNumberOfPlayers.class);
		startActivityForResult(intent, 0);
		finish();
	}
}
