package com.noe.game;


import java.util.ArrayList;

import com.noe.framework.impl.AndroidGame;
import android.graphics.Rect;

public class Canoe {

	// Constants are Here
	private int moveSpeed = 1;

	private int centerX = 200;
	private double centerY = 277;
    private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
    public int health= 5;
	public double bac = 0;
    private double rotation =  0;
	private int speedX = 0;
	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rect2 = new Rect(0, 0, 0, 0);
	public static Rect rect3 = new Rect(0, 0, 0, 0);
	public static Rect rect4 = new Rect(0, 0, 0, 0);
	public static Rect yellowRed = new Rect(0, 0, 0, 0);
	public Boolean canMove =true;
	public int beers = 12;
    public boolean movingUpDown;
    public void setCenterYend(int centerYend) {
        this.centerYend = centerYend;
    }

    public double centerYend =277;
    public boolean isMovingUp;
	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    public void update() {
        if(movingUpDown) {
            if (centerY > centerYend) {
                centerY += (-4 + ((bac +1) *10));
                if (centerY <= centerYend) {
                    movingUpDown = false;
                    centerY = centerYend;
                }
            } else if (centerY < centerYend) {
                centerY += (4 - ((bac +1) * 10));
                if (centerY >= centerYend) {
                    movingUpDown = false;
                    centerY = centerYend;
                }
            }
        }
		if(canMove) {
            if (speedX < 0) {
                centerX += speedX;
            }
            if (speedX == 0 || speedX < 0) {
                bg1.setSpeedX(0);
                bg2.setSpeedX(0);
            }
            bg1.setSpeedX(-moveSpeed);
            bg2.setSpeedX(-moveSpeed);

            if(bg1.getBgX() < -500 && bg1.getBgX() > -1000){
                bg1.setSpeedX( -moveSpeed * 3 );
                bg2.setSpeedX( -moveSpeed * 3 );
            } else {
                moveSpeed = 1;
            }

            if (centerX + speedX <= 60) {
                centerX = 61;
            }

            yellowRed.set(centerX - 88, (int)centerY - 44, centerX + 88, (int)centerY + 44);
            if (rotation> 0){
                rotation -=1;
            }
            if(rotation < 0 ){
                rotation +=1;
            }
        }
        else
		{
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
			speedX =0;
			moveSpeed = 0;
		}
		
	}

	public void moveRight() {
		if (!ducked) {
			speedX = moveSpeed;
		}
	}

	public void moveLeft() {
		if (!ducked) {
			speedX = -moveSpeed;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}
	public double getBac(){
		if(bac <= 0){
			return bac = 0;
		}
		if(bac != 0){
			if(Math.abs(bg1.getBgX()) %101 ==1){
				bac +=-.05;
			}

		}
		return bac;
	}
	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}
	public int getBeers(){
		return beers;
	}
	private void stop() {
		if (!isMovingRight() && !isMovingLeft()) {
			speedX = 0;
		}

		if (!isMovingRight() && isMovingLeft()) {
			moveLeft();
		}

		if (isMovingRight() && !isMovingLeft()) {
			moveRight();
		}

	}

	public void noeUp(){
//		if(centerY >= 110){
//			centerY += -10 + (bac * 5);
//		}
        centerYend = centerY - (40 * (bac * 10));
        isMovingUp = true;
        movingUpDown = true;
	}
	public void noeDown(){		
//		if(centerY < 380) {
//            centerY += 10 - (bac * 5);
//
//        }
        centerYend = centerY + (40 * (bac * 10));
        isMovingUp = false;
        movingUpDown = true;
	}
	public int getHealth(){
		return health;
	}
	public void shoot() {
		if (beers >= 1) {
			beers -= 1;
			Projectile p = new Projectile(centerX + 88, (int)centerY);
			projectiles.add(p);
			AndroidGame.vibes.vibrate(25);
			if(bac == 0)
				bac = .02;
			bac += .045;
		}
	}
    public double getRotation(){ return rotation;}
	public int getCenterX() {
		return centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
        boolean readyToFire = true;
        return readyToFire;
	}

	public void sink(){
	}
	public void setMoveSpeed(int speed){
		this.moveSpeed = speed;
	}
}
