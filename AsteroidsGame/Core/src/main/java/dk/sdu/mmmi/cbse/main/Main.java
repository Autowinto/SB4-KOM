package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		
		Lwjgl3ApplicationConfiguration cfg =
			new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		cfg.setResizable(false);
		cfg.setWindowedMode(1200, 1000);
		
		new Lwjgl3Application(new Game(), cfg);
		
	}
	
}
