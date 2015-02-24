package com.noe.game;

import android.graphics.Rect;

import com.noe.framework.Game;
import com.noe.framework.Image;

import java.util.ArrayList;

public class Tile {

	private int tileX, tileY;
    public int type;
	public Image tileImage;

	private Canoe canoe = GameScreen.getCanoe();
	private Background bg = GameScreen.getBg1();
    private BlueCooler cooler = GameScreen.getBlueCooler();
	private Rect r;
    private ArrayList<Enemy> enimies = GameScreen.getEnemy();
	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;

		type = typeInt;

		r = new Rect();

		if (type == 5) {
			tileImage = Assets.tiledirt;
		} else if (type == 8) {
			tileImage = Assets.tilegrassTop;
		} else if (type == 4) {
			tileImage = Assets.tilegrassLeft;

		} else if (type == 6) {
			tileImage = Assets.tilegrassRight;

		} else if (type == 2) {
			tileImage = Assets.tilegrassBot;
		} else {
			type = 0;
		}

	}

		public void update() {
            double speedX = bg.getSpeedX();
			tileX += speedX;
			r.set(tileX, tileY, tileX+40, tileY+40);

			if (Rect.intersects(r, Canoe.yellowRed) && type != 0) {
				checkVerticalCollision(Canoe.yellowRed);//Canoe.rect, Canoe.rect2);
				checkSideCollision(Canoe.yellowRed); //, Canoe.rect4, Canoe.footleft, Canoe.footright);
			}
            if(Rect.intersects(r, cooler.getPowerUpRec())){
                if (Rect.intersects(cooler.getPowerUpRec(), r) && type == 8) {
                    cooler.isMovingUp = false;
                }

                if (Rect.intersects(cooler.getPowerUpRec(), r) && type == 5) {
                    cooler.isMovingUp = true;
                }
            }
            for(Enemy enemy : enimies){
            if(Rect.intersects(r, enemy.getEnemyRec())){
                if(Rect.intersects(enemy.getEnemyRec(), r) && type == 8){
                    enemy.setCenterY(enemy.getCenterY() + 1);
                }
                if(Rect.intersects(enemy.getEnemyRec(), r) && type == 5){
                    enemy.setCenterY(enemy.getCenterY()- 1);
                }
            }

            }
		}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void checkVerticalCollision(Rect rect) {
		if (Rect.intersects(rect, r) && type == 8) {
			canoe.setCenterY(tileY+20);
		}

		if (Rect.intersects(rect, r) && type == 5) {
			canoe.setCenterY(tileY-40);
		}
	}

	public void checkSideCollision(Rect rect) {
		if (type != 5 && type != 8 && type != 0){

            if (Rect.intersects(rect, r)) {
				//canoe.setCenterX(tileX + 102);
				canoe.canMove = false;
	
			}/*else if (Rect.intersects(leftfoot, r)) {

				canoe.setCenterX(tileX + 85);
				canoe.canMove = false;
			}

			if (Rect.intersects(rright, r)) {
				canoe.setCenterX(tileX - 62);
				canoe.canMove = false;
			}

			else if (Rect.intersects(rightfoot, r)) {
				canoe.setCenterX(tileX - 45);
				canoe.canMove = false;
			}*/
		}
		else {
			canoe.canMove = true;
			canoe.moveRight();
			canoe.setMoveSpeed(1);
		}
	}

}