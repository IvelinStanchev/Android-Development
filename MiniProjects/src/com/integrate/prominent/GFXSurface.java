package com.integrate.prominent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	MyBringBackSurface mySurfaceView;
	float x, y, sX, sY, fX, fY, dX, dY, aniX, aniY, scaledX, scaledY;
	Bitmap test, circle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mySurfaceView = new MyBringBackSurface(this);
		mySurfaceView.setOnTouchListener(this);
		x = 0;
		y = 0;
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		dX = dY = aniX = aniY = scaledX = scaledY = 0;
		test = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		circle = BitmapFactory.decodeResource(getResources(), R.drawable.buttonhovered);
		setContentView(mySurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mySurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mySurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(50);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
			break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaledX = dX / 30;
			scaledY = dY / 30;
			x = 0;
			y = 0;
			break;
		}
		
		return true;
	}

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
				if (x != 0 && y != 0){
					canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
				}
				if (sX != 0 && sY != 0){
					canvas.drawBitmap(circle, sX-(circle.getWidth()/2), sY-(circle.getHeight()/2), null);
				}
				if (fX != 0 && fY != 0){
					canvas.drawBitmap(test, fX-(test.getWidth()/2)-aniX, fY-(test.getHeight()/2)-aniY, null);
					canvas.drawBitmap(circle, fX-(circle.getWidth()/2), fY-(circle.getHeight()/2), null);
				}
				
				aniX = aniX + scaledX;
				aniY = aniY + scaledY;
				
				myHolder.unlockCanvasAndPost(canvas);
				
			}
		}
	}
}
