package com.noe.game;

import java.util.ArrayList;

import com.noe.framework.Image;
import com.noe.framework.Music;
import com.noe.framework.Sound;

public class Assets {
	
	public static Image menu, splash, background, character, character2, character3, snake1, snake2, snake3, snake4, snake5, snake6, snake7, snake8;
	public static Image beerCan, blueCooler;
	public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, characterJump, characterDown;
	public static Image button;
	public static Sound beerOpen;
	public static Music theme;
	
	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		beerOpen = sampleGame.getAudio().createSound("beerOpen.wav");
		theme = sampleGame.getAudio().createMusic("SpanishCaravan.m4a");
		theme.setLooping(true);
		theme.setVolume(0.5f);
		theme.play();
		
	}
	public static void getBeerCan(){
		beerOpen.play(0.6f);
	}
	
}
