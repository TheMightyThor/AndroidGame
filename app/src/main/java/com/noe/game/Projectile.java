package com.noe.game;

import android.graphics.Rect;


public class Projectile {
	
	private int x, y, speedX;
	private boolean visible;
	
	private Rect r;
	
	public Projectile(int startX, int startY){
		x = startX;
		y = startY;
		speedX = 10 - (int)GameScreen.getCanoe().getBac();
		visible = true;
		
		r = new Rect(0, 0, 0, 0);
	}
	
	public void update(){
		x += speedX;
		r.set(x, y, x+41, y+23);
		if (x > 800){
			visible = false;
			r = null;
		}
		if (visible){
			checkCollision();
		}
		
	}

	private void checkCollision() {
		
		if (GameScreen.snakeObject1 !=null && Rect.intersects(r, GameScreen.snakeObject1.r)){
			visible = false;

			if (GameScreen.snakeObject1.health > 0) {
				GameScreen.snakeObject1.health -= 1;
			}
			if (GameScreen.snakeObject1.health == 0 && GameScreen.snakeObject2 != null) {
				GameScreen.snakeObject1.setCenterX(GameScreen.snakeObject2.getCenterX()+450);
				GameScreen.snakeObject1.health = 2;
			} 
			else if(GameScreen.snakeObject2 == null)
				GameScreen.snakeObject1 = null;

		}
		if (GameScreen.snakeObject2 !=null && Rect.intersects(r, GameScreen.snakeObject2.r)){
			visible = false;

			if (GameScreen.snakeObject2.health > 0) {
				GameScreen.snakeObject2.health -= 1;
			}
			if (GameScreen.snakeObject2.health == 0 && GameScreen.snakeObject1 != null) {
				GameScreen.snakeObject2.setCenterX(GameScreen.snakeObject1.getCenterX() + 450);
				GameScreen.snakeObject2.health = 2;
				
			}
			else if(GameScreen.snakeObject1 == null )
				GameScreen.snakeObject2 = null;
		}

	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
