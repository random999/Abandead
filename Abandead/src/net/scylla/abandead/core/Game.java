package net.scylla.abandead.core;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Game {

	private int xScroll = 0;
	private int yScroll = 0;
	private int frameCount = 0;
	private boolean running = true;
	
	private ArrayList<QuadTile> tileList;

	private static final float TILE_SIZE = 128;
	private static final float MAP_WIDTH = 5;
	private static final float MAP_HEIGHT = 5;

	private long prevTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	private void start() {
		tileList = new ArrayList<QuadTile>();
		IGDisplay.create(800, 600);
		try {
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
		
		while (running) {
			pollInput();
			draw();
			outputFPS();
		}
		
		int counter = 0;
		for(QuadTile tile : tileList) {
			System.out.println(++counter + "-- X: " + tile.getX() + " Y: " + tile.getY());
			
		}
		System.out.println("Exiting game...");
		Display.destroy();
	}

	private void draw() {

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		drawBackground();
		drawSprite();
		Display.update();
	}

	private void pollInput() {

		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();

			System.out.println("Mouse pressed at " + x + ", " + y);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			running=false;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			yScroll++;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			yScroll--;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			xScroll++;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			xScroll--;
		}
		
		if(Display.isCloseRequested()) {
			running=false;
		}

	}

	private void drawBackground() {
		
		tileList.clear();

		for (float x = 0; x < MAP_WIDTH; x++) {
			for (float y = 0; y < MAP_HEIGHT; y++) {
				glLoadIdentity();
				//glTranslatef(x*TILE_SIZE,y*TILE_SIZE,0f);
				float R = x/MAP_WIDTH;
				float G = y/MAP_HEIGHT;
				glColor3f(R,G,0f);
				QuadTile tile = new QuadTile(x*TILE_SIZE+xScroll, y*TILE_SIZE+yScroll, TILE_SIZE);
				tileList.add(tile);
			}
		}

		glLoadIdentity();

		if (xScroll > Display.getWidth()) {
			xScroll = 0;
		} else if (xScroll < 0) {
			xScroll = Display.getWidth();
		}

		if (yScroll > Display.getHeight()) {
			yScroll = 0;
		} else if (yScroll < 0) {
			yScroll = Display.getHeight();
		}

		//if (yScroll + 200 > Display.getHeight()) {
		//	glLoadIdentity();
		//	glTranslatef(xScroll, 0f, 0f);
		//	glColor3f(0.5f, 0.5f, 1.0f);
		//	glBegin(GL_QUADS);
		//		glVertex2d(0, 0);
		//		glColor3f(1.0f, 0.0f, 0.0f);
		//		glVertex2d(200, 0);
		//		glColor3f(0.0f, 1.0f, 0.0f);
		//		glVertex2d(200, yScroll - Display.getHeight() + 200);
		//		glColor3f(0.0f, 0.0f, 1.0f);
		//		glVertex2d(0, yScroll - Display.getHeight() + 200);
		//	glEnd();
		//}

		//if (xScroll + 200 > Display.getWidth()) {
		//	glLoadIdentity();
		//	glColor3f(0.5f, 0.5f, 1.0f);
		//	glTranslatef(0f, yScroll, 0f);
		//	glBegin(GL_QUADS);
		//		glVertex2d(0, 0);
		//		glColor3f(1.0f, 0.0f, 0.0f);
		//		glVertex2d(200 + xScroll - Display.getWidth(), 0);
		//		glColor3f(0.0f, 1.0f, 0.0f);
		//		glVertex2d(200 + xScroll - Display.getWidth(), 200);
		//		glColor3f(0.0f, 0.0f, 1.0f);
		//		glVertex2d(0, 200);
		//	glEnd();
		//}

		//glColor3f(0.5f, 0.5f, 1.0f);
		//glLoadIdentity();
		//glBegin(GL_QUADS);
		//	glVertex2d(xScroll, yScroll);
		//	glColor3f(1.0f, 0.0f, 0.0f);
		//	glVertex2d(200 + xScroll, yScroll);
		//	glColor3f(0.0f, 1.0f, 0.0f);
		//	glVertex2d(200 + xScroll, 200 + yScroll);
		//	glColor3f(0.0f, 0.0f, 1.0f);
		//	glVertex2d(xScroll, 200 + yScroll);
		//glEnd();

		// xScroll++;
		// yScroll++;
		glLoadIdentity();
		glBegin(GL_LINES);
			glVertex2d(xScroll, 0);
			glVertex2d(xScroll, Display.getHeight());
			glVertex2d(0, yScroll);
			glVertex2d(Display.getWidth(), yScroll);
			glVertex2d(xScroll + 200, 0);
			glVertex2d(xScroll + 200, Display.getHeight());
			glVertex2d(0, yScroll + 200);
			glVertex2d(Display.getWidth(), yScroll + 200);
		glEnd();
		glBegin(GL_LINE_LOOP);
			glVertex2d(0, 0);
			glVertex2d(Display.getWidth(), 0);
			glVertex2d(Display.getWidth(), Display.getHeight());
			glVertex2d(0, Display.getHeight());
		glEnd();
	}

	private void outputFPS() {

		long newTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();

		if (newTime - prevTime >= 1000) {
			System.out.println(frameCount + " fps");
			frameCount = 0;
			prevTime = newTime;
		}
		frameCount++;
	}

	private void drawSprite() {
		glLoadIdentity();
		glTranslatef(Display.getWidth() / 2, Display.getHeight() / 2, 0.0f);
		glRotatef(xScroll, 0.0f, 0.0f, 1.0f);
		glColor3f(0.2f, 0.7f, 0.3f);
		glBegin(GL_TRIANGLES);
		glVertex2d(-10, -15);
		glVertex2d(10, -15);
		glVertex2d(0, 15);
		glEnd();
	}
}
