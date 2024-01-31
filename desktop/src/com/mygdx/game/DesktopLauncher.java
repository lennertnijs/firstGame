package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {

		// Used to configure the application settings.
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		// Configuring the settings of game window.
		config.setTitle("Game Title");
		config.setWindowedMode(1080, 1080);
		config.useVsync(false);
		config.setForegroundFPS(144);

		// Start the game with the settings.
		new Lwjgl3Application(new MyGame(), config);
	}
}
