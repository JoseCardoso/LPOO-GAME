package com.jumpityJump.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jumpityJump.game.JumpityJump;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Jumpity Jump";
		config.useGL30 = true;
		config.width = 1280;
		config.height =720;
		new LwjglApplication(new JumpityJump(), config);
	}
}
