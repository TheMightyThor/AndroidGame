package com.noe.game;

import android.graphics.Rect;


public class Enemy {

    private int power, centerX, centerY;
    double speedX;
    double speedY;
    private Background bg = GameScreen.getBg1();
    private Canoe canoe = GameScreen.getCanoe();

    public Rect r = new Rect(0, 0, 0, 0);
    public int health = 2;

    private double movementSpeed;

    public void update() {
        follow();
        centerX += speedX;
        centerY += speedY;

        speedX = bg.getSpeedX() + movementSpeed;
        r.set(centerX - 44, centerY - 87, centerX + 44, centerY + 87);

        if (Rect.intersects(r, Canoe.yellowRed)) {
            checkCollision();
        }


    }

    private void checkCollision() {
        if (Rect.intersects(r, Canoe.rect) || Rect.intersects(r, Canoe.rect2)
                || Rect.intersects(r, Canoe.yellowRed) || Rect.intersects(r, Canoe.rect4)) {
            canoe.health -= 1;
            centerX += 200;
            if (canoe.health <= 0) {
                canoe.sink();
            }
        }
    }

    public void follow() {

        if (centerY < 10) {
            speedY += 2;
        }
        if (centerY >= 450) {
            speedY += -2;
        }

        movementSpeed = -1;



        double y = (Math.sqrt(canoe.yellowRed.centerY() - centerY) / 10);

        if (y > 0 && y < 1) {
            speedY = 1;
        } else if (y > 0) {
            speedY = y;
        } else
            speedY = -(Math.sqrt(Math.abs(canoe.yellowRed.centerY() - centerY)) / 10);
    }



	public void die() {
		
	}

	public void attack() {

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

    public Rect getEnemyRec(){return r;}

}