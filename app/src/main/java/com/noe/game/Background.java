package com.noe.game;


public class Background {
	
	private int bgX, bgY;
	private double speedX;

	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedX = 0;
	}
	
	public void update() {
		if(speedX <= -.25){
			bgX += (int) speedX - 1;
		}

			if (bgX <= -2160){
				bgX += 4320;
			}
		
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	
	
	
}
