package com.noe.game;

import com.noe.framework.Game;
import com.noe.framework.Graphics;
import com.noe.framework.Screen;
import com.noe.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("Menu.png", ImageFormat.RGB565);
		Assets.background = g.newImage("background.png", ImageFormat.RGB565);
		Assets.character = g.newImage("rednoe.jpg", ImageFormat.ARGB4444);
		Assets.character2 = g.newImage("rednoe.jpg", ImageFormat.ARGB4444);
		Assets.character3  = g.newImage("rednoe.jpg", ImageFormat.ARGB4444);
		Assets.characterJump = g.newImage("rednoe.jpg", ImageFormat.ARGB4444);
		Assets.characterDown = g.newImage("rednoe.jpg", ImageFormat.ARGB4444);


		
		Assets.snake1 = g.newImage("Snake1.png", ImageFormat.ARGB4444);
		Assets.snake2 = g.newImage("snake2.png", ImageFormat.ARGB4444);
		Assets.snake3  = g.newImage("snake3.png", ImageFormat.ARGB4444);
		Assets.snake4  = g.newImage("snake4.png", ImageFormat.ARGB4444);
		Assets.snake5  = g.newImage("snake5.png", ImageFormat.ARGB4444);
		Assets.snake6 = g.newImage("snake6.png", ImageFormat.ARGB4444);
		Assets.snake7 = g.newImage("snake7.png", ImageFormat.ARGB4444);
		Assets.snake8 = g.newImage("snake8.png", ImageFormat.ARGB4444);
		
		Assets.tiledirt = g.newImage("tiledirt.png", ImageFormat.RGB565);
		Assets.tilegrassTop = g.newImage("tilegrasstop.png", ImageFormat.RGB565);
		Assets.tilegrassBot = g.newImage("tilegrassbot.png", ImageFormat.RGB565);
		Assets.tilegrassLeft = g.newImage("tilegrassleft.png", ImageFormat.RGB565);
		Assets.tilegrassRight = g.newImage("tilegrassright.png", ImageFormat.RGB565);
		Assets.blueCooler = g.newImage("blueCooler.jpg", ImageFormat.RGB565);
		Assets.beerCan = g.newImage("Guinness.jpg", ImageFormat.ARGB4444);


		
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
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