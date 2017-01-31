package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MyActivity extends Activity {
	GameView gameView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		gameView = new GameView(this);
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		setContentView(gameView);
	}

	@Override
	public void onStop() {
		super.onStop();
		gameView.onStop();
	}

	@Override
	public void onPause() {
		super.onPause();
		gameView.onPause();

	}

	@Override
	public void onResume() {
		super.onResume();
		gameView.onResume();
	}
}
