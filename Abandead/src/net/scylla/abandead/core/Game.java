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
	
	private void start() {
		try {
			//Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("AbanDead");
			//Display.setFullscreen(true);
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(!Display.isCloseRequested()) {
			pollInput();
			Display.update();
		}
		Display.destroy();
	}
	
	private void pollInput() {
		
		if(Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();
			
			System.out.println("Mouse pressed at " + x + ", " + y);
		}
	}
}
