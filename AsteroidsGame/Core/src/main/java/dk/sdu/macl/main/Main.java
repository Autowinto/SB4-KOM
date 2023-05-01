package dk.sdu.macl.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration cfg =
			new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		cfg.setResizable(false);
		cfg.setWindowedMode(1200, 1000);

		// Spring Framework
		AnnotationConfigApplicationContext springApp = new AnnotationConfigApplicationContext();
		springApp.scan("dk.sdu.macl.main");
		springApp.refresh();

		new Lwjgl3Application((ApplicationListener) springApp.getBean("game"), cfg);
		
	}
	
}
