package com.noe.game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Debug;


import com.noe.framework.Game;
import com.noe.framework.Graphics;
import com.noe.framework.Image;
import com.noe.framework.Screen;
import com.noe.framework.Input.TouchEvent;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Running;

	DecimalFormat df = new DecimalFormat("#.###");
	private static Background bg1, bg2;
	private static Canoe canoe;
	public static Snake snakeObject1, snakeObject2;//, snakeObject3;
	public static BlueCooler blueCooler;

	private Image currentSprite;
    private Image character;
    private Image character2;
    private Image character3;
    private Image snake1;
    private Image beerCan;
    private Image cooler;
    private Image shake2;
    private Image snake3;
    private Image snake4;
    private Image snake5;
    private Image snake6;
    private Image snake7;
    protected Animation anim, snakeAnimation1, snakeAnimation2;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	int livesLeft = 1;
	Paint paint, paint2, paint3, paintDebug;
	Paint paintGnar = new Paint();

	public GameScreen(Game game) {
		super(game);

		DecimalFormat df = new DecimalFormat("#.##");
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		canoe = new Canoe();
		snakeObject1 = new Snake(600, 360);
		snakeObject2 = new Snake(900, 360);
		blueCooler = new BlueCooler(600, 200);
		
		character = Assets.character;
		character2 = Assets.character2;
		character3 = Assets.character3;

		snake1 = Assets.snake1;
		shake2 = Assets.snake2;
		snake3 = Assets.snake3;
		snake4 = Assets.snake4;
		snake5 = Assets.snake5;
		snake6 = Assets.snake6;
		snake7 = Assets.snake7;
        Image snake8 = Assets.snake8;

		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);

		snakeAnimation1 = new Animation();
		snakeAnimation1.addFrame(snake1, 250);
		snakeAnimation1.addFrame(shake2, 250);
		snakeAnimation1.addFrame(snake3, 250);
		snakeAnimation1.addFrame(snake4, 250);
		snakeAnimation1.addFrame(snake5, 250);
		snakeAnimation1.addFrame(snake6, 250);
		snakeAnimation1.addFrame(snake7, 250);
		snakeAnimation1.addFrame(snake8, 250);
		
		snakeAnimation2 = new Animation();
		snakeAnimation2.addFrame(snake1, 250);
		snakeAnimation2.addFrame(shake2, 250);
		snakeAnimation2.addFrame(snake3, 250);
		snakeAnimation2.addFrame(snake4, 250);
		snakeAnimation2.addFrame(snake5, 250);
		snakeAnimation2.addFrame(snake6, 250);
		snakeAnimation2.addFrame(snake7, 250);
		snakeAnimation2.addFrame(snake8, 250);
		
		beerCan = Assets.beerCan;
		cooler = Assets.blueCooler;
		currentSprite = anim.getImage();

        loadMap();


        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint3 = new Paint();
        paint3.setTextSize(15);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

        paintDebug = new Paint();
        paintDebug.setTextSize(10);
        paintDebug.setColor(Color.WHITE);


	}

	private void loadMap() {
		ArrayList<String> lines;
        lines = new ArrayList<String>();
        int width = 0;

		Scanner scanner = new Scanner(SampleGame.map);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();


			if (null == line) {
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}

		for (int j = 0; j < 12; j++) {
			String line = lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

    	if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 0, 240, 65, 65)) {

					if (canoe.isReadyToFire()) {
						canoe.shoot();
					}
				}

				if(event.y <= 240 && event.x > 100){
					canoe.noeUp();
				}
				if(event.y > 240 && event.x > 100){
					canoe.noeDown();
				}
		
			
			}

			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, 415, 65, 65)) {
					currentSprite = anim.getImage();
					canoe.setDucked(false);

				}

				if (inBounds(event, 0, 0, 35, 35)) {
					pause();

				}

				if (event.x > 400) {
					// Move right.
					canoe.stopRight();
				}
				if (event.x < 399 && event.x > 100){
					canoe.stopLeft();
				}
			}
	

		}


		if (livesLeft == 0) {
			state = GameState.GameOver;
		}

		canoe.update();
		currentSprite = anim.getImage();
		
		ArrayList<Projectile> projectiles = canoe.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (p.isVisible()) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

		updateTiles();
		if(snakeObject1 !=null)
			snakeObject1.update();
		if(snakeObject2 !=null)
			snakeObject2.update();
		bg1.update();
		bg2.update();
		blueCooler.update();
		animate();
		if(canoe.getHealth() <=0){
			state = GameState.GameOver;
		}
		if (canoe.getCenterX() <= -1500) {
			state = GameState.GameOver;
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
        return event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			t.update();
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		
		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
		paintTiles(g);

		ArrayList<Projectile> projectiles = canoe.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
            g.drawImage(beerCan, p.getX(), p.getY());
		}

		g.drawImage(currentSprite, Canoe.yellowRed.centerX() - (currentSprite.getWidth()/2),
				Canoe.yellowRed.centerY() - (currentSprite.getHeight()/2));

        g.drawRect(canoe.getCenterX(), (int)canoe.getCenterY(), 10, 10, Color.WHITE);
		if(snakeObject1 !=null)
			g.drawImage(snakeAnimation1.getImage(), snakeObject1.getCenterX() - (snakeAnimation1.getImage().getWidth()/2),
				snakeObject1.getCenterY() - (snakeAnimation1.getImage().getHeight() /2));
		
		if(snakeObject2 !=null)
			g.drawImage(snakeAnimation2.getImage(), snakeObject2.getCenterX()- (snakeAnimation2.getImage().getWidth()/2),
                    snakeObject2.getCenterY() - (snakeAnimation2.getImage().getHeight() /2));
		
		g.drawImage(cooler,  blueCooler.getCenterX() - (cooler.getWidth() / 2), blueCooler.getCenterY() - (cooler.getHeight() / 2));


		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = tilearray.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	public void animate() {
		anim.update(10);
		snakeAnimation1.update(50);
		snakeAnimation2.update(50);
	}

	private void nullify() {


		paint = null;
		bg1 = null;
		bg2 = null;
		canoe = null;
		snakeObject1 = null;
		snakeObject2 = null;
		currentSprite = null;
		character = null;
		character2 = null;
		character3 = null;
		snake1 = null;
		shake2 = null;
		snake3 = null;
		snake4 = null;
		snake5 = null;
		anim = null;
		snakeAnimation1 = null;
		snakeAnimation2 = null;

		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawString("Shoot Beer!", 45, 210, paint3);
		g.drawImage(Assets.beerCan, 10, 240, 0, 0, 75, 75);
		g.drawString("Beers Left: " + canoe.getBeers(), 700, 25, paint);
		g.drawString("'Noe Health: " + canoe.getHealth(), 500, 25, paint);
		g.drawString("BAC: " + df.format(canoe.getBac()), 200, 25, paint);
/*        g.drawString("CANOE CENTER X " + bg1.getBgX() , 200, 50, paint);
        g.drawString("CANOE CENTER X2 " + bg2.getBgX() , 200, 75, paint);*/
        if(Debug.isDebuggerConnected()){
            g.drawString("Center bg1: " + bg1.getBgX()+ " " + "Center bg2: " + bg2.getBgX(), 100, 55, paintDebug );
            g.drawRect(snakeObject1.getCenterX(), snakeObject1.getCenterY(), snake1.getWidth(), snake1.getHeight(), Color.WHITE);
        }
		if(canoe.bac > .1)
			g.drawString("Going to have trouble paddling if you keep drinking!", 400, 460, paint);
        if(bg1.getBgX() < -500 && bg1.getBgX() > -1000)
            g.drawString("Rapids!", 350, 400, paint);
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER.", 400, 240, paint2);
		g.drawString("Tap to return.", 400, 290, paint);

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		// TODO Auto-generated method stub
		return bg2;
	}
	
	public static Canoe getCanoe() {
		// TODO Auto-generated method stub
		return canoe;
	}
    public static BlueCooler getBlueCooler(){
        return blueCooler;
    }

    public static ArrayList<Enemy> getEnemy(){
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(snakeObject1);
        enemies.add(snakeObject2);
        return enemies;
    }
}