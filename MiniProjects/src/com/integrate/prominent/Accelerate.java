package com.integrate.prominent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener{

	float x, y, sensorX, sensorY;
	Bitmap ball;
	SensorManager sm;
	MyBringBackSurface mySurfaceView;
	
	public class MyBringBackSurface extends SurfaceView implements Runnable{

		SurfaceHolder myHolder;
		Thread myThread = null;
		boolean isRunning = false;
		
		public MyBringBackSurface(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
			myHolder = getHolder();
		}
		
		public void pause(){
			isRunning = false;
			while(true){
				try {
					myThread.join();
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			myThread = null;
		}
		
		public void resume(){
			isRunning = true;
			myThread = new Thread(this);
			myThread.start();
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				if(!myHolder.getSurface().isValid()){
					continue;
				}
				
				Canvas canvas = myHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				float centerX = canvas.getWidth()/2;
				float centerY = canvas.getHeight()/2;
				canvas.drawBitmap(ball, centerX + sensorX*20, centerY + sensorY*20, null);
				
				myHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new MyBringBackSurface(this));
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		x = y = sensorX = sensorY = 0;
		
		mySurfaceView = new MyBringBackSurface(this);
		mySurfaceView.resume();
		setContentView(mySurfaceView);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sm.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(16);
		} 
		catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sensorX = e.values[0];
		sensorY = e.values[1];
	}

}
