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
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FivePlayersMain extends Activity implements OnClickListener{

	static Random random = new Random();
	EditText player1, player2, player3, player4, player5;
	TextView tvPlayer1, tvPlayer2, tvPlayer3, tvPlayer4, tvPlayer5, aboveMessageFivePlayers;
	static int generated;
	final Context context = this;
	MediaPlayer mp, otherClassSound;
	Button goFivePlayers;
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
		
		setContentView(R.layout.fiveplayersmain);
		
		if(random.nextInt(3) == 1){
			next1.showBubble();
		}
		
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		aboveMessageFivePlayers = (TextView) findViewById(R.id.tvMessageFivePlayers);
		
		//inputs
		player1 = (EditText) findViewById(R.id.etPlayer1FivePlayers);
		player2 = (EditText) findViewById(R.id.etPlayer2FivePlayers);
		player3 = (EditText) findViewById(R.id.etPlayer3FivePlayers);
		player4 = (EditText) findViewById(R.id.etPlayer4FivePlayers);
		player5 = (EditText) findViewById(R.id.etPlayer5FivePlayers);
		
		//names
		tvPlayer1 = (TextView) findViewById(R.id.tvPlayer1FivePlayers);
		tvPlayer2 = (TextView) findViewById(R.id.tvPlayer2FivePlayers);
		tvPlayer3 = (TextView) findViewById(R.id.tvPlayer3FivePlayers);
		tvPlayer4 = (TextView) findViewById(R.id.tvPlayer4FivePlayers);
		tvPlayer5 = (TextView) findViewById(R.id.tvPlayer5FivePlayers);
		
		goFivePlayers = (Button) findViewById(R.id.bSeeResultsFivePlayerss);
		goFivePlayers.setOnClickListener(this);
		
		otherClassSound = MainActivity.bgSound;
		mp = MediaPlayer.create(FivePlayersMain.this, R.raw.congratulations);
		
		tvPlayer1.setText(ChoosingNamesFivePlayers.player1FivePlayers.getText().toString());
		tvPlayer2.setText(ChoosingNamesFivePlayers.player2FivePlayers.getText().toString());
		tvPlayer3.setText(ChoosingNamesFivePlayers.player3FivePlayers.getText().toString());
		tvPlayer4.setText(ChoosingNamesFivePlayers.player4FivePlayers.getText().toString());
		tvPlayer5.setText(ChoosingNamesFivePlayers.player5FivePlayers.getText().toString());
		
		aboveMessageFivePlayers.setText(ChoosingNamesFivePlayers.player1FivePlayers.getText().toString() + " must type a number and\n" + ChoosingNamesFivePlayers.player2FivePlayers.getText().toString() + ", " + ChoosingNamesFivePlayers.player3FivePlayers.getText().toString() + ", \n" + ChoosingNamesFivePlayers.player4FivePlayers.getText().toString() + ", " + ChoosingNamesFivePlayers.player5FivePlayers.getText().toString() + " should guess it!");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSeeResultsFivePlayerss:
			String players1inputAsString = player1.getText().toString();
			String players2inputAsString = player2.getText().toString();
			String players3inputAsString = player3.getText().toString();
			String players4inputAsString = player4.getText().toString();
			String players5inputAsString = player5.getText().toString();
			if (!players1inputAsString.matches("") && !players2inputAsString.matches("")
					&& !players3inputAsString.matches("") && !players4inputAsString.matches("")
					&& !players5inputAsString.matches("")){
				
				int players1Number = Integer.parseInt(player1.getText().toString());
				int players2Number = Integer.parseInt(player2.getText().toString());
				int players3Number = Integer.parseInt(player3.getText().toString());
				int players4Number = Integer.parseInt(player4.getText().toString());
				int players5Number = Integer.parseInt(player5.getText().toString());
				
				generated = players1Number;
				
				int player2substracted = generated - players2Number;
				int player3substracted = generated - players3Number;
				int player4substracted = generated - players4Number;
				int player5substracted = generated - players5Number;
				
				if (generated < 0){
					generated *= (-1);
				}
				
				if (player2substracted < 0){
					player2substracted *= (-1);
				}
				
				if (player3substracted < 0){
					player3substracted *= (-1);
				}
				
				if (player4substracted < 0){
					player4substracted *= (-1);
				}
				
				if (player5substracted < 0){
					player5substracted *= (-1);
				}
				
				
				if ((player2substracted < player3substracted) && 
						(player2substracted < player4substracted) &&
						(player2substracted < player5substracted)){
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
							Intent intent = new Intent(FivePlayersMain.this, FivePlayersMain.class);
							startActivityForResult(intent, 0);
						}
					});
		 
					dialog.show();
				}
				else if ((player3substracted < player2substracted) &&
						(player3substracted < player4substracted) &&
						(player3substracted < player5substracted)){
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
							Intent intent = new Intent(FivePlayersMain.this, FivePlayersMain.class);
							startActivityForResult(intent, 0);
						}
					});
		 
					dialog.show();
				}
				else if ((player4substracted < player2substracted) &&
						(player4substracted < player3substracted) &&
						(player4substracted < player5substracted)){
					//player4 wins
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
					
					message.setText(tvPlayer4.getText() + " with his number " + players4Number + "\nis closer to " + generated + "!");
					
					//if button is clicked, close the custom dialog
					tryAgain.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							mp.release();
							vibrator.cancel();
							MainActivity.bgSound.setVolume(1, 1);
							finish();
							Intent intent = new Intent(FivePlayersMain.this, FivePlayersMain.class);
							startActivityForResult(intent, 0);
						}
					});
		 
					dialog.show();
				}
				else if ((player5substracted < player2substracted) &&
						(player5substracted < player3substracted) &&
						(player5substracted < player4substracted)){
					//player5 wins
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
					
					message.setText(tvPlayer5.getText() + " with his number " + players5Number + "\nis closer to " + generated + "!");
					
					//if button is clicked, close the custom dialog
					tryAgain.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							mp.release();
							vibrator.cancel();
							MainActivity.bgSound.setVolume(1, 1);
							finish();
							Intent intent = new Intent(FivePlayersMain.this, FivePlayersMain.class);
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
							Intent intent = new Intent(FivePlayersMain.this, FivePlayersMain.class);
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
		Intent intent = new Intent(FivePlayersMain.this, ChoosingNumberOfPlayers.class);
		startActivityForResult(intent, 0);
		finish();
	}
}
