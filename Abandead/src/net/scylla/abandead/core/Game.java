package net.scylla.abandead.core;


import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(!Display.isCloseRequested()) {
			Display.update();
		}
		Display.destroy();
	}
}