package com.noe.game;

import android.os.Vibrator;

public class VibesService {
	public VibesService(Vibrator vibe){
		this.vibes = vibe;
	}
	Vibrator vibes;
	
	public void getVibes(){
		vibes.vibrate(1000);
	}
	public void getShotVibes(){
		vibes.vibrate(20);
	}
}