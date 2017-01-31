package com.example.myfirstapp;

public class Background {

	GameView gameView;

	int bx;
	int by;
	int yspeed;
	int xspeed;

	public Background(GameView gameView, int x, int y) {
		this.bx = x;
		this.by = y;
		this.gameView = gameView;
		this.yspeed = 2;
		this.xspeed = 8;
	}

	public Background(GameView gameView) {
		this(gameView, 0, 0);
	}

	public GameView getGameView() {
		return gameView;
	}

	public void updatey() {
		int l = getGameView().getBmpHeight();
		by += yspeed;
		if (getBy() >= l) {
			this.by = this.by + (-2 * l);
		}

	}

	public void updatex() {
		int l = getGameView().getBmpWidth();
		bx -= xspeed;
		if (getBx() <= -1 * l) {
			this.bx = this.bx + (2 * l);
		}

	}

	public int getBx() {
		return bx;
	}

	public void setBx(int bx) {
		this.bx = bx;
	}

	public int getBy() {
		return by;
	}

	public void setBy(int by) {
		this.by = by;
	}

}
