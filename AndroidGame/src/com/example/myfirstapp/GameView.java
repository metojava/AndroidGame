package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class GameView extends SurfaceView implements Runnable {

	SurfaceHolder holder;
	Bitmap bmp, spriteBmp;
	Thread thread;
	boolean running = true;
	boolean jump = false;
	Background bgone;
	Background bgtwo;
	Sprite sprite;

	public int getBmpWidth() {
		return bmp.getWidth();
	}

	public int getBmpHeight() {
		return bmp.getHeight();
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Bitmap getBmp() {
		return bmp;
	}

	public GameView(Context context) {
		super(context);

		setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				int x = (int) event.getX();
				int y = (int) event.getY();

				switch (action) {
				case MotionEvent.ACTION_DOWN:
					// sprite.movetTo(x, y);
					jump = true;
					Log.i("GameView - jump - ","jump");
					break;
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_UP:
					jump = false;
					break;
					default :
						break;

				}
				return true;
			}
		});
		thread = new Thread(this);
		// thread.start();

		holder = getHolder();

		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				setRunning(false);

			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
				running = true;
				if (!thread.isAlive())
					thread.start();

			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}
		});
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bgr);
		spriteBmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.image);

		this.bgone = new Background(this, 0, 0);
		this.bgtwo = new Background(this, bmp.getWidth(), 0);
		this.sprite = new Sprite(this, spriteBmp);

	}

	@Override
	public void run() {

		while (running) {

			Canvas canv = null;
			if (!holder.getSurface().isValid())
				continue;
			try {
				canv = holder.lockCanvas(null);
				synchronized (getHolder()) {
					drawScrollingBackground(canv);
				}
				if (canv != null)
					holder.unlockCanvasAndPost(canv);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void drawScrollingBackground(Canvas canvas) {

		bgone.updatex();
		bgtwo.updatex();
		sprite.update(jump);

		// Log.i("GameView", canvas.getWidth() + " " + canvas.getHeight());
		// canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(bmp, bgone.getBx(), bgone.getBy(), null);
		Log.i("GameView - bgone x - ", bgone.getBx() + "");
		canvas.drawBitmap(bmp, bgtwo.getBx(), bgtwo.getBy(), null);
		Log.i("GameView", canvas.getWidth() + " " + canvas.getHeight());
		Log.i("GameView - bgtwo x - ", bgtwo.getBx() + "");

		sprite.draw(canvas);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// sprite.movetTo(x, y);
			jump = true;
			Log.i("GameView - jump - ","jump");
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			jump = false;
			break;

		}
		// invalidate();
		return true;
	}

	public void onResume() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void onPause() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onStop() {
		running = false;
		try {
			thread.destroy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
