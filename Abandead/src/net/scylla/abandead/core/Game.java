package net.scylla.abandead.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Game {
	
	private int xScroll = 0;
	private int yScroll = 0;
	private int frameCount = 0;
	
	private long prevTime = (Sys.getTime()*1000)/Sys.getTimerResolution();

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	private void start() {
		try {
			setDisplayMode();
			Display.setTitle("AbanDead");
			// Display.setFullscreen(true);
			System.out.println("Initializing AbanDead...");
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		
		while (!Display.isCloseRequested()) {
			pollInput();
			draw();
			outputFPS();
		}


		System.out.println("Exiting game...");
		Display.destroy();
	}

	private void pollInput() {

		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();

			System.out.println("Mouse pressed at " + x + ", " + y);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			//yScroll++;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			//yScroll--;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			//xScroll--;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			//xScroll++;
		}

	}

	private void setDisplayMode() {
		DisplayMode[] modes = null;
		DisplayMode mode = null;
		try {
			modes = Display.getAvailableDisplayModes();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (modes == null) {
			System.out.println("Unable to find any display modes");
			System.exit(-1);
		}

		for (DisplayMode mode1 : modes) {
			if (mode1.getWidth() == 800 && mode1.getHeight() == 600) {
				mode = mode1;
				break;
			}
		}

		if (mode == null) {
			System.out.println("No compatible display mode found");
			System.exit(-1);
		}

		try {
			Display.setDisplayMode(mode);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void draw() {
		
		if(xScroll+200 > Display.getWidth()) {
			xScroll = 0;
		}
		
		if(yScroll+200 > Display.getHeight()) {
			yScroll = 0;
		}
		
		xScroll=0;
		yScroll=0;
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//glColor3f(0.5f, 0.5f, 1.0f);
		glBegin(GL_QUADS);
			glVertex2d(xScroll, yScroll);
			glVertex2d(200+xScroll, yScroll);
			glVertex2d(200+xScroll, 200+yScroll);
			glVertex2d(xScroll, 200+yScroll);
		glEnd();

		//xScroll++;
		//yScroll++;
		
		Display.update();
	}
	
	private void outputFPS() {
		
		long newTime = (Sys.getTime() *1000)/Sys.getTimerResolution();
		
		if(newTime - prevTime >= 1000) {
			System.out.println(frameCount + " fps");
			frameCount = 0;
			prevTime = newTime;
		}
		frameCount++;	
	}
}
