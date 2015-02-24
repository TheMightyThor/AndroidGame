package com.noe.game;

import android.graphics.Rect;

import java.util.ArrayList;

public class PowerUp {
	private int centerX, centerY;
	double speedX;
	double speedY;
	private Background bg = GameScreen.getBg1();
	private Canoe canoe = GameScreen.getCanoe();


	public Rect r = new Rect(0, 0, 0, 0);
	boolean isMovingUp;


	public void update() {
		floatPowerUp();
		centerX += speedX;
		centerY += speedY;

		r.set(centerX - 25, centerY - 45, centerX + 25, centerY + 25);

		if (Rect.intersects(r, Canoe.yellowRed)) {
			checkCollision();
		}


	}

	private void checkCollision() {
		if (Rect.intersects(r, Canoe.rect)|| Rect.intersects(r, Canoe.rect2)
				|| Rect.intersects(r, Canoe.rect3) || Rect.intersects(r, Canoe.yellowRed)) {
			canoe.beers += 12;
            centerX += canoe.getCenterX() * 5;
		}
	}

	public void floatPowerUp() {
		
		if (centerY < 10 ){
			isMovingUp = false;
		}
		if(centerY >= 400){
			isMovingUp = true;
		}

		if(isMovingUp){
			speedY = -2;
		}
		else {
			speedY = 2;
		}
		
		speedX = -2;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

    public Rect getPowerUpRec() { return r;}

    public void changeDirection(){
        if (isMovingUp){
            isMovingUp = false;
        } else {
            isMovingUp = true;
        }
    }
}
