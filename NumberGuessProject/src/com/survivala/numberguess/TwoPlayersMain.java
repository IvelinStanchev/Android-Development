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

public class TwoPlayersMain extends Activity implements OnClickListener{

	EditText player1, player2;
	TextView tvPlayer1, tvPlayer2;
	static Random random = new Random();
	static int generated;
	final Context context = this;
	MediaPlayer mp, otherClassSound;
	boolean isPlaying;
	Button go;
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
		
		setContentView(R.layout.twoplayersmain);
		 
		next1 = new Appnext(this);
		next1.setAppID("fc8d7356-1633-4676-8e90-74e565ab7166");
		
		if(random.nextInt(3) == 1){
			next1.showBubble();
		}
		
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		generated = random.nextInt(101);
		player1 = (EditText) findViewById(R.id.etPlayer1Number);
		player2 = (EditText) findViewById(R.id.etPlayer2Number);
		tvPlayer1 = (TextView) findViewById(R.id.tvplayer1);
		tvPlayer2 = (TextView) findViewById(R.id.tvplayer2);
		go = (Button) findViewById(R.id.bSeeResultTwoPlayers);
		go.setOnClickListener(this);
		mp = MediaPlayer.create(TwoPlayersMain.this, R.raw.congratulations);
		tvPlayer1.setText(ChoosingNamesTwoPlayers.player1.getText().toString());
		tvPlayer2.setText(ChoosingNamesTwoPlayers.player2.getText().toString());
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSeeResultTwoPlayers:
			String players1inputAsString = player1.getText().toString();
			String players2inputAsString = player2.getText().toString();
			if (!players1inputAsString.matches("") && !players2inputAsString.matches("")){
				int players1Number = Integer.parseInt(player1.getText().toString());
				int players2Number = Integer.parseInt(player2.getText().toString());
				
				int player1substracted = generated - players1Number;
				int player2substracted = generated - players2Number;
				
				if (player1substracted < 0){
					player1substracted *= (-1);
				}
				
				if (player2substracted < 0){
					player2substracted *= (-1);
				}
				
				if (player1substracted < player2substracted){
					//player1 wins
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
					
					message.setText(tvPlayer1.getText() + " with his number " + players1Number + "\nis closer to " + generated + "!");
					
					//if button is clicked, close the custom dialog
					tryAgain.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							mp.release();
							vibrator.cancel();
							MainActivity.bgSound.setVolume(1, 1);
							finish();
							Intent intent = new Intent(TwoPlayersMain.this, TwoPlayersMain.class);
							startActivityForResult(intent, 0);
						}
					});
		 
					dialog.show();
				}
				else if (player1substracted > player2substracted){
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
							Intent intent = new Intent(TwoPlayersMain.this, TwoPlayersMain.class);
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
							Intent intent = new Intent(TwoPlayersMain.this, TwoPlayersMain.class);
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
		finish();
		Intent intent = new Intent(TwoPlayersMain.this, ChoosingNumberOfPlayers.class);
		startActivityForResult(intent, 0);
	}
}
