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
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class OnePlayerMain extends Activity implements OnClickListener{

	static String lastValue;
	EditText input;
	static Random random = new Random();
	static int generated;
	static int parsedInput;
	Button b;
	ImageButton bUp, bDown;
	final Context context = this;
	MediaPlayer mp, otherClassSound;
	AnimationDrawable frameAnimationUp, frameAnimationDown;
	int tries, counter;
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
		
		setContentView(R.layout.oneplayermain);
		
		next1 = new Appnext(this);
		next1.setAppID("fc8d7356-1633-4676-8e90-74e565ab7166");
		
		if(random.nextInt(2) == 1){
			next1.showBubble();
		}
		
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		generated = random.nextInt(10000);
		otherClassSound = MainActivity.bgSound;
		input = (EditText) findViewById(R.id.etInput);
		b = (Button) findViewById(R.id.bTry);
		bUp = (ImageButton) findViewById(R.id.bUp);
		bDown = (ImageButton) findViewById(R.id.bDown);
		b.setOnClickListener(this);
		tries = 1;
		counter = 0;
		
		mp = MediaPlayer.create(OnePlayerMain.this, R.raw.congratulations);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bTry:
			
			//String inputAsString1 = inputAsString2;
			String inputAsString2 = input.getText().toString();
			
			
				if (!inputAsString2.matches("")){
					parsedInput = Integer.parseInt(input.getText().toString());
					
					
					if(counter == 0){
						lastValue = input.getText().toString();
						counter++;
					}
					
					if (!inputAsString2.equals(lastValue)){
						tries++;
						lastValue = inputAsString2;
					}
					
					if (parsedInput > generated){
						bDown.setVisibility(1);
						bUp.setVisibility(View.INVISIBLE);
	
						bDown.setImageResource(R.drawable.downbuttonanimation);
						frameAnimationDown =  (AnimationDrawable) bDown.getDrawable();
						             
						  if (frameAnimationDown.isRunning()) {
							  frameAnimationDown.stop();
						  }
						  else {
							  frameAnimationDown.stop();
							  frameAnimationDown.start();
						  }
						
						Thread t = new Thread(){
							public void run(){
								try{
									sleep(4000);
								}
								catch(InterruptedException e){
									e.printStackTrace();
								}
								finally{
									frameAnimationDown.stop();
								}
							}
						};
						t.start();
					}
					else if (parsedInput < generated){
						bUp.setVisibility(1);
						bDown.setVisibility(View.INVISIBLE);
						
						bUp.setImageResource(R.drawable.upbuttonanimation);
						frameAnimationUp =  (AnimationDrawable) bUp.getDrawable();
						             
						  if (frameAnimationUp.isRunning()) {
							  frameAnimationUp.stop();
						  }
						  else {
							  frameAnimationUp.stop();
							  frameAnimationUp.start();
						  }
						
						Thread t = new Thread(){
							public void run(){
								try{
									sleep(4000);
								}
								catch(InterruptedException e){
									e.printStackTrace();
								}
								finally{
									frameAnimationUp.stop();
								}
							}
						};
						t.start();
					}
					else{
						
						//Action for guessed number
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
						
						if (tries == 1){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "You are the luckiest human!");
						}
						else if (tries == 2){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "You must play ToTo!");
						}
						else if (tries == 3){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "You are a fortune teller!");
						}
						else if (tries == 4){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Just Wonderful!");
						}
						else if (tries == 5){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Just AWESOME!");
						}
						else if (tries == 6){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Very, very, very GOOD!");
						}
						else if (tries == 7){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "AWESOME!");
						}
						else if (tries == 8){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "You are Master!");
						}
						else if (tries == 9){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "You are Ninja!");
						}
						else if (tries == 10){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "PERFECT!");
						}
						else if (tries == 11){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Very high result, dude!");
						}
						else if (tries == 12){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "High result, dude!");
						}
						else if (tries == 13){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "You have beaten most of the people!");
						}
						else if (tries == 14){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Above the average, but not enough!");
						}
						else if (tries == 15){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Average Result...!");
						}
						else if (tries == 16){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Come on...You are better!");
						}
						else if (tries == 17){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "That's under the average!");
						}
						else if (tries == 18){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Not so good, my friend!");
						}
						else if (tries == 19){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Don't give up!");
						}
						else if (tries == 20){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Hmmm...Maybe you must try again!");
						}
						else if (tries == 21){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Were you wrong in something?!");
						}
						else if (tries == 22){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Maybe try another strategy in guessing the number ?!");
						}
						else if (tries == 23){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Try in another way!");
						}
						else if (tries == 24){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "Keep yourself away from guessing numbers!");
						}
						else if (tries == 25){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "A dog will guess it with lower tries!");
						}
						else if ((tries > 25) && (tries <= 30)){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "That's NOT A GOOD result!");
						}
						else if (tries > 30){
							message.setText("You have guessed the number " + generated + "\n in " + tries + " tries.\n" + "That's a BAD result!");
						}
						
						//if button is clicked, close the custom dialog
						tryAgain.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
								mp.release();
								vibrator.cancel();
								MainActivity.bgSound.setVolume(1, 1);
								finish();
								Intent intent = new Intent(OnePlayerMain.this, OnePlayerMain.class);
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
		Intent intent = new Intent(OnePlayerMain.this, ChoosingNumberOfPlayers.class);
		startActivityForResult(intent, 0);
	}
}
