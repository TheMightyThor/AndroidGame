package com.noe.game;

import android.graphics.Paint;

import com.noe.framework.Game;
import com.noe.framework.Graphics;
import com.noe.framework.Screen;
import com.noe.framework.Graphics.ImageFormat;

public class SplashLoadingScreen extends Screen {
	public SplashLoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		
		Assets.splash= g.newImage("background.png", ImageFormat.RGB565);
		game.setScreen(new LoadingScreen(game));

	}

	@Override
	public void paint(float deltaTime) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}