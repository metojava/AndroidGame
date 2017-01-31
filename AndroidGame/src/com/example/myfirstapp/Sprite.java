package com.example.myfirstapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite {

	int x = 50;
	int y = 0;
	int xa = 0;
	int ya = 0;
	int rows = 4;
	int cols = 3;
	GameView gameView;
	Bitmap bmp;
	int width;
	int height;
	int curframe = 0;
	int anims[] = { 3, 1, 0, 2 };
	int animD = 0;
	int Yspeed = 0;
	int baseY = 260;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Sprite(GameView gameView, Bitmap bmp) {
		this.gameView = gameView;
		this.bmp = bmp;
		this.width = bmp.getWidth() / cols;
		this.height = bmp.getHeight() / rows;
		this.y = baseY;

	}

	public void update(boolean jump) {

		if(jump)
		{
			ya += -2;
		}
		
		if (y < 200 || jump == false) {
			Yspeed = 10;
			//y = baseY;
			if (jump)
				jump = false;
		}
		if(y>=baseY)
		{
			y=baseY;
			jump=false;
			Yspeed=0;
		}
		y += Yspeed;
		
		x += xa;
		curframe = ++curframe % cols;
	}

	public void moveY(int yd) {
		int difY = yd - y;
		if (difY < 0)
			difY *= -1;
		if (y < yd) {
			if (difY % 2 == 0)
				ya = 2;
			else
				ya = 1;
			animD = 0;
		} else {
			if (difY % 2 == 0)
				ya = -2;
			else
				ya = -1;
			animD = 3;
		}
		{
			while (y != yd) {
				y += ya;
				curframe = ++curframe % cols;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void moveX(int xd) {

		int difX = xd > x ? xd - x : x - xd;
		if (x < xd) {
			if (difX % 2 == 0)
				xa = 2;
			else
				xa = 1;
			animD = 2;
		} else {
			if (difX % 2 == 0)
				xa = -2;
			else
				xa = -1;
			animD = 1;
		}
		{
			while (x != xd) {
				x += xa;
				curframe = ++curframe % cols;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}

	}

	public void draw(Canvas canvas) {
		// update();
		//drawLocation(canvas);
		int srcX = curframe * width;
		int srcY = 2 * height;
		Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
		Rect dest = new Rect(x, y, x + width, y + height);
		canvas.drawBitmap(bmp, src, dest, null);
		
	}

	public void movetTo(int x2, int y2) {

		moveX(x2);
		moveY(y2);
		// gameView.invalidate();

	}

	public void drawLocation(Canvas canvas ) {
		Paint paint = new Paint();
		canvas.drawPaint(paint);
		paint.setColor(Color.GRAY);
		paint.setTextSize(16);
		canvas.drawText("Sprite Position"+getX()+" - "+getY(), 20, 20, paint);
		
	}
}
